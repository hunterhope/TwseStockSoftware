/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hunterhope.twsestocksoftware.scene;

import com.hunterhope.twsestocksoftware.componet.AddNewPreferStockDialog;
import com.hunterhope.twsestocksoftware.componet.SearchStockPriceComponet.SearchStockPriceComponetVM;
import com.hunterhope.twsestocksoftware.componet.StockBriefInfoCardComponet;
import com.hunterhope.twsestocksoftware.componet.StockIdComponet.StockIdComponetVM;
import com.hunterhope.twsestocksoftware.data.StockBriefInfo;
import java.util.List;
import java.util.Optional;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;

/**
 *
 * @author user
 */
public class PreferStocksScene extends SceneBasicFormBorder {

    public interface PreferStocksSceneVM {
        ListProperty<StockBriefInfo> preferStocksDataProperty();
    }

    private final ListProperty<StockBriefInfo> preferData = new SimpleListProperty<>();
    private final PreferStocksSceneVM pssvm;
    private TilePane preferStocksPane;
    
    public PreferStocksScene(StockIdComponetVM vm, SearchStockPriceComponetVM scvm, PreferStocksSceneVM pssvm) {
        super(vm, scvm);
        this.pssvm = pssvm;
        myLayout();
        bindData();
        handleEvent();
    }

    private void myLayout() {
        //設定中間顯示區域
        //建立固定大小的瓦片布局
        preferStocksPane = new TilePane(Orientation.HORIZONTAL);
        //內容置中
        preferStocksPane.setAlignment(Pos.TOP_CENTER);
        //設定邊距
        preferStocksPane.setPadding(new Insets(8, 8, 8, 8));
        //設定欄間距
        preferStocksPane.setHgap(12);
        preferStocksPane.setVgap(12);
        preferStocksPane.getChildren().add(new StockBriefInfoCardComponet());
        //放入中間顯示區域
        setCenter(preferStocksPane);
        
    }
    private void bindData(){
        preferData.bind(pssvm.preferStocksDataProperty());
    }
    private void handleEvent(){
        preferData.addListener(new ChangeListener<ObservableList<StockBriefInfo>>(){
            @Override
            public void changed(ObservableValue<? extends ObservableList<StockBriefInfo>> ov, ObservableList<StockBriefInfo> oldv, ObservableList<StockBriefInfo> nv) {
                preferStocksPane.getChildren().clear();
                nv.forEach(e->{
                    StockBriefInfoCardComponet stockCard = new StockBriefInfoCardComponet(e);
                    stockCard.setOnMouseClicked(PreferStocksScene.this::changeToIndividualStockScene);
                    stockCard.setUserData(e.getId());
                    preferStocksPane.getChildren().add(stockCard);
                });
                StockBriefInfoCardComponet addNewCard = new StockBriefInfoCardComponet();
                addNewCard.setOnMouseClicked(PreferStocksScene.this::addNewPreferStock);
                preferStocksPane.getChildren().add(addNewCard);
            }
        });
    }
    private void changeToIndividualStockScene(MouseEvent mouseEvent){
        Object eventSource = mouseEvent.getSource();
        if(eventSource instanceof Node node){
            String id=node.getUserData().toString();
            System.out.println("click id="+id);
        }
    }
    private void addNewPreferStock(MouseEvent mouseEvent){
        new AddNewPreferStockDialog((stockId) -> {
            System.out.println("新增偏好個股:"+stockId);
        } ).showAndWait();
    }
    
}
