/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hunterhope.twsestocksoftware.componet;

import javafx.concurrent.Task;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;

/**
 *
 * @author user
 */
public class ProgressBarComponet extends HBox {

    private final ProgressBar pb = new ProgressBar();
    private final Label msg = new Label();

    public ProgressBarComponet(Task<?> task) {
        myLayout();
        bindData(task);
    }

    private void myLayout() {
        getChildren().addAll(pb, msg);
    }

    private void bindData(Task<?> task) {
        visibleProperty().bind(task.runningProperty());
        pb.progressProperty().bind(task.progressProperty());
        msg.textProperty().bind(task.messageProperty());
    }

}
