/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hunterhope.twsestocksoftware.ioc;

import com.hunterhope.twsestocksoftware.componet.SearchStockPriceComponet.SearchStockPriceComponetVM;
import com.hunterhope.twsestocksoftware.componet.StockIdComponet.StockIdComponetVM;
import com.hunterhope.twsestocksoftware.viewModel.SearchStockPriceComponetVM_impl;
import com.hunterhope.twsestocksoftware.viewModel.StockIdComponetVM_impl;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author user
 */
public class IocContainer {
    private final ExecutorService es;
    private SearchStockPriceComponetVM scvm;
    public IocContainer() {
        es = Executors.newFixedThreadPool(3);
    }
    
    
    
    public Executor getExecutor(){
        return es;
    }
    
    public void releaseResource(){
        es.shutdownNow();
    }
    
    public StockIdComponetVM createStockIdComponetVM(){
        return new StockIdComponetVM_impl(es);
    }
    
    public SearchStockPriceComponetVM getSearchComponetVM(){
        if(scvm==null){
            scvm = new SearchStockPriceComponetVM_impl(es);
        }
        return scvm;
    }
}
