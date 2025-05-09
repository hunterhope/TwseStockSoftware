/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hunterhope.twsestocksoftware.componet;

import com.hunterhope.twsestocksoftware.viewModel.StockIdComponetVM_impl;
import java.util.List;
import java.util.concurrent.Executor;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

/**
 *
 * @author user
 */
public class StockIdComponet extends HBox {

    public interface StockIdComponetVM {

        public Task<List<String>> querySuggestions(String inputWord);

        public ObjectProperty<ObservableList<String>> suggestionsProperty();

        public String parceInputStockId(String inputStockId);
        
        public ReadOnlyBooleanProperty disableSearchProperty();
        public void enableSearchBtn();

        public String parceInputStockFullName(String string);
    }

    private final ComboBox<String> stockIdComb = new ComboBox<>();;
    private final StockIdComponetVM vm;
    public StockIdComponet(Executor executor) {
        vm = new StockIdComponetVM_impl(executor);
        otherInit();
        
    }
    /**
     * 測試程式邏輯使用,或當你想共用ViewModel時可以使用
     */
    public StockIdComponet(StockIdComponetVM vm){
        this.vm = vm;
        otherInit();
    }
    private void otherInit(){
        //布局
        myLayout();
        //資料綁定
        bindData();
        //事件處裡(事件處理要在資料綁定後面,不然若事件處理是利用監聽器,會造成綁定到錯誤的屬性上)
        //但這順序又會產生一個問題:bindData若一開始有資料,該資料不會觸發事件
        hendleEvent();
    }
    private void myLayout() {
        //建立控制項
        Label stockIdLab = new Label("股票代號");

        //comb讓他可以編輯
        stockIdComb.setEditable(true);
        //加入HBox布局
        getChildren().addAll(stockIdLab, stockIdComb);
        //設定每個子元件間距
        setSpacing(8);
        //設定子元件對齊方式: 水平至中/垂直至中文字線
        setAlignment(Pos.BASELINE_CENTER);
        //設定HBox大小為指定大小 310試出來的大小
        setMaxWidth(270);
        //測試用 看看此元件實際在布局中涵蓋的大小 利用背景色或邊框
//        setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 5; -fx-padding: 10;");
    }

    private void hendleEvent() {
        //當comb文字有改變就對網路發出請求
        stockIdComb.getEditor().textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String os, String ns) {
                //假如輸入的字串不是空字串在發出查詢
                if (stockIdComb.getSelectionModel().isEmpty()) {//空字串邏輯給vm判斷
                    vm.querySuggestions(ns);
                }else{
                    vm.enableSearchBtn();
                }
            }
        });
        //當comb的Items有變化時,重新顯示下拉選單
        stockIdComb.getItems().addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends String> change) {
                if (!change.getList().isEmpty()) {//此判斷是減少無意義更新UI畫面
                    stockIdComb.hide();
                    stockIdComb.show();
                }
            }
        });
        stockIdComb.getEditor().setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent ke) {
                switch (ke.getCode()) {
                    case DELETE:
                    case BACK_SPACE:
                        stockIdComb.setValue("");
                        break;
                    default:
                        break;
                }
            }
        });
        
    }

    private void bindData() {
        //將ViewModel的建議選項屬性綁定給stockIdComb的Items屬性
        if (vm.suggestionsProperty().getValue() == null) {
            throw new RuntimeException("要綁定的資料是空的會造成監聽器失效");
        }
        stockIdComb.itemsProperty().bind(vm.suggestionsProperty());
    }
    
    public String getInputStockId(){
        return vm.parceInputStockId(stockIdComb.getValue());
    }
    
    public String getInputStockFullName(){
        return vm.parceInputStockFullName(stockIdComb.getValue());
    }
    
}
