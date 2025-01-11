package com.hunterhope.twsestocksoftware.componet.test;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import com.hunterhope.twsestocksoftware.componet.StockIdComponet;
import java.lang.invoke.MethodHandles;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

/**
 *
 * @author user
 */
@ExtendWith(ApplicationExtension.class)
public class StockIdComponetTest {
    
    public StockIdComponetTest() {
    }

    @Start
    public void start(Stage stage){
        //實力化查詢元件
        Parent searchComp = new StockIdComponet();
        //實力化場景
        Scene scene = new Scene(searchComp);
        //放入舞台
        stage.setScene(scene);
        //開演
        stage.show();
    }
    
    @Test
    public void componet_has_suggestion_item_2020中鋼(FxRobot robot){
        //測試物件
        ComboBox cb = robot.lookup(".combo-box").query();
        //使用者互動行為
        //1.點擊ComboBox使他成為焦點
        robot.clickOn(cb, MouseButton.PRIMARY);
        //2.按下鍵盤數字鍵
        robot.type(KeyCode.NUMPAD2,1);
        //驗證下拉選單中會有'2002 中鋼'的其中一個選項
        Assertions.assertTrue(cb.getItems().contains("2002 中鋼"),"應該包含選項 '2002 中鋼'");
    }
    
    @Test
    public void componet_items_has_show(FxRobot robot){
        //測試物件
        ComboBox cb = robot.lookup(".combo-box").query();
        //使用者互動行為
        //1.點擊ComboBox使他成為焦點
        robot.clickOn(cb, MouseButton.PRIMARY);
        //2.按下鍵盤數字鍵
        robot.type(KeyCode.NUMPAD2,1);
        //驗證下拉選單有顯示列表
        Assertions.assertTrue(cb.isShowing(),"下拉選單沒有顯示");
    }
    
}
