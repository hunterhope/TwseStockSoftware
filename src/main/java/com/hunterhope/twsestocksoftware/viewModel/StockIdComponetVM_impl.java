/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hunterhope.twsestocksoftware.viewModel;

import com.hunterhope.twsestockid.service.TwseStockIdService;
import com.hunterhope.twsestocksoftware.componet.StockIdComponet.StockIdComponetVM;
import java.util.List;
import java.util.concurrent.Executor;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

/**
 *
 * @author user
 */
public class StockIdComponetVM_impl implements StockIdComponetVM {

    private ObjectProperty<ObservableList<String>> suggestions;
    private StringProperty errorMsg;
    private final Executor executor;
    private final TwseStockIdService tsis;
    
    public StockIdComponetVM_impl(Executor executor) {
        this.executor = executor;
        tsis= new TwseStockIdService();
    }

    public StockIdComponetVM_impl(Executor executor, TwseStockIdService tsis) {
        this.executor = executor;
        this.tsis = tsis;
    }
    
    
    
    @Override
    public Task<List<String>> querySuggestions(String inputWord) {
        //產生執行緒任務,此任務不需要回報任何進度
        Task<List<String>> task = new Task<>(){
            @Override
            protected List<String> call() throws Exception {
                //確認使用者輸入不是空白字串
                if(!inputWord.isBlank()){
                    //發出請求
                    return tsis.suggestStockId(inputWord);
                }
                return List.of();
            }

            @Override
            protected void succeeded() {
                //將結果加入suggestions
                suggestions.getValue().clear();
                suggestions.getValue().addAll(getValue());
            }

            @Override
            protected void failed() {
                //將失敗原因通知使用者
                String[] msgs = getException().getMessage().split(" ");
                errorMsg.setValue(msgs.length>1?msgs[1]:msgs[0]);    
                
            }
        };
        
        //執行任務
        executor.execute(task);
        return task;
    }

    @Override
    public ObjectProperty<ObservableList<String>> suggestionsProperty() {
        if (suggestions == null) {
            ObservableList<String> data = FXCollections.observableArrayList();
            data.add("請輸入查詢股票");
            suggestions = new SimpleObjectProperty<>(data);//這個物件裡沒有任何可被觀察的List
        }
        return suggestions;
    }

    @Override
    public String parceInputStockId(String inputStockId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public StringProperty getErrorMsgProperty(){
        if(errorMsg==null){
            errorMsg = new SimpleStringProperty();
        }
        return errorMsg;
    }
}
