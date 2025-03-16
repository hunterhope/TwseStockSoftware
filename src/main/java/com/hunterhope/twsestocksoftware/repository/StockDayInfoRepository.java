/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hunterhope.twsestocksoftware.repository;

import com.hunterhope.twsedbsave.service.StepInfo;
import com.hunterhope.twsedbsave.service.TwseDbQueryService;
import com.hunterhope.twsedbsave.service.TwseDbSaveService;
import com.hunterhope.twsedbsave.service.TwseDbSaveService.StepListener;
import com.hunterhope.twsedbsave.service.exception.NotMatchDataException;
import com.hunterhope.twsedbsave.service.exception.TwseDbQueryException;
import com.hunterhope.twsedbsave.service.exception.TwseDbSaveException;
import com.hunterhope.twsestocksoftware.data.StockDayInfo;
import com.hunterhope.twsestocksoftware.exception.StockIdNetNoDataException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 *
 * @author user
 */
public class StockDayInfoRepository {

    private final TwseDbQueryService tdqs;
    private final TwseDbSaveService tdss;
    public StockDayInfoRepository() {
        tdqs = new TwseDbQueryService();
        tdss = new TwseDbSaveService();
    }

    public StockDayInfoRepository(TwseDbQueryService tdqs, TwseDbSaveService tdss) {
        this.tdqs = tdqs;
        this.tdss = tdss;
    }
    public List<StockDayInfo> queryAllDayInfo(String stocdId, Consumer<String> updateMsgAction) throws StockIdNetNoDataException {
        try {
            //查詢本地資料庫
            return tdqs.selectAllDayInfo(stocdId, StockDayInfo.class);
        } catch (TwseDbQueryException ex) {
            return noTableAction(stocdId, updateMsgAction);
        }
    }

    private List<StockDayInfo> noTableAction(String stockId, Consumer<String> updateMsgAction) throws StockIdNetNoDataException {
        StepListener si = (StepInfo si1) -> {
            updateMsgAction.accept(si1.getStockId() + " " + si1.getMsg());
        };
        try {
            //加入監聽器
            tdss.addListener(si);
            //上網抓取
            tdss.crawl(stockId, LocalDate.now(), 2);//抓2個月是為了確保個月的第1天碰到交易日貨不是交易日,而沒資料,其實有錢一個月資料
            try {
                return tdqs.selectAllDayInfo(stockId, StockDayInfo.class);
            } catch (TwseDbQueryException exR) {
                //基本上不會有錯誤才對,所以丟出直行錯誤
                throw new RuntimeException(exR);
            }
        } catch (TwseDbSaveException ex1) {
            throw new RuntimeException(ex1);
        } catch (NotMatchDataException ex1) {
            //丟出例外,因為上網抓取沒資料,表示該股票基本上不存在
            throw new StockIdNetNoDataException(stockId);
        } finally {
            //移除監聽器
            tdss.removeListener(si);
        }
    }

    public Map<String, String> queryLatestDayInfo(String stockId) throws StockIdNetNoDataException {
        try {
            return tdss.crawlLatestNoSave(stockId);
        } catch (TwseDbSaveException ex) {
            throw new RuntimeException(ex);
        } catch (NotMatchDataException ex) {
            //丟出例外,因為上網抓取沒資料,表示該股票基本上不存在
            throw new StockIdNetNoDataException(stockId);
        }
    }

}
