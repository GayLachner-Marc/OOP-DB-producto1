package com.compilers.onlinestore.view.controllers;

import com.compilers.onlinestore.controller.Controladora;
import com.compilers.onlinestore.model.Articulos.Articulo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class PedidosController {

    private final Controladora controladora =
            new Controladora();

    @FXML
    private Button btnPendientes;

    @FXML
    private Button btnEnviados;

    @FXML
    private Button btnNuevoPedido;

    @FXML
    private ComboBox<String> comboClientes;

    @FXML
    private Label lblTituloTabla;

    @FXML
    private TableView<?> tablaPedidos;

    @FXML
    private TableColumn<?, ?> colNumero;

    @FXML
    private TableColumn<?, ?> colCliente;

    @FXML
    private TableColumn<?, ?> colArticulo;

    @FXML
    private TableColumn<?, ?> colCantidad;

    @FXML
    private TableColumn<?, ?> colFecha;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colEstado;

    @FXML
    private TableColumn<?, ?> colAcciones;

    // ==========================
    // NUEVO PEDIDO
    // ==========================

    @FXML
    private VBox panelNuevoPedido;
    @FXML
private VBox cardTablaPedidos;

    @FXML
    private TextField txtEmailCliente;

    @FXML
    private ComboBox<Articulo> comboArticuloPedido;

    @FXML
    private TextField txtCantidadPedido;

    // ==========================
    // INIT
    // ==========================

   @FXML
public void initialize() {

    comboClientes.getItems().add(
            "Todos los clientes"
    );

    comboClientes
            .getSelectionModel()
            .selectFirst();

    comboArticuloPedido.getItems().addAll(
            controladora.listarArticulos()
    );

    tablaPedidos.setColumnResizePolicy(
            TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS
    );
}

    // ==========================
    // BOTONES PENDIENTES/ENVIADOS
    // ==========================

    @FXML
    private void mostrarPendientes() {

        btnPendientes.setStyle(
                "-fx-background-color: #2563eb;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 12;" +
                "-fx-padding: 12 18;"
        );

        btnEnviados.setStyle(
                "-fx-background-color: #eef2f7;" +
                "-fx-text-fill: #334155;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 12;" +
                "-fx-padding: 12 18;"
        );

        lblTituloTabla.setText(
                "Pedidos Pendientes de Envío (0)"
        );
    }

    @FXML
    private void mostrarEnviados() {

        btnEnviados.setStyle(
                "-fx-background-color: #2563eb;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 12;" +
                "-fx-padding: 12 18;"
        );

        btnPendientes.setStyle(
                "-fx-background-color: #eef2f7;" +
                "-fx-text-fill: #334155;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 12;" +
                "-fx-padding: 12 18;"
        );

        lblTituloTabla.setText(
                "Pedidos Enviados (0)"
        );
    }

    // ==========================
    // NUEVO PEDIDO
    // ==========================

 @FXML
private void nuevoPedido() {

    panelNuevoPedido.setVisible(true);
    panelNuevoPedido.setManaged(true);

    cardTablaPedidos.setVisible(false);
    cardTablaPedidos.setManaged(false);

    txtEmailCliente.clear();

    comboArticuloPedido
            .getSelectionModel()
            .clearSelection();

    txtCantidadPedido.setText("1");
}

@FXML
private void cancelarNuevoPedido() {

    panelNuevoPedido.setVisible(false);
    panelNuevoPedido.setManaged(false);

    cardTablaPedidos.setVisible(true);
    cardTablaPedidos.setManaged(true);
}

    @FXML
    private void crearPedido() {

        System.out.println(
                "Cliente: "
                + txtEmailCliente.getText()
        );

        System.out.println(
                "Artículo: "
                + comboArticuloPedido.getValue()
        );

        System.out.println(
                "Cantidad: "
                + txtCantidadPedido.getText()
        );

        panelNuevoPedido.setVisible(false);
panelNuevoPedido.setManaged(false);

cardTablaPedidos.setVisible(true);
cardTablaPedidos.setManaged(true);
    }
}