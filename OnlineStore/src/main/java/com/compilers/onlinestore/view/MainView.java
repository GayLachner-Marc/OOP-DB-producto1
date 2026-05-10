package com.compilers.onlinestore.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainView extends Application {

    private BorderPane ventanaPrincipal;
    private HBox menuSuperior;
    private VBox contenidoCentral;

    @Override
    public void start(Stage stage) {

        ventanaPrincipal = new BorderPane();

        crearContenido();

        Scene escena =
                new Scene(ventanaPrincipal, 1400, 800);

        stage.setTitle("Online Store");
        stage.setScene(escena);
        stage.show();
    }

    private void crearContenido() {

        crearMenuSuperior();

        contenidoCentral = new VBox();
        contenidoCentral.setPadding(new Insets(20));

        ventanaPrincipal.setTop(menuSuperior);
        ventanaPrincipal.setCenter(contenidoCentral);
    }

    private void crearMenuSuperior() {

        menuSuperior = new HBox();
        menuSuperior.setSpacing(15);
        menuSuperior.setPadding(new Insets(20));
        menuSuperior.setAlignment(Pos.CENTER_LEFT);

        menuSuperior.setStyle("""
                -fx-background-color: white;
                -fx-border-color: #d1d5db;
                """);

        Button btnArticulos =
                new Button("Gestión de Artículos");

        Button btnClientes =
                new Button("Gestión de Clientes");

        Button btnPedidos =
                new Button("Gestión de Pedidos");

        btnArticulos.setOnAction(e -> {
            mostrarVistaArticulos();
        });

        menuSuperior.getChildren().addAll(
                btnArticulos,
                btnClientes,
                btnPedidos
        );
    }

    private void mostrarVistaArticulos() {

        contenidoCentral.getChildren().clear();

        ArticuloView articuloView =
                new ArticuloView();

        contenidoCentral.getChildren()
                .add(articuloView);
    }

    public static void main(String[] args) {
        launch(args);
    }
}