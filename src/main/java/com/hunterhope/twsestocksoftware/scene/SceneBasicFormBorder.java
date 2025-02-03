/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hunterhope.twsestocksoftware.scene;

import com.hunterhope.twsestocksoftware.componet.ProgressBarComponet;
import com.hunterhope.twsestocksoftware.componet.SearchStockPriceComponet;
import com.hunterhope.twsestocksoftware.componet.SearchStockPriceComponet.SearchStockPriceComponetVM;
import com.hunterhope.twsestocksoftware.componet.StockIdComponet;
import com.hunterhope.twsestocksoftware.componet.StockIdComponet.StockIdComponetVM;
import javafx.concurrent.Task;
import javafx.geometry.Orientation;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 *
 * @author user
 */
public class SceneBasicFormBorder extends BorderPane {

    private final StockIdComponet stockIdComponet;
    private final SearchStockPriceComponet searchComponet;
    private final ToolBar toolbar = new ToolBar();
    private static final HBox statusbar;

    static {
        statusbar = new HBox();
        
        statusbar.getChildren().addAll(new Text("狀態列"), new Separator(Orientation.VERTICAL));
    }

    public SceneBasicFormBorder(StockIdComponetVM vm,SearchStockPriceComponetVM scvm) {

        //設定每個使用這格式的基本元件
        stockIdComponet = new StockIdComponet(vm);
        searchComponet = new SearchStockPriceComponet(scvm,
                stockIdComponet::getInputStockId,
                this::taskProgressView);
        //上方有查詢股票元件
        toolbar.getItems().addAll(stockIdComponet, searchComponet, new Separator());
        setTop(toolbar);
        //中間沒有內容
        //下方有狀態列
        setBottom(statusbar);
    }

    private void taskProgressView(Task<? extends Object> task) {
        //產生一個進度條
        ProgressBarComponet pbc = new ProgressBarComponet(task);
        //加入狀態列
        statusbar.getChildren().add(pbc);
    }
}
