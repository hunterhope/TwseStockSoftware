/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hunterhope.twsestocksoftware.viewModel;

import com.hunterhope.twsestockid.service.TwseStockIdService;
import com.hunterhope.twsestocksoftware.componet.StockIdComponet.StockIdComponetVM;
import com.hunterhope.twsestocksoftware.other.HasErrorHandelTask;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

/**
 *
 * @author user
 */
public class StockIdComponetVM_impl extends HasErrorMsgVM implements StockIdComponetVM {

    private ObjectProperty<ObservableList<String>> suggestions;
    private final Executor executor;
    private final TwseStockIdService tsis;
    private BooleanProperty disableSearchProperty;
    public StockIdComponetVM_impl(Executor executor) {
        super("查詢股票ID發生錯誤");
        this.executor = executor;
        tsis = new TwseStockIdService();
    }

    public StockIdComponetVM_impl(Executor executor, TwseStockIdService tsis) {
        super("查詢股票ID發生錯誤");
        this.executor = executor;
        this.tsis = tsis;
    }

    @Override
    public Task<List<String>> querySuggestions(String inputWord) {
        //產生執行緒任務,此任務不需要回報任何進度
        Task<List<String>> suggestionQueryTask  = new HasErrorHandelTask<List<String>>(this::notifyErrorMsg) {
            @Override
            protected List<String> call() throws Exception {
                //確認使用者輸入不是空白字串
                if (!inputWord.isBlank()) {
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
        };

        //執行任務
        executor.execute(suggestionQueryTask);
        //啟動另一個執行續等待剛任務結束,並檢查是否可以查詢該股票
        executor.execute(new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                List<String> result = suggestionQueryTask.get();
                return !findCorrectItems(inputWord, result).isPresent();
            }

            @Override
            protected void succeeded() {
                disableSearchProperty.set(getValue());
            }

            @Override
            protected void failed() {
                super.failed(); 
                getException().printStackTrace();
            }
            
            
        });
        
        return suggestionQueryTask;
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
        Optional<String> opt = findCorrectItems(inputStockId,suggestions.getValue());
        if (opt.isPresent()) {
            return opt.get().split(" ")[0];
        }
        return inputStockId;
    }

    private Optional<String> findCorrectItems(String inputStockId,List<String> suggestionsData) {
        //找出正確的選項
        return suggestionsData.stream()
                .filter(e -> {
                    if (e.equals(inputStockId)) {
                        return true;
                    }
                    String[] split = e.split(" ");
                    if (split[0].equals(inputStockId)) {
                        return true;
                    }
                    return split.length > 1 ? split[1].equals(inputStockId) : false;
                })
                .findFirst();
    }

    @Override
    public ReadOnlyBooleanProperty disableSearchProperty() {
        if(disableSearchProperty==null){
            disableSearchProperty=new SimpleBooleanProperty(true);
        }
        return disableSearchProperty;
    }

    @Override
    public void enableSearchBtn() {
        disableSearchProperty.set(false);
    }
}
