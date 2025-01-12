package com.hunterhope.twsestocksoftware.componet.test;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
import com.hunterhope.twsestocksoftware.componet.StockIdComponet;
import com.hunterhope.twsestocksoftware.componet.StockIdComponet.StockIdComponetVM;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import static org.testfx.matcher.control.LabeledMatchers.hasText;
import static org.testfx.assertions.api.Assertions.assertThat;

/**
 *
 * @author user
 */
@ExtendWith(ApplicationExtension.class)
public class StockIdComponetTest {

    private StockIdComponetVM spyVm;

    public StockIdComponetTest() {
    }

    @Start
    public void start(Stage stage) {
        StockIdComponetVM_Fake vm = new StockIdComponetVM_Fake();
        spyVm = Mockito.spy(vm);
        //實力化查詢元件
        Parent searchComp = new StockIdComponet(spyVm);
        //實力化場景
        Scene scene = new Scene(searchComp);
        //放入舞台
        stage.setScene(scene);
        //開演
        stage.show();
    }

    /**
     * 測試回傳解果不是空則顯示提示
     */
    @Test
    public void componet_suggestions_not_empty_then_prompt_show(FxRobot robot) {
        //測試物件
        ComboBox cb = combox_input(robot,KeyCode.NUMPAD2);
        //驗證下拉選單有顯示列表
        Assertions.assertTrue(cb.isShowing(), "下拉選單沒有顯示");
    }

    /**
     * 測試回傳解果是空值則提示不顯示
     */
    @Test
    public void componet_suggestions_are_empty_then_prompt_not_show(FxRobot robot) {
        //測試物件
        ComboBox cb = combox_input(robot,KeyCode.NUMPAD3);
        //驗證下拉選單沒有顯示列表
        Assertions.assertFalse(cb.isShowing(), "下拉選單有顯示");
    }

    /**
     * 測試按下提示選項,則不再發出查詢動作
     */
    @Test
    public void componet_prompt_items_click_not_query_suggestions(FxRobot robot) {
        //測試物件
        ComboBox cb = combox_input(robot,KeyCode.NUMPAD2);
        //使用者互動行為
        robot.clickOn(hasText("2002 中鋼"), MouseButton.PRIMARY);
        //驗證下拉選單內容沒有改變,沒有呼叫查詢方法
        Assertions.assertTrue(cb.getItems().contains("2002 中鋼"), "應該包含選項 '2002 中鋼'");
        Mockito.verify(spyVm, Mockito.times(1)).querySuggestions(Mockito.any());
    }
    
    public ComboBox combox_input(FxRobot robot,KeyCode keyCode){
        ComboBox cb = robot.lookup(".combo-box").query();
        //使用者互動行為
        //1.點擊ComboBox使他成為焦點
        robot.clickOn(cb, MouseButton.PRIMARY);
        //2.按下鍵盤數字鍵
        robot.type(keyCode, 1);
        return cb;
    }
    /**
     * 測試按下清除按鍵,可以清空輸入 清除按鍵有2個: DELETE/BACK_SPACE
     */
    @Test
    public void componet_pressed_delete_clear_input(FxRobot robot) {
        ComboBox cb = combox_input(robot,KeyCode.NUMPAD2);
        //按下第一個選項
        robot.clickOn(hasText("2002 中鋼"), MouseButton.PRIMARY);
        combox_input(robot,KeyCode.DELETE);
        //驗證輸入全部清除
        Assertions.assertTrue(cb.getValue().equals(""), "輸入應該清除");

        combox_input(robot, KeyCode.NUMPAD2);
        //按下第一個選項
        robot.clickOn(hasText("2002 中鋼"), MouseButton.PRIMARY);
        combox_input(robot,KeyCode.BACK_SPACE);
        //驗證輸入全部清除
        Assertions.assertTrue(cb.getValue().equals(""), "輸入應該清除");
    }

    @Test
    public void componet_query_suggestion_ocure_exception_has_alert(FxRobot robot) {
        combox_input(robot,KeyCode.NUMPAD4);
        //驗證畫面出現Alert
        Node alert = robot.lookup(".alert").query();
        assertThat(alert).isNotNull();
    }

    @Test
    public void componet_query_suggestion_ocure_thesame_error_msg(FxRobot robot) {
        combox_input(robot,KeyCode.NUMPAD4);
        //驗證畫面出現Alert
        Node alert = robot.lookup(".alert").query();
        assertThat(alert).isNotNull();
        //關閉提示訊息
        robot.clickOn(".button", MouseButton.PRIMARY);
        combox_input(robot,KeyCode.NUMPAD5);
        //第2次驗證
         //驗證畫面出現Alert
        Node alert2 = robot.lookup(".alert").query();
        assertThat(alert2).isNotNull();
    }
}
