/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hunterhope.twsestocksoftware.viewmodel.test;

import com.hunterhope.twsestocksoftware.componet.SearchComponet.SearchComponetVM;
import com.hunterhope.twsestocksoftware.viewModel.SearchComponetVM_impl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author user
 */
public class SearchComponetVM_implTest {
    
    @Test
    public void testSearch_DB_no_data_then_show_update_progress_bar(){
    }
    
    @Test
    public void testSearch_DB_has_data(){
        //測試物件
        SearchComponetVM vm = new SearchComponetVM_impl();
        //跑起來
        vm.search("2323");
        //驗證屬性有值
        Assertions.assertFalse(vm.stockDaysInfoProperty().getValue().isEmpty(),"沒有股票交易紀錄");
    }
}
