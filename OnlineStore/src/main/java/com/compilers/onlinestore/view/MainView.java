package com.compilers.onlinestore.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainView extends Application {

    private VBox zonaSuperior;
    private BorderPane ventanaPrincipal;
    private HBox menuSuperior;
    private VBox contenidoCentral;
    private VBox contenedorVista;

    @Override
    public void start(Stage stage) {

        ventanaPrincipal = new BorderPane();

        crearContenido();

        Scene escena
                = new Scene(ventanaPrincipal, 1400, 800);

        stage.setTitle("Online Store");
        stage.setScene(escena);
        stage.show();
    }

    private VBox crearCabecera() {

        VBox cabeceraApp = new VBox();

        cabeceraApp.setSpacing(5);
        cabeceraApp.setPadding(new Insets(24));

        cabeceraApp.setStyle("""
        -fx-background-color: white;
        """);

        javafx.scene.control.Label tituloApp
                = new javafx.scene.control.Label("Online Store");

        tituloApp.setStyle("""
            -fx-font-size: 32px;
            -fx-font-weight: bold;
            -fx-text-fill: #0f172a;
            """);

        javafx.scene.control.Label subtituloApp
                = new javafx.scene.control.Label(
                        "Sistema de Gestión de Tienda Electrónica"
                );

        subtituloApp.setStyle("""
            -fx-font-size: 18px;
            -fx-text-fill: #64748b;
            """);

        cabeceraApp.getChildren().addAll(
                tituloApp,
                subtituloApp
        );

        return cabeceraApp;
    }

    private void crearContenido() {

        crearMenuSuperior();

        contenidoCentral = new VBox();
        contenidoCentral.setSpacing(20);
        contenidoCentral.setPadding(new Insets(20));

        contenedorVista = new VBox();

        contenidoCentral.getChildren().addAll(
                menuSuperior,
                contenedorVista
        );

        ventanaPrincipal.setTop(crearCabecera());
        ventanaPrincipal.setCenter(contenidoCentral);
    }

    private void crearMenuSuperior() {

        menuSuperior = new HBox();

        menuSuperior.setMaxWidth(Region.USE_PREF_SIZE);
        menuSuperior.setSpacing(10);
        menuSuperior.setPadding(new Insets(10));
        menuSuperior.setAlignment(Pos.CENTER_LEFT);

        menuSuperior.setStyle("""
            -fx-background-color: #e9ecef;
            -fx-background-radius: 18;
            -fx-padding: 4;
            """);

        Button btnArticulos
                = new Button("Gestión de Artículos");

        Button btnClientes
                = new Button("Gestión de Clientes");

        Button btnPedidos
                = new Button("Gestión de Pedidos");

        // TODOS empiezan normales
        aplicarEstiloNormal(btnArticulos);
        aplicarEstiloNormal(btnClientes);
        aplicarEstiloNormal(btnPedidos);

        btnArticulos.setOnAction(e -> {

            aplicarEstiloNormal(btnArticulos);
            aplicarEstiloNormal(btnClientes);
            aplicarEstiloNormal(btnPedidos);

            aplicarEstiloActivo(btnArticulos);

            mostrarVistaArticulos();
        });

        btnClientes.setOnAction(e -> {

            aplicarEstiloNormal(btnArticulos);
            aplicarEstiloNormal(btnClientes);
            aplicarEstiloNormal(btnPedidos);

            aplicarEstiloActivo(btnClientes);

            // mostrarVistaClientes();
        });

        btnPedidos.setOnAction(e -> {

            aplicarEstiloNormal(btnArticulos);
            aplicarEstiloNormal(btnClientes);
            aplicarEstiloNormal(btnPedidos);

            aplicarEstiloActivo(btnPedidos);

            // mostrarVistaPedidos();
        });

        menuSuperior.getChildren().addAll(
                btnArticulos,
                btnClientes,
                btnPedidos
        );
    }

    private void aplicarEstiloNormal(Button boton) {

        boton.setStyle("""
            -fx-background-color: transparent;
            -fx-text-fill: #334155;
            -fx-font-size: 15px;
            -fx-font-weight: normal;
            -fx-border-color: transparent;
            -fx-background-radius: 14;
            -fx-padding: 8 22;
            -fx-cursor: hand;
            -fx-focus-color: transparent;
            -fx-faint-focus-color: transparent;
            """);
    }

    private void aplicarEstiloActivo(Button boton) {

        boton.setStyle("""
            -fx-background-color: white;
            -fx-text-fill: #0f172a;
            -fx-font-size: 15px;
            -fx-font-weight: bold;
            -fx-background-radius: 14;
            -fx-border-radius: 14;
            -fx-border-color: transparent;
            -fx-padding: 8 22;
            -fx-cursor: hand;
            -fx-focus-color: transparent;
            -fx-faint-focus-color: transparent;
            -fx-effect: dropshadow(
                three-pass-box,
                rgba(0,0,0,0.08),
                8,
                0,
                0,
                2
            );
            """);
    }

    private void mostrarVistaArticulos() {

        contenedorVista.getChildren().clear();

        ArticuloView articuloView
                = new ArticuloView();

        contenedorVista.getChildren()
                .add(articuloView);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
