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
public class PreferStocksSceneVM_impl implements PreferStocksSceneVM{

    @Override
    public ListProperty<StockBriefInfo> preferStocksDataProperty() {
        ListProperty<StockBriefInfo> data = new SimpleListProperty<>();
        Platform.runLater(()->{
            data.set(FXCollections.observableArrayList(createTestData()));
        });
        return data;
    }
    private StockBriefInfo createTestData(){
        StockBriefInfo testData = new StockBriefInfo();
        testData.setClose("15.7");
        testData.setId("2323");
        testData.setName("中環");
        testData.setConcernedTime(21);
        testData.setDate("114/03/09");
        testData.setPrice_dif("-0.5");
        return testData;
    }
}
