/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hunterhope.twsestocksoftware.viewModel;

import com.hunterhope.twsestockid.service.TwseStockIdService;
import com.hunterhope.twsestocksoftware.componet.StockIdComponet.StockIdComponetVM;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
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
        tsis = new TwseStockIdService();
    }

    public StockIdComponetVM_impl(Executor executor, TwseStockIdService tsis) {
        this.executor = executor;
        this.tsis = tsis;
    }

    @Override
    public Task<List<String>> querySuggestions(String inputWord) {
        //產生執行緒任務,此任務不需要回報任何進度
        Task<List<String>> task = new Task<>() {
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

            @Override
            protected void failed() {
                //將失敗原因通知使用者
                String[] msgs = getException().getMessage().split(" ");
                errorMsg.setValue(msgs.length > 1 ? msgs[1] : msgs[0]);

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
        ensureSuggestionsHasData(inputStockId);
        Optional<String> opt = findCorrectItems(inputStockId);
        if (opt.isPresent()) {
            return opt.get().split(" ")[0];
        }
        errorMsg.setValue("股票代號不明確或上市無此股票: " + inputStockId);
        return inputStockId;
    }

    private Optional<String> findCorrectItems(String inputStockId) {
        //找出正確的選項
        return suggestions.getValue().stream()
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

    private void ensureSuggestionsHasData(String inputStockId) {
        if (suggestions.getValue().isEmpty() || suggestions.getValue().contains("請輸入查詢股票")) {
            Task<List<String>> task = querySuggestions(inputStockId);
            try {
                task.get();//等待結果完成
            } catch (InterruptedException | ExecutionException ex) {
            }
        }
    }

    @Override
    public StringProperty errorMsgProperty() {
        if (errorMsg == null) {
            errorMsg = new SimpleStringProperty();
        }
        return errorMsg;
    }

    @Override
    public void errorMsgClear() {
        errorMsg.setValue("");
    }
}
