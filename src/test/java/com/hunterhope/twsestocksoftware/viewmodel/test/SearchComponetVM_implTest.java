/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hunterhope.twsestocksoftware.viewmodel.test;

import com.hunterhope.twsestocksoftware.componet.SearchComponet.SearchComponetVM;
import com.hunterhope.twsestocksoftware.data.StockDayInfo;
import com.hunterhope.twsestocksoftware.repository.StockDayInfoRepository;
import com.hunterhope.twsestocksoftware.utility.ThreadWait;
import com.hunterhope.twsestocksoftware.viewModel.SearchComponetVM_impl;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.testfx.framework.junit5.Stop;

/**
 *
 * @author user
 */
public class SearchComponetVM_implTest extends InitJavaFxThread{
    private ExecutorService es = Executors.newFixedThreadPool(1);
    @Test
    public void testSearch_DB_no_data_then_show_update_progress_bar(){
    }
    
    @Test
    public void testSearch_DB_has_data()throws Exception{
        //模擬依賴物件
        StockDayInfoRepository sdir = Mockito.mock(StockDayInfoRepository.class);
        Mockito.when(sdir.queryAllDayInfo(Mockito.any())).thenReturn(List.of(new StockDayInfo()));
        //測試物件
        SearchComponetVM vm = new SearchComponetVM_impl(sdir,es);
        //跑起來
        vm.search("2323").get();
        ThreadWait.waitforPropertyContentChange();
        //驗證屬性有值
        Assertions.assertFalse(vm.stockDaysInfoProperty().getValue().isEmpty(),"沒有股票交易紀錄");
    }
    @Stop
    public void stopTest() {
        es.shutdownNow();
    }
}
