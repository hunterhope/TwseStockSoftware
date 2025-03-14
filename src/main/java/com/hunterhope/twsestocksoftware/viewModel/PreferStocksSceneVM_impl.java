/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hunterhope.twsestocksoftware.viewModel;

import com.hunterhope.twsestocksoftware.data.StockBriefInfo;
import com.hunterhope.twsestocksoftware.scene.PreferStocksScene.PreferStocksSceneVM;
import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

/**
 *
 * @author user
 */
public class PreferStocksSceneVM_impl implements PreferStocksSceneVM {
    private ListProperty<StockBriefInfo> preferStockDataProperty = new SimpleListProperty<>();
    
    @Override
    public ListProperty<StockBriefInfo> preferStocksDataProperty() {
        Platform.runLater(() -> {
            preferStockDataProperty.set(FXCollections.observableArrayList(createTestData("15.7"), createTestData("15.77777777777")));
        });
        new Thread(() -> {
            try {
                Thread.sleep(5000);
                Platform.runLater(() -> {
                    preferStockDataProperty.forEach(e->e.updateDateProperty("114/03/14"));
                    preferStockDataProperty.forEach(e->e.updatePriceProperty("16.7","+1"));
                    preferStockDataProperty.forEach(e->e.updateUpdateProperty(false));
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
    public void addNewPreferStock(String stockId) {
        System.out.println("new stockId="+stockId);
        StockBriefInfo newStock = new StockBriefInfo();
        newStock.setId(stockId);
        newStock.setConcernedTime(1);
        preferStockDataProperty.add(newStock);
    }
}
