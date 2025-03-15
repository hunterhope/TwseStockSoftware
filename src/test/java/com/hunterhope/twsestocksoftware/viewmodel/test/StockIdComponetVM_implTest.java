/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hunterhope.twsestocksoftware.viewmodel.test;

import com.hunterhope.twsestockid.exception.TwseStockIdException;
import com.hunterhope.twsestockid.service.TwseStockIdService;
import com.hunterhope.twsestocksoftware.componet.StockIdComponet.StockIdComponetVM;
import com.hunterhope.twsestocksoftware.utility.ThreadWait;
import com.hunterhope.twsestocksoftware.viewModel.StockIdComponetVM_impl;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javafx.concurrent.Task;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 *
 * @author user
 */
public class StockIdComponetVM_implTest extends InitJavaFxThread{


    @Test
    public void test_success_get_net_data() throws Exception {
        //模擬依賴
        TwseStockIdService tsis = Mockito.mock(TwseStockIdService.class);
        //指定查詢答案
        //模擬依賴
        Mockito.when(tsis.suggestStockId(Mockito.any())).thenReturn(List.of("測試資料"));
        //建立代測物件
        StockIdComponetVM vm = new StockIdComponetVM_impl(executorService, tsis);
        //讓viewModel屬性有被綁定呼叫過
        vm.suggestionsProperty();
        //跑起來
        Task<List<String>> task = vm.querySuggestions("測試ID");
        //等待任務執行完畢
        task.get();
        //驗證tsis有被呼叫
        Mockito.verify(tsis, Mockito.times(1)).suggestStockId(Mockito.any());
        ThreadWait.waitforPropertyContentChange();
        //驗證vm的建議選項不是空的
        Assertions.assertTrue(!vm.suggestionsProperty().getValue().isEmpty(), "建議選項是空的");
        //驗證建議選項內容
        Assertions.assertTrue(vm.suggestionsProperty().getValue().contains("測試資料"), "測試資料內容解果不正確");
    }

    @Test
    public void test_fail_get_net_data() throws Exception {
        //模擬依賴
        TwseStockIdService tsis = Mockito.mock(TwseStockIdService.class);
        //指定查詢答案
        //模擬依賴
        Mockito.when(tsis.suggestStockId(Mockito.any())).thenThrow(new TwseStockIdException(new RuntimeException("查詢發生例外")));
        //建立代測物件
        StockIdComponetVM_impl vm = new StockIdComponetVM_impl(executorService, tsis);
        //讓viewModel屬性有被綁定呼叫過
        vm.suggestionsProperty();
        //跑起來
        Task<List<String>> task = vm.querySuggestions("測試ID");
        //等待任務執行完畢
        try {
            task.get();
            fail("沒發生例外");
        } catch (ExecutionException ex) {
            //驗證tsis有被呼叫
            Mockito.verify(tsis, Mockito.times(1)).suggestStockId(Mockito.any());
            ThreadWait.waitforPropertyContentChange();
            //驗證vm的錯誤訊息
            Assertions.assertTrue(vm.errorMsgProperty().getValue().equals("查詢發生例外"), "例外訊息不對:"+vm.errorMsgProperty().getValue());
        }

    }

    @Test
    public void test_input_empty_str_then_return_empty_list() throws Exception {
        //模擬依賴
        TwseStockIdService tsis = Mockito.mock(TwseStockIdService.class);
        //模擬依賴
        Mockito.when(tsis.suggestStockId(Mockito.any())).thenReturn(List.of("測試資料"));
        //建立代測物件
        StockIdComponetVM vm = new StockIdComponetVM_impl(executorService, tsis);
        //讓viewModel屬性有被綁定呼叫過
        vm.suggestionsProperty();
        //跑起來
        Task<List<String>> task = vm.querySuggestions(" ");
        //等待任務執行完畢
        task.get();
        //驗證tsis沒被呼叫
        Mockito.verify(tsis, Mockito.times(0)).suggestStockId(Mockito.any());
        ThreadWait.waitforPropertyContentChange();
        //驗證vm的建議選項是空的
        Assertions.assertTrue(vm.suggestionsProperty().getValue().isEmpty(), "建議選項是空的");
    }


    @Test
    public void test_parceInputStockId_click_items()throws Exception{
        //模擬依賴
        TwseStockIdService tsis = Mockito.mock(TwseStockIdService.class);
        //模擬依賴
        Mockito.when(tsis.suggestStockId(Mockito.any())).thenReturn(List.of("2002 中鋼", "2003 同光", "2004 大鋼", "2005 友力", "2006 東和鋼鐵", "2007 燁興"));
        //建立代測物件
        StockIdComponetVM vm = new StockIdComponetVM_impl(executorService, tsis);
        //讓viewModel屬性有被綁定呼叫過
        vm.suggestionsProperty();
        //讓viewModel有值
        Task<List<String>> task = vm.querySuggestions("2");
        //等待任務執行完畢
        task.get();
        ThreadWait.waitforPropertyContentChange();
        //跑起來
        String result = vm.parceInputStockId("2002 中鋼");
        //驗證
        Assertions.assertTrue(result.equals("2002"),"結果要'2002'但不對: "+result);
        
    }
    
    @Test
    public void test_parceInputStockId_parse_input_2()throws Exception{
        //模擬依賴
        TwseStockIdService tsis = Mockito.mock(TwseStockIdService.class);
        //模擬依賴
        Mockito.when(tsis.suggestStockId(Mockito.any())).thenReturn(List.of("2002 中鋼", "2003 同光", "2004 大鋼", "2005 友力", "2006 東和鋼鐵", "2007 燁興"));
        //建立代測物件
        StockIdComponetVM vm = new StockIdComponetVM_impl(executorService, tsis);
        //讓viewModel屬性有被綁定呼叫過
        vm.suggestionsProperty();
        //讓viewModel有值
        Task<List<String>> task = vm.querySuggestions("2");
        //等待任務執行完畢
        task.get();
        ThreadWait.waitforPropertyContentChange();
        //跑起來
        String result = vm.parceInputStockId("2");
        //驗證
        Assertions.assertTrue(result.equals("2"),"結果要'2'但不對: "+result);
        
    }
    @Test
    public void test_parceInputStockId_parse_input_2002()throws Exception{
        //模擬依賴
        TwseStockIdService tsis = Mockito.mock(TwseStockIdService.class);
        //模擬依賴
        Mockito.when(tsis.suggestStockId(Mockito.any())).thenReturn(List.of("2002 中鋼", "2003 同光", "2004 大鋼", "2005 友力", "2006 東和鋼鐵", "2007 燁興"));
        //建立代測物件
        StockIdComponetVM vm = new StockIdComponetVM_impl(executorService, tsis);
        //讓viewModel屬性有被綁定呼叫過
        vm.suggestionsProperty();
        //讓viewModel有值
        Task<List<String>> task = vm.querySuggestions("2");
        //等待任務執行完畢
        task.get();
        ThreadWait.waitforPropertyContentChange();
        //跑起來
        String result = vm.parceInputStockId("2002");
        //驗證
        Assertions.assertTrue(result.equals("2002"),"結果要'2002'但不對: "+result);
        
    }
    @Test
    public void test_parceInputStockId_parse_input_中鋼()throws Exception{
        //模擬依賴
        TwseStockIdService tsis = Mockito.mock(TwseStockIdService.class);
        //模擬依賴
        Mockito.when(tsis.suggestStockId(Mockito.any())).thenReturn(List.of("2002 中鋼", "2003 同光", "2004 大鋼", "2005 友力", "2006 東和鋼鐵", "2007 燁興"));
        //建立代測物件
        StockIdComponetVM vm = new StockIdComponetVM_impl(executorService, tsis);
        //讓viewModel屬性有被綁定呼叫過
        vm.suggestionsProperty();
        //讓viewModel有值
        Task<List<String>> task = vm.querySuggestions("中鋼");
        //等待任務執行完畢
        task.get();
        ThreadWait.waitforPropertyContentChange();
        //跑起來
        String result = vm.parceInputStockId("中鋼");
        //驗證
        Assertions.assertTrue(result.equals("2002"),"結果要'2002'但不對: "+result);
        
    }
    @Test
    public void test_parceInputStockId_parse_input_2002_but_not_suggestions()throws Exception{
        //模擬依賴
        TwseStockIdService tsis = Mockito.mock(TwseStockIdService.class);
        //模擬依賴
        Mockito.when(tsis.suggestStockId(Mockito.any())).thenReturn(List.of("2002 中鋼", "2003 同光", "2004 大鋼", "2005 友力", "2006 東和鋼鐵", "2007 燁興"));
        //建立代測物件
        StockIdComponetVM vm = new StockIdComponetVM_impl(executorService, tsis);
        //讓viewModel屬性有被綁定呼叫過
        vm.suggestionsProperty();
        //跑起來
        String result = vm.parceInputStockId("2002");
        //驗證
        Assertions.assertTrue(result.equals("2002"),"結果要'2002'但不對: "+result);
        
    }
    @Test
    public void test_parceInputStockFullName_input_2330() throws Exception{
        //模擬依賴
        TwseStockIdService tsis = Mockito.mock(TwseStockIdService.class);
        //模擬依賴
        Mockito.when(tsis.suggestStockId(Mockito.any())).thenReturn(List.of("2002 中鋼", "2003 同光", "2004 大鋼", "2005 友力", "2006 東和鋼鐵", "2007 燁興","2330 台積電"));
        //建立代測物件
        StockIdComponetVM vm = new StockIdComponetVM_impl(executorService, tsis);
        //讓viewModel屬性有被綁定呼叫過
        vm.suggestionsProperty();
        //讓viewModel有值
        Task<List<String>> task = vm.querySuggestions("2330");
        //等待任務執行完畢
        task.get();
        ThreadWait.waitforPropertyContentChange();
        //跑起來
        String result = vm.parceInputStockFullName("2330");
        //驗證
        Assertions.assertTrue(result.equals("2330 台積電"),"結果要'2330 台積電'但不對: "+result);
    }
}
