/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hunterhope.twsestocksoftware.scene;

import com.hunterhope.twsestocksoftware.componet.SearchStockPriceComponet.SearchStockPriceComponetVM;
import com.hunterhope.twsestocksoftware.componet.StockBriefInfoCardComponet;
import com.hunterhope.twsestocksoftware.componet.StockIdComponet.StockIdComponetVM;
import com.hunterhope.twsestocksoftware.data.StockBriefInfo;
import java.util.List;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;

/**
 *
 * @author user
 */
public class PreferStocksScene extends SceneBasicFormBorder{
    private ListProperty<StockBriefInfo> preferData=new SimpleListProperty<>();
    
    public PreferStocksScene(StockIdComponetVM vm, SearchStockPriceComponetVM scvm) {
        super(vm, scvm);
        //設定中間顯示區域
        //建立固定大小的瓦片布局
        TilePane preferStocks = new TilePane(Orientation.HORIZONTAL);
        //設定顯示幾欄
        preferStocks.setPrefColumns(5);
        //內容置中
        preferStocks.setAlignment(Pos.TOP_CENTER);
        //設定邊距
        preferStocks.setPadding(new Insets(8,8,8,8));
        //設定欄間距
        preferStocks.setHgap(12);
        preferStocks.setVgap(12);
        
        preferStocks.getChildren().add(createTestData());
        preferStocks.getChildren().add(new StockBriefInfoCardComponet());
        //放入中間顯示區域
        setCenter(preferStocks);
    }
    
    private StockBriefInfoCardComponet createTestData(){
        StockBriefInfo testData = new StockBriefInfo();
        testData.setClose("15.7");
        testData.setId("2323");
        testData.setName("中環");
        testData.setConcernedTime(21);
        testData.setDate("114/03/09");
        testData.setPrice_dif("-0.5");
        StockBriefInfoCardComponet testComp = new StockBriefInfoCardComponet(testData);
        return testComp;
    }
}
