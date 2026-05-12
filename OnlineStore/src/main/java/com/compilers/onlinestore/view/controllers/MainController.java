package com.compilers.onlinestore.view.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class MainController {
    @FXML
private Button btnHome;

    @FXML
    private StackPane contenedorVista;

    @FXML
    private HBox menuSuperior;

    @FXML
    private Button btnArticulos;

    @FXML
    private Button btnClientes;

    @FXML
    private Button btnPedidos;

    private final String ESTILO_ACTIVO = """
        -fx-background-color: white;
        -fx-background-radius: 14;
        -fx-font-size: 15px;
        -fx-font-weight: bold;
        -fx-text-fill: #111827;
        -fx-padding: 14 30;
        -fx-border-color: #e5e7eb;
        -fx-border-radius: 14;
        """;

    private final String ESTILO_NORMAL = """
        -fx-background-color: transparent;
        -fx-font-size: 15px;
        -fx-text-fill: #4b5563;
        -fx-padding: 14 30;
        """;

    @FXML
    public void initialize() {

        // arrancar en HOME
        cargarVista("home-view.fxml");

        // ocultar menú
        ocultarMenu();
    }

    @FXML
    public void mostrarArticulos() {

        mostrarMenu();

        cargarVista("articulos-view.fxml");

        activarBoton(btnArticulos);
    }

    @FXML
    public void mostrarClientes() {

        mostrarMenu();

        cargarVista("clientes-view.fxml");

        activarBoton(btnClientes);
    }

    @FXML
    public void mostrarPedidos() {

        mostrarMenu();

        cargarVista("pedidos-view.fxml");

        activarBoton(btnPedidos);
    }

    private void cargarVista(String fxml) {

        try {

            Parent vista =
                    FXMLLoader.load(
                            getClass().getResource(
                                    "/fxml/" + fxml
                            )
                    );

            contenedorVista
                    .getChildren()
                    .setAll(vista);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

private void activarBoton(Button activo) {

    btnHome.setStyle(ESTILO_NORMAL);
    btnArticulos.setStyle(ESTILO_NORMAL);
    btnClientes.setStyle(ESTILO_NORMAL);
    btnPedidos.setStyle(ESTILO_NORMAL);

    activo.setStyle(ESTILO_ACTIVO);
}

    private void ocultarMenu() {

        menuSuperior.setVisible(false);
        menuSuperior.setManaged(false);
    }

    private void mostrarMenu() {

        menuSuperior.setVisible(true);
        menuSuperior.setManaged(true);
    }
    @FXML
public void mostrarHome() {

    cargarVista("home-view.fxml");

    ocultarMenu();

    btnHome.setStyle(ESTILO_NORMAL);
    btnArticulos.setStyle(ESTILO_NORMAL);
    btnClientes.setStyle(ESTILO_NORMAL);
    btnPedidos.setStyle(ESTILO_NORMAL);
}
}