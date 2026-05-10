package com.compilers.onlinestore;

import com.compilers.onlinestore.controller.Controladora;
import com.compilers.onlinestore.exceptions.ArticuloNoExisteException;
import com.compilers.onlinestore.exceptions.PedidoYaEnviadoException;
import com.compilers.onlinestore.exceptions.ClienteNoExisteException;
import com.compilers.onlinestore.exceptions.PedidoNoExisteException;
import com.compilers.onlinestore.view.ConsolaView;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class OnlineStore extends Application {
 @Override
    public void start(Stage stage) {

        VBox root = new VBox();

        root.getChildren().add(
            new Label("OnlineStore funcionando con JavaFX 🚀")
        );

        Scene scene = new Scene(root, 500, 300);

        stage.setTitle("OnlineStore");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}