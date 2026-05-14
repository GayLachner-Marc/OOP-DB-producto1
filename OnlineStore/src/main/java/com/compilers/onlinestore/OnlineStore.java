package com.compilers.onlinestore;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OnlineStore extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/main-view.fxml")
        );

        Scene scene = new Scene(loader.load());

        scene.getStylesheets().add(
            getClass().
            getResource("/css/styles.css")
            .toExternalForm()
        );

        stage.setTitle("Online Store");
        stage.setWidth(1200);
        stage.setHeight(700);
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}