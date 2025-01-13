/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hunterhope.twsestocksoftware.componet;

import java.util.function.Supplier;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 *
 * @author user
 */
public class SearchComponet extends Button{

    public interface SearchComponetVM{
        public void search(String stocdId);
    }
    
    private final SearchComponetVM vm;
    private final Supplier<String> stockIdSupplier;
    public SearchComponet(Supplier<String> stockIdSupplier) {
        this.vm=null;
        this.stockIdSupplier = stockIdSupplier;
        otherInit();
        
    }

    public SearchComponet(SearchComponetVM vm,Supplier<String> stockIdSupplier) {
        this.vm = vm;
        this.stockIdSupplier = stockIdSupplier;
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
                vm.search(stockIdSupplier.get());
            }
        });
    }
    
    
}
