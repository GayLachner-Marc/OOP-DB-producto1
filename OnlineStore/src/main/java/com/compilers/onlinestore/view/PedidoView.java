package com.compilers.onlinestore.view;

import com.compilers.onlinestore.controller.Controladora;
import com.compilers.onlinestore.model.Pedidos.Pedido;
import com.compilers.onlinestore.model.Clientes.Cliente;
import com.compilers.onlinestore.model.Articulos.Articulo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.*;

public class PedidoView extends VBox {

    private final Controladora controladora
            = new Controladora();

    private TableView<Pedido> tablaPedidos;

    private ObservableList<Pedido> listaPedidos;

    private VBox panelPedidos;

    public PedidoView() {

        setSpacing(18);

        setPadding(
                new Insets(10, 0, 0, 0)
        );

        crearContenido();
    }

    private void crearContenido() {

        HBox cabecera = new HBox();

        Label titulo = new Label(
                "Gestión de Pedidos"
        );

        titulo.setStyle("""
            -fx-font-size: 28px;
            -fx-font-weight: bold;
            """);

        Region espacioFlexible = new Region();

        HBox.setHgrow(
                espacioFlexible,
                Priority.ALWAYS
        );

        Button btnAgregar = new Button(
                "+ Añadir Pedido"
        );

        btnAgregar.setStyle("""
            -fx-background-color: #2563eb;
            -fx-text-fill: white;
            -fx-font-size: 15px;
            -fx-font-weight: bold;
            -fx-background-radius: 10;
            -fx-padding: 12 22;
            """);

        btnAgregar.setOnAction(e -> {
            mostrarFormularioPedido();
        });

        cabecera.getChildren().addAll(
                titulo,
                espacioFlexible,
                btnAgregar
        );

        panelPedidos = new VBox();

        panelPedidos.setSpacing(20);

        panelPedidos.setPadding(
                new Insets(20)
        );

        panelPedidos.setStyle("""
            -fx-background-color: white;
            -fx-background-radius: 12;
            -fx-border-color: #d1d5db;
            -fx-border-radius: 12;
            """);

        mostrarTablaPedidos();

        getChildren().addAll(
                cabecera,
                panelPedidos
        );
    }

    private void mostrarTablaPedidos() {

        panelPedidos.getChildren().clear();

        tablaPedidos = new TableView<>();

        TableColumn<Pedido, Integer> colNumero
                = new TableColumn<>("NÚMERO");

        colNumero.setCellValueFactory(
                new PropertyValueFactory<>("numero")
        );

        TableColumn<Pedido, String> colCliente
                = new TableColumn<>("CLIENTE");

        colCliente.setCellValueFactory(data ->

                new javafx.beans.property.SimpleStringProperty(
                        data.getValue()
                                .getCliente()
                                .getNombre()
                )
        );

        TableColumn<Pedido, String> colArticulo
                = new TableColumn<>("ARTÍCULO");

        colArticulo.setCellValueFactory(data ->

                new javafx.beans.property.SimpleStringProperty(
                        data.getValue()
                                .getArticulo()
                                .getDescripcion()
                )
        );

        TableColumn<Pedido, Integer> colCantidad
                = new TableColumn<>("CANTIDAD");

        colCantidad.setCellValueFactory(
                new PropertyValueFactory<>("cantidad")
        );

        tablaPedidos.getColumns().addAll(
                colNumero,
                colCliente,
                colArticulo,
                colCantidad
        );

        tablaPedidos.setPrefHeight(550);

        cargarPedidos();

        panelPedidos.getChildren().add(
                tablaPedidos
        );
    }

    private void cargarPedidos() {

        try {

            listaPedidos
                    = FXCollections.observableArrayList(
                            controladora.listarPedidos()
                    );

            tablaPedidos.setItems(
                    listaPedidos
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    private void mostrarFormularioPedido() {

        panelPedidos.getChildren().clear();

        Label tituloFormulario
                = new Label(
                        "Nuevo Pedido"
                );

        tituloFormulario.setStyle("""
            -fx-font-size: 20px;
            -fx-font-weight: bold;
            """);

        GridPane formulario = new GridPane();

        formulario.setHgap(15);
        formulario.setVgap(15);

        TextField txtNumero = new TextField();
        txtNumero.setPromptText("Número pedido");

        ComboBox<Cliente> comboClientes
                = new ComboBox<>();

        comboClientes.setItems(
                FXCollections.observableArrayList(
                        controladora.listarClientes()
                )
        );

        comboClientes.setPromptText(
                "Seleccionar cliente"
        );

        ComboBox<Articulo> comboArticulos
                = new ComboBox<>();

        comboArticulos.setItems(
                FXCollections.observableArrayList(
                        controladora.listarArticulos()
                )
        );

        comboArticulos.setPromptText(
                "Seleccionar artículo"
        );

        TextField txtCantidad = new TextField();

        txtCantidad.setPromptText(
                "Cantidad"
        );

        formulario.add(new Label("Número"), 0, 0);
        formulario.add(txtNumero, 1, 0);

        formulario.add(new Label("Cliente"), 0, 1);
        formulario.add(comboClientes, 1, 1);

        formulario.add(new Label("Artículo"), 0, 2);
        formulario.add(comboArticulos, 1, 2);

        formulario.add(new Label("Cantidad"), 0, 3);
        formulario.add(txtCantidad, 1, 3);

        Button btnCancelar = new Button(
                "Cancelar"
        );

        Button btnGuardar = new Button(
                "Guardar"
        );

        btnGuardar.setStyle("""
            -fx-background-color: #2563eb;
            -fx-text-fill: white;
            -fx-font-weight: bold;
            """);

        btnGuardar.setOnAction(e -> {

            try {

                Pedido pedido = new Pedido(
                        Integer.parseInt(
                                txtNumero.getText()
                        ),
                        comboClientes.getValue(),
                        comboArticulos.getValue(),
                        Integer.parseInt(
                                txtCantidad.getText()
                        )
                );

                controladora.crearPedido(pedido);

                mostrarTablaPedidos();

            } catch (Exception ex) {

                ex.printStackTrace();
            }
        });

        btnCancelar.setOnAction(e -> {
            mostrarTablaPedidos();
        });

        HBox botones = new HBox(10);

        botones.setAlignment(
                Pos.CENTER_RIGHT
        );

        botones.getChildren().addAll(
                btnCancelar,
                btnGuardar
        );

        VBox contenedorFormulario = new VBox(20);

        contenedorFormulario.getChildren().addAll(
                tituloFormulario,
                formulario,
                botones
        );

        panelPedidos.getChildren().add(
                contenedorFormulario
        );
    }
}