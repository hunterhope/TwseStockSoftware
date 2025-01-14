/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hunterhope.twsestocksoftware.utility;

import java.util.concurrent.CountDownLatch;
import javafx.application.Platform;

/**
 *
 * @author user
 */
public class ThreadWait {
    public static void waitforPropertyContentChange() throws InterruptedException {
        //確保JavaFx的執行緒任務都結束,在讀取getValue()時,資料才是正確的
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(latch::countDown);
        latch.await();
    }
}
