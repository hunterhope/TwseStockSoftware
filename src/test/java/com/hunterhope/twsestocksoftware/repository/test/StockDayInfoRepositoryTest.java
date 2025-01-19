/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hunterhope.twsestocksoftware.repository.test;

import com.hunterhope.twsedbsave.service.TwseDbQueryService;
import com.hunterhope.twsedbsave.service.TwseDbSaveService;
import com.hunterhope.twsedbsave.service.exception.NotMatchDataException;
import com.hunterhope.twsedbsave.service.exception.TwseDbQueryException;
import com.hunterhope.twsestocksoftware.data.StockDayInfo;
import com.hunterhope.twsestocksoftware.exception.StockIdNetNoDataException;
import com.hunterhope.twsestocksoftware.repository.StockDayInfoRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Consumer;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 *
 * @author user
 */
public class StockDayInfoRepositoryTest {

    @Test
    public void test_db_has_Data() throws Exception {
        //模擬帶測物件
        TwseDbSaveService tdss = Mockito.mock(TwseDbSaveService.class);
        TwseDbQueryService tdqs = Mockito.mock(TwseDbQueryService.class);

        //模擬依賴行為
        Mockito.when(tdqs.selectAllDayInfo(Mockito.any(), Mockito.any())).thenReturn(List.of(new StockDayInfo()));
        //帶測物件
        StockDayInfoRepository sdir = new StockDayInfoRepository(tdqs, tdss);
        //跑起來
        List<StockDayInfo> result = sdir.queryAllDayInfo("2323", System.out::println);
        //驗證
        Assertions.assertFalse(result.isEmpty(), "本地資料庫沒有資料");
    }

    @Test
    public void test_db_no_data() throws Exception {
        //模擬帶測物件
        TwseDbSaveService tdss = Mockito.spy(new TwseDbSaveService());
        TwseDbQueryService tdqs = Mockito.mock(TwseDbQueryService.class);
        //模擬依賴行為
        Mockito.when(tdqs.selectAllDayInfo(Mockito.any(), Mockito.any())).thenThrow(new TwseDbQueryException("沒有資料表")).thenReturn(List.of(new StockDayInfo()));
        Mockito.doNothing().when(tdss).crawl(Mockito.any(), Mockito.any(), Mockito.anyInt());
        //帶測物件
        StockDayInfoRepository sdir = new StockDayInfoRepository(tdqs, tdss);
        //跑起來
        sdir.queryAllDayInfo("2323", System.out::println);
        //驗證
        Mockito.verify(tdss, Mockito.times(1)).crawl(Mockito.any(), Mockito.any(), Mockito.anyInt());
        Mockito.verify(tdqs, Mockito.times(2)).selectAllDayInfo(Mockito.any(), Mockito.any());
    }

    @Test
    public void test_db_no_data_net_no_data() throws Exception {
        //模擬帶測物件
        TwseDbSaveService tdss = Mockito.spy(new TwseDbSaveService());
        TwseDbQueryService tdqs = Mockito.mock(TwseDbQueryService.class);
        //模擬依賴行為
        Mockito.when(tdqs.selectAllDayInfo(Mockito.any(), Mockito.any())).thenThrow(new TwseDbQueryException("沒有資料表"));
        Mockito.doThrow(new NotMatchDataException("")).when(tdss).crawl(Mockito.any(), Mockito.any(), Mockito.anyInt());
        //帶測物件
        StockDayInfoRepository sdir = new StockDayInfoRepository(tdqs, tdss);
        //跑起來
        try {
            sdir.queryAllDayInfo("2323", System.out::println);
            fail("沒有丟出例外");
        } catch (StockIdNetNoDataException ex) {
            //驗證
            Mockito.verify(tdss, Mockito.times(1)).crawl(Mockito.any(), Mockito.any(), Mockito.anyInt());
            Mockito.verify(tdqs, Mockito.times(1)).selectAllDayInfo(Mockito.any(), Mockito.any());
        }
    }

    /**
     * 此測試會拖慢測試速度,未來可考慮移到其他地方?
     */
    @Test
    public void test_updateMsgAction_has_action() throws Exception {
        //模擬帶測物件
        TwseDbSaveService tdss = Mockito.spy(new TwseDbSaveService());
        TwseDbQueryService tdqs = Mockito.mock(TwseDbQueryService.class);
        Consumer<String> mockUpdateMsgAction = Mockito.mock(Consumer.class);
        //模擬依賴行為
        Mockito.when(tdqs.selectAllDayInfo(Mockito.any(), Mockito.any())).thenThrow(new TwseDbQueryException("沒有資料表")).thenReturn(List.of(new StockDayInfo()));
        Mockito.doNothing().when(mockUpdateMsgAction).accept(Mockito.any());
        //帶測物件
        StockDayInfoRepository sdir = new StockDayInfoRepository(tdqs, tdss);
        //跑起來
        sdir.queryAllDayInfo("2323", mockUpdateMsgAction);
        //驗證
        Mockito.verify(mockUpdateMsgAction, Mockito.atLeast(1)).accept(Mockito.any());
    }
}
