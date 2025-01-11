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
        if(suggestions.getValue()==null){
            suggestions.setValue(FXCollections.observableArrayList());
        }
        switch (inputWord) {
            case "2":
                suggestions.getValue().clear();
                suggestions.getValue().add("2002 中鋼");
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
            suggestions = new SimpleObjectProperty<>();
        }
        return suggestions;
    }

}
