package com.hunterhope.twsestocksoftware;

import static com.hunterhope.twsestocksoftware.SceneType.Test;
import com.hunterhope.twsestocksoftware.scene.SceneBasicFormBorder;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private static final ExecutorService es = Executors.newFixedThreadPool(1);
    private static Stage mainStage;

    @Override
    public void start(Stage stage) {
        mainStage = stage;
        changeScene(SceneType.Test);
    }

    @Override
    public void stop() throws Exception {
        es.shutdownNow();
    }

    public static void changeScene(SceneType sceneType) {
        Parent root = null;
        switch (sceneType) {
            case Test:
            default:
                root = new SceneBasicFormBorder(es);
                break;
        }
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.setMaximized(true);
        mainStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
