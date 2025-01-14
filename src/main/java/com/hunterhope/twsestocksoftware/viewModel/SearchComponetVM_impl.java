/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hunterhope.twsestocksoftware.viewModel;

import com.hunterhope.twsestocksoftware.componet.SearchComponet;
import com.hunterhope.twsestocksoftware.componet.SearchComponet.SearchComponetVM;
import com.hunterhope.twsestocksoftware.data.StockDayInfo;
import com.hunterhope.twsestocksoftware.repository.StockDayInfoRepository;
import java.util.List;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.concurrent.Task;

/**
 *
 * @author user
 */
public class SearchComponetVM_impl implements SearchComponetVM{
    private final StockDayInfoRepository sdir;
    private final ListProperty<StockDayInfo> stockDaysInfo = new SimpleListProperty<>();

    public SearchComponetVM_impl() {
        sdir = new StockDayInfoRepository();
    }

    public SearchComponetVM_impl(StockDayInfoRepository sdir) {
        this.sdir = sdir;
    }
    
    @Override
    public void search(String stocdId) {
        //使用其他執行緒執行
        Task<List<StockDayInfo>> task = new Task<>(){
            @Override
            protected List<StockDayInfo> call() throws Exception {
                //請資訊庫給資訊
               return sdir.queryAllDayInfo(stocdId);
            }

            @Override
            protected void succeeded() {
                stockDaysInfo.clear();
                stockDaysInfo.addAll(getValue());
            }
            
        };
        
        
    }

    @Override
    public ListProperty<StockDayInfo> stockDaysInfoProperty() {
        return stockDaysInfo;
    }
    
}
