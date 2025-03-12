/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hunterhope.twsestocksoftware.componet;

import com.hunterhope.twsestocksoftware.App;
import com.hunterhope.twsestocksoftware.componet.StockIdComponet.StockIdComponetVM;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 *
 * @author user
 */
public class AddNewPreferStockDialog extends Dialog<String> {
    
    public interface OkBtnClick{
        void action(String stockId);
    }
    public AddNewPreferStockDialog(OkBtnClick okbtnClick) {
        setTitle("新增偏好個股");
        ButtonType okBtn = new ButtonType("新增", ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().add(okBtn);
        getDialogPane().getButtonTypes().add(new ButtonType("取消", ButtonData.NO));
        HBox hBox = new HBox();
        hBox.setSpacing(8);
        StockIdComponetVM vm = App.getIocContainer().createStockIdComponetVM();
        StockIdComponet sic = new StockIdComponet(vm);
        getDialogPane().lookupButton(okBtn).disableProperty().bind(vm.disableSearchProperty());
        hBox.getChildren().addAll(sic);
        getDialogPane().setContent(hBox);
        setResultConverter(dialogButton -> {
            if(dialogButton == okBtn){
                okbtnClick.action(sic.getInputStockId());
            }
            return null;
        });
    }
    
}
