package com.compilers.onlinestore.view.controllers;

import com.compilers.onlinestore.controller.Controladora;
import com.compilers.onlinestore.model.Articulos.Articulo;
import com.compilers.onlinestore.model.Pedidos.Pedido;

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
    private TableView<Pedido> tablaPedidos;

    @FXML
    private TableColumn<Pedido, Integer> colNumero;

    @FXML
    private TableColumn<Pedido, String> colCliente;

    @FXML
    private TableColumn<Pedido, String> colArticulo;

    @FXML
    private TableColumn<Pedido, Integer> colCantidad;

    @FXML
    private TableColumn<Pedido, String> colFecha;

    @FXML
    private TableColumn<Pedido, Double> colTotal;

    @FXML
    private TableColumn<Pedido, String> colEstado;

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

        // COLUMNAS

        colNumero.setCellValueFactory(data ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        data.getValue().getNumeroPedido()
                )
        );

        colCliente.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue()
                                .getCliente()
                                .getEmail()
                )
        );

        colArticulo.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue()
                                .getArticulo()
                                .getCodigo()
                )
        );

        colCantidad.setCellValueFactory(data ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        data.getValue().getCantidad()
                )
        );

        colFecha.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue()
                                .getFechaHora()
                                .toString()
                )
        );

        colTotal.setCellValueFactory(data ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        data.getValue().calcularTotal()
                )
        );

        colEstado.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().estaEnviado()
                                ? "Enviado"
                                : "Pendiente"
                )
        );

        cargarPedidos();
    }

    // ==========================
    // CARGAR PEDIDOS
    // ==========================

    private void cargarPedidos() {

        var pedidos =
                controladora.listarPedidos();

        tablaPedidos.getItems().setAll(
                pedidos
        );

        lblTituloTabla.setText(
                "Pedidos (" +
                pedidos.size() +
                ")"
        );
    }

    // ==========================
    // BOTONES
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

    try {

        String email =
                txtEmailCliente
                        .getText()
                        .trim();

        Articulo articulo =
                comboArticuloPedido
                        .getValue();

        int cantidad =
                Integer.parseInt(
                        txtCantidadPedido
                                .getText()
                                .trim()
                );

        // Buscar cliente por email
        var cliente =
                controladora.buscarCliente(
                        email
                );

        if (cliente == null) {

            System.out.println(
                    "Cliente no encontrado"
            );
            return;
        }

        if (articulo == null) {

            System.out.println(
                    "Debe seleccionar un artículo"
            );
            return;
        }

        // Número pedido automático
        int numeroPedido =
                controladora
                        .listarPedidos()
                        .size() + 1;

        Pedido pedido =
                new Pedido(
                        numeroPedido,
                        cliente,
                        articulo,
                        cantidad
                );

        // GUARDAR EN BD
        controladora.crearPedido(
                pedido
        );

        // Cerrar formulario
        panelNuevoPedido.setVisible(false);
        panelNuevoPedido.setManaged(false);

        cardTablaPedidos.setVisible(true);
        cardTablaPedidos.setManaged(true);

        // Recargar tabla
        cargarPedidos();

        System.out.println(
                "Pedido creado correctamente"
        );

    } catch (Exception e) {

        e.printStackTrace();
    }
}
}