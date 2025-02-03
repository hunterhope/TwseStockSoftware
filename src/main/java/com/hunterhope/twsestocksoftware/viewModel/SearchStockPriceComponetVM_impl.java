/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hunterhope.twsestocksoftware.viewModel;

import com.hunterhope.twsestocksoftware.componet.SearchStockPriceComponet.SearchStockPriceComponetVM;
import com.hunterhope.twsestocksoftware.data.StockDayInfo;
import com.hunterhope.twsestocksoftware.other.HasErrorHandelTask;
import com.hunterhope.twsestocksoftware.repository.StockDayInfoRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import javafx.concurrent.Task;

/**
 *
 * @author user
 */
public class SearchStockPriceComponetVM_impl extends HasErrorMsgVM implements SearchStockPriceComponetVM{
    private final StockDayInfoRepository sdir;
    private final List<StockDayInfo> stockDaysInfo = new ArrayList<>();
    private final Executor executor;
    
    public SearchStockPriceComponetVM_impl(Executor executor) {
        super("查詢股票日交易紀錄發生錯誤");
        sdir = new StockDayInfoRepository();
        this.executor=executor;
    }

    public SearchStockPriceComponetVM_impl(StockDayInfoRepository sdir,Executor executor) {
        super("查詢股票日交易紀錄發生錯誤");
        this.sdir = sdir;
        this.executor=executor;
    }
    
    @Override
    public Task<List<StockDayInfo>> search(String stocdId) {
        //使用其他執行緒執行
        Task<List<StockDayInfo>> task = new HasErrorHandelTask<List<StockDayInfo>>(this::notifyErrorMsg){
            @Override
            protected List<StockDayInfo> call() throws Exception {
                //請資訊庫給資訊
                //最好有一個轉換步驟,不要讓顯示的資料相依於資料表
               return sdir.queryAllDayInfo(stocdId, this::updateMessage);//這邊的this是Task的
            }

            @Override
            protected void succeeded() {
                stockDaysInfo.clear();
                stockDaysInfo.addAll(getValue());
            }
            
        };
        executor.execute(task);
        return task;
    }

    public List<StockDayInfo> getStockDaysInfo() {
        return stockDaysInfo;
    }
    
}
