/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hunterhope.twsestocksoftware.viewModel;

import com.hunterhope.twsestocksoftware.data.StockBriefInfo;
import com.hunterhope.twsestocksoftware.other.HasErrorHandelTask;
import com.hunterhope.twsestocksoftware.repository.PreferStockRepository;
import com.hunterhope.twsestocksoftware.scene.PreferStocksScene.PreferStocksSceneVM;
import java.util.Map;
import java.util.concurrent.Executor;
import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;

/**
 *
 * @author user
 */
public class PreferStocksSceneVM_impl extends HasErrorMsgVM implements PreferStocksSceneVM {
    private final ListProperty<StockBriefInfo> preferStockDataProperty = new SimpleListProperty<>();
    private final Executor executor;
    private final PreferStockRepository psr;
    public PreferStocksSceneVM_impl(Executor executor, PreferStockRepository psr) {
        super("新增偏好個股錯誤");
        preferStockDataProperty.set(FXCollections.observableArrayList(createTestData("15.7"), createTestData("15.77777777777")));//測試用
        this.executor = executor;
        this.psr = psr;
    }
    
    @Override
    public ListProperty<StockBriefInfo> preferStocksDataProperty() {
        new Thread(() -> {
            try {
                Thread.sleep(5000);
                Platform.runLater(() -> {
                    preferStockDataProperty.forEach(e->e.updateDateProperty("114/03/14"));
                    preferStockDataProperty.forEach(e->e.updatePriceProperty("16.7","+1"));
                    preferStockDataProperty.forEach(e->e.updateProperty().set(false));
                });
            } catch (InterruptedException ex) {
            }
        }).start();
        return preferStockDataProperty;
    }

    private StockBriefInfo createTestData(String closePrice) {
        StockBriefInfo testData = new StockBriefInfo();
        testData.setClose(closePrice);
        testData.setId("2323");
        testData.setName("中環");
        testData.setConcernedTime(21);
        testData.setDate("114/03/09");
        testData.setPrice_dif("-0.5");

        return testData;
    }

    @Override
    public Task<Map<String,String>> addNewPreferStock(String stockFullName) {
        StockBriefInfo newEmptyCard = createNewEmptyCard(stockFullName);
        if(stockExistPrefer(newEmptyCard.getId())){
            notifyErrorMsg("此股票已在偏好裡");
            return null;
        }
        preferStockDataProperty.add(newEmptyCard);
        //啟動任務上網查詢資料
        Task<Map<String,String>> updateNewCardTask = new HasErrorHandelTask<Map<String, String>>(this::notifyErrorMsg) {
            @Override
            protected Map<String, String> call() throws Exception {
                return psr.searchNetData(stockFullName);
            }

            @Override
            protected void succeeded() {
                Map<String, String> result = getValue();
                newEmptyCard.updateDateProperty(result.get("date"));
                newEmptyCard.updatePriceProperty(result.get("close"),result.get("priceDif"));
            }
        };
        newEmptyCard.updateProperty().bind(updateNewCardTask.runningProperty());
        executor.execute(updateNewCardTask);
        return updateNewCardTask;
    }
    
    private boolean stockExistPrefer(String stockId){
        return preferStockDataProperty.get().stream().anyMatch(e->stockId.equals(e.getId()));
    }
    
    private StockBriefInfo createNewEmptyCard(String stockFullName){
        String[] split = stockFullName.split(" ");
        StockBriefInfo newStock = new StockBriefInfo();
        newStock.setId(split[0]);
        newStock.setName(split[1]);
        newStock.setConcernedTime(1);
        return newStock;
    }
}
