/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hunterhope.twsestocksoftware.componet.test;

import com.hunterhope.twsestocksoftware.componet.StockIdComponet;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author user
 */
public class StockIdComponetVM_Fake implements StockIdComponet.StockIdComponetVM{

    private ObjectProperty<ObservableList<String>> suggestions;

    @Override
    public void querySuggestions(String inputWord) {
        switch (inputWord) {
            case "2":
                suggestions.getValue().clear();
                suggestions.getValue().add("2002 中鋼");
                break;
            case "3":
                suggestions.getValue().clear();
                break;
            default:
                suggestions.getValue().clear();
                suggestions.getValue().add("沒有此建議選項");
                break;
        }
    }

    @Override
    public ObjectProperty<ObservableList<String>> suggestionsProperty() {
        if (suggestions == null) {
            ObservableList<String> data = FXCollections.observableArrayList();
            data.add("初始化選項");
            suggestions = new SimpleObjectProperty<>(data);//這個物件裡沒有任何可被觀察的List
        }
        return suggestions;
    }
    
}
