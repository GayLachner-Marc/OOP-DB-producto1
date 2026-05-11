package com.compilers.onlinestore.view;

import javafx.scene.layout.VBox;

public class ViewManager {

    private final VBox contenedorVista;

    public ViewManager(VBox contenedorVista) {
        this.contenedorVista = contenedorVista;
    }

    public void mostrarArticulos() {

        contenedorVista.getChildren().clear();

        contenedorVista.getChildren().add(
                new ArticuloView()
        );
    }

    public void mostrarClientes() {

        contenedorVista.getChildren().clear();

        // contenedorVista.getChildren().add(
        //         new ClienteView()
        // );
    }

    public void mostrarPedidos() {

        contenedorVista.getChildren().clear();

        // contenedorVista.getChildren().add(
        //         new PedidoView()
        // );
    }
}