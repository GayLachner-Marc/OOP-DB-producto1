package com.compilers.onlinestore.view.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class MainController {

    @FXML
    private StackPane contenedorVista;

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
        activarBoton(btnArticulos);
    }

    @FXML
    private void mostrarArticulos() {
        cargarVista("articulos-view.fxml");
        activarBoton(btnArticulos);
    }

    @FXML
    private void mostrarClientes() {
        cargarVista("clientes-view.fxml");
        activarBoton(btnClientes);
    }

    @FXML
private void mostrarPedidos() {
    cargarVista("pedidos-view.fxml");
    activarBoton(btnPedidos);
}

    private void cargarVista(String fxml) {

        try {

            Parent vista = FXMLLoader.load(
                    getClass().getResource("/fxml/" + fxml)
            );

            contenedorVista.getChildren().setAll(vista);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void activarBoton(Button activo) {

        btnArticulos.setStyle(ESTILO_NORMAL);
        btnClientes.setStyle(ESTILO_NORMAL);
        btnPedidos.setStyle(ESTILO_NORMAL);

        activo.setStyle(ESTILO_ACTIVO);
    }
}