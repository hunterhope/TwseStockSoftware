/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hunterhope.twsestocksoftware.viewmodel.test;

import com.hunterhope.twsestocksoftware.repository.PreferStockRepository;
import com.hunterhope.twsestocksoftware.utility.ThreadWait;
import com.hunterhope.twsestocksoftware.viewModel.PreferStocksSceneVM_impl;
import java.util.HashMap;
import java.util.Map;
import javafx.concurrent.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

/**
 *
 * @author user
 */
public class PreferStocksSceneVM_implTest extends InitJavaFxThread {

    @Test
    public void addNewPreferStock_but_exist_show_alert_msg() throws Exception {
        //準備測試物件
        PreferStocksSceneVM_impl vm = new PreferStocksSceneVM_impl(executorService, null);
        //建立測試資料

        //跑起來
        vm.addNewPreferStock("2323 中環");
        ThreadWait.waitforPropertyContentChange();
        //驗證
        Assertions.assertFalse(vm.errorMsgProperty().get().isEmpty(), "沒有錯誤訊息");
    }

    @Test
    public void addNewPreferStock_2330_successed_add_empty_card() throws Exception {
        //準備測試物件
        PreferStockRepository psr = Mockito.mock(PreferStockRepository.class);
        PreferStocksSceneVM_impl vm = new PreferStocksSceneVM_impl(executorService, psr);
        //建立測試資料

        //跑起來
        vm.addNewPreferStock("2330 台積電");
        ThreadWait.waitforPropertyContentChange();
        //驗證
        Assertions.assertTrue(vm.preferStocksDataProperty().stream().anyMatch(e -> "2330".equals(e.getId())), "沒有成功加入一個2330卡片的初始資料");
    }

    @Test
    public void addNewPreferStock_2330_successed_and_has_update_date()throws Exception{
        //準備測試物件
        PreferStockRepository psr = Mockito.mock(PreferStockRepository.class);
        PreferStocksSceneVM_impl vm = new PreferStocksSceneVM_impl(executorService, psr);
        //模擬依賴
        Mockito.when(psr.searchNetData("2330")).thenReturn(Map.of("name", "台積電"));
        //跑起來
        Task<Map<String, String>> task = vm.addNewPreferStock("2330 台積電");
        Map<String, String> result = task.get();
        //驗證
        Map<String,String> act = new HashMap<>();
        act.put("name", "台積電");
        Assertions.assertEquals(act,result);
    }
}
