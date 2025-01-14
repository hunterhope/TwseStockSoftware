/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hunterhope.twsestocksoftware.viewmodel.test;

import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;

/**
 *
 * @author user
 */
public class InitJavaFxThread {
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
}
