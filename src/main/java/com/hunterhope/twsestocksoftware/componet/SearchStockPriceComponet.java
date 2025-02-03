/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hunterhope.twsestocksoftware.componet;

import com.hunterhope.twsestocksoftware.App;
import com.hunterhope.twsestocksoftware.SceneType;
import com.hunterhope.twsestocksoftware.data.StockDayInfo;
import com.hunterhope.twsestocksoftware.viewModel.SearchStockPriceComponetVM_impl;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import java.util.function.Supplier;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 *
 * @author user
 */
public class SearchStockPriceComponet extends Button{

    public interface SearchStockPriceComponetVM {
        public Task<List<StockDayInfo>> search(String stocdId);
    }
    
    private final SearchStockPriceComponetVM vm;
    private final Supplier<String> stockIdSupplier;
    private final Consumer<Task<?extends Object>> taskUiAction;
    public SearchStockPriceComponet(Supplier<String> stockIdSupplier,Consumer<Task<?extends Object>> taskUiAction,Executor executor) {
        this.vm=new SearchStockPriceComponetVM_impl(executor);
        this.stockIdSupplier = stockIdSupplier;
        this.taskUiAction=taskUiAction;
        otherInit();
        
    }

    public SearchStockPriceComponet(SearchStockPriceComponetVM vm,Supplier<String> stockIdSupplier,Consumer<Task<?extends Object>> taskUiAction) {
        this.vm = vm;
        this.stockIdSupplier = stockIdSupplier;
        this.taskUiAction=taskUiAction;
        otherInit();
        
    }

    private void myLayout() {
        setText("查詢");
    }

    private void otherInit(){
        myLayout();
        handleEvent();
    }
    
    private void handleEvent() {
        setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent t) {
                //委託給ViewModel
                Task<List<StockDayInfo>> task = vm.search(stockIdSupplier.get());
                taskUiAction.accept(task);
                //改變場景成顯示K線
                App.changeScene(SceneType.IndividualStockK);
            }
        });
    }
    
}
