package com.hunterhope.twsestocksoftware;

import static com.hunterhope.twsestocksoftware.SceneType.Test;
import com.hunterhope.twsestocksoftware.ioc.IocContainer;
import com.hunterhope.twsestocksoftware.scene.PreferStocksScene;
import com.hunterhope.twsestocksoftware.scene.SceneBasicFormBorder;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * JavaFX App
 */
@SpringBootApplication
public class App extends Application {

    private static Stage mainStage;
    private static IocContainer ioc = new IocContainer();
    @Override
    public void start(Stage stage) {
        mainStage = stage;
        changeScene(SceneType.PreferStockList);
        mainStage.setMaximized(true);
        mainStage.show();
    }

    @Override
    public void stop() throws Exception {
        ioc.releaseResource();
    }

    public static void changeScene(SceneType sceneType) {
        SceneBasicFormBorder root = null;
        switch (sceneType) {
            case PreferStockList:
                root = new PreferStocksScene(ioc.createStockIdComponetVM(),ioc.getSearchComponetVM(),ioc.createPreferStocksScene());
                break;
            case Test:
            default:
                root = new SceneBasicFormBorder(ioc.createStockIdComponetVM(),ioc.getSearchComponetVM());
                break;
        }
        Scene scene = new Scene(root);
        double width=0, height=0;
        if(mainStage.isShowing()){
            width=mainStage.getWidth();
            height=mainStage.getHeight();
        }
        mainStage.setScene(scene);
        if(mainStage.isShowing()){
            mainStage.setWidth(width);
            mainStage.setHeight(height);
        }
    }

    public static IocContainer getIocContainer(){
        return ioc;
    }
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        launch();
    }
    
}
