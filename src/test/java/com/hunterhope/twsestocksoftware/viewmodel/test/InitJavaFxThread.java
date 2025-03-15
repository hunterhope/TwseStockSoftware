/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hunterhope.twsestocksoftware.viewmodel.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;
import org.testfx.framework.junit5.Stop;

/**
 * 此類別是給所有測試viewModel繼承,用來確保每次開始測試都有啟動JavaFxThread<br>
 * 因為vm裡面很可能會用property的方式更新資料,而Javafx的property都必須在JavaFxThread上直行
 * @author user
 */
public class InitJavaFxThread {
    protected final ExecutorService executorService = Executors.newFixedThreadPool(1);
    @BeforeAll
    public static void setupJavaFX() {
        try {
            //測試環境會需要啟動JavaFx執行緒
            Platform.startup(() -> {
                System.out.println(" JavaFX Toolkit initialized.");
            });
        } catch (IllegalStateException ex) {
            System.out.println("JavaFX runtime is activity.");
        }
    }
    
    @Stop
    public void stopTest() {
        executorService.shutdownNow();
    }
}
