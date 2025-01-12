/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hunterhope.twsestocksoftware.componet;

import com.hunterhope.twsestocksoftware.componet.StockIdComponet.StockIdComponetVM;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author user
 */
public class StockIdComponetVM_impl implements StockIdComponetVM {

    private ObjectProperty<ObservableList<String>> suggestions;

    @Override
    public void querySuggestions(String inputWord) {
        switch (inputWord) {
            case "2":
                suggestions.getValue().clear();
                suggestions.getValue().add("2002 中鋼");
                suggestions.getValue().add("2003 中鋼2");
                suggestions.getValue().add("2004 中鋼3");
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

    @Override
    public String parceInputStockId(String inputStockId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
