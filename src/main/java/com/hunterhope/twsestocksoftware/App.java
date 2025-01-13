package com.hunterhope.twsestocksoftware;

import com.hunterhope.twsestocksoftware.componet.SearchComponet;
import com.hunterhope.twsestocksoftware.componet.StockIdComponet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {
    private final ExecutorService es = Executors.newFixedThreadPool(1);
    @Override
    public void start(Stage stage) {
        StockIdComponet sic = new StockIdComponet(es);
        SearchComponet sc = new SearchComponet(()->sic.getInputStockId());
        HBox hBox = new HBox(sic,sc);
        hBox.setAlignment(Pos.BASELINE_CENTER);
        VBox root = new VBox(hBox);
        root.setAlignment(Pos.CENTER);
        root.autosize();
        var scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        es.shutdownNow();
    }

    public static void main(String[] args) {
        launch();
    }

}