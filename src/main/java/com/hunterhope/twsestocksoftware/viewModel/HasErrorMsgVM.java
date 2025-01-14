/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hunterhope.twsestocksoftware.viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author user
 */
public abstract class HasErrorMsgVM {
    protected StringProperty errorMsg;
    
    public StringProperty errorMsgProperty() {
        if (errorMsg == null) {
            errorMsg = new SimpleStringProperty();
        }
        return errorMsg;
    }
    
    public void errorMsgClear() {
        errorMsg.setValue("");
    }
    
    public void notifyErrorMsg(String msg){
        errorMsg.setValue(msg);
    }
}
