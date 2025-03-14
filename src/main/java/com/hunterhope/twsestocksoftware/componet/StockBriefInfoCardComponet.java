/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hunterhope.twsestocksoftware.componet;

import com.hunterhope.twsestocksoftware.data.StockBriefInfo;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

/**
 *
 * @author user
 */
public class StockBriefInfoCardComponet extends HBox {

    private final CornerRadii cr = new CornerRadii(5, 5, 5, 5, false);
    private final Insets insets8 = new Insets(8, 8, 8, 8);
    private final Background mouseEnteredBg = new Background(new BackgroundFill(Color.web("#dbdad7"), cr, Insets.EMPTY));
    private final Background mouseExitedBg = new Background(new BackgroundFill(Color.WHITE, cr, Insets.EMPTY));

    public StockBriefInfoCardComponet() {
        createAddNewShape();
        appearanceSetting();
        mouseEnteredExitedEventSetting();
    }

    public StockBriefInfoCardComponet(StockBriefInfo info) {
        VBox vBox = new VBox(info.fullNameText(), info.dateText(), info.priceText(), info.conceredTimeText(),info.updateProgressBar());
        vBox.setPadding(insets8);
        vBox.setSpacing(8);
        getChildren().add(vBox);
        appearanceSetting();
        mouseEnteredExitedEventSetting();
    }

    private void appearanceSetting() {
        setBackground(mouseExitedBg);
        setAlignment(Pos.CENTER);
        setEffect(new DropShadow());
    }

    private void mouseEnteredExitedEventSetting() {
        setOnMouseEntered((MouseEvent t) -> {
            setBackground(mouseEnteredBg);
        });
        setOnMouseExited((MouseEvent t) -> {
            setBackground(mouseExitedBg);
        });
    }

    private void createAddNewShape() {
        StackPane stackPane = new StackPane();
        stackPane.setPadding(insets8);
        //繪製圓圈
        Circle circle = new Circle(30);
        circle.setFill(Color.TRANSPARENT);
        circle.setStrokeWidth(2);
        circle.setStroke(Color.GRAY);
        circle.setOpacity(0.5);
        stackPane.getChildren().add(circle);
        //繪製十字
        Line line1 = new Line();
        line1.setStroke(Color.GRAY);
        line1.setStrokeWidth(2);
        line1.setOpacity(0.5);
        line1.setStartX(0);
        line1.setStartY(0);
        line1.setEndX(0);
        line1.setEndY(30);
        stackPane.getChildren().add(line1);
        Line line2 = new Line();
        line2.setStroke(Color.GRAY);
        line2.setStrokeWidth(2);
        line2.setOpacity(0.5);
        line2.setStartX(0);
        line2.setStartY(0);
        line2.setEndX(30);
        line2.setEndY(0);
        stackPane.getChildren().add(line2);
        getChildren().add(stackPane);
    }
}
