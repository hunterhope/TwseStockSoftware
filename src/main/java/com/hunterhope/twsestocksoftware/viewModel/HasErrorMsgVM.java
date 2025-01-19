/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hunterhope.twsestocksoftware.viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogEvent;

/**
 *
 * @author user
 */
public abstract class HasErrorMsgVM {

    protected StringProperty errorMsg;

    public HasErrorMsgVM(String errorMsgTitle) {
        errorMsg = new SimpleStringProperty("");
        errorMsg.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                if (t1.isBlank()) {
                    return;
                }
                //使用警告通知使用者
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(errorMsgTitle);
                alert.setContentText(t1);
                alert.setOnCloseRequest(new EventHandler<DialogEvent>() {
                    @Override
                    public void handle(DialogEvent t) {
                        errorMsgClear();
                    }
                });
                alert.show();
            }
        });
    }

    public StringProperty errorMsgProperty() {
        return errorMsg;
    }

    public void errorMsgClear() {
        errorMsg.setValue("");
    }

    public void notifyErrorMsg(String msg) {
        errorMsg.setValue(msg);
    }
}
