package com.compilers.onlinestore.view.controllers;

import com.compilers.onlinestore.controller.Controladora;
import com.compilers.onlinestore.model.Articulos.Articulo;
import com.compilers.onlinestore.model.Clientes.Cliente;
import com.compilers.onlinestore.model.Pedidos.Pedido;

import java.time.LocalDateTime;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PedidosController {

    private final Controladora controladora = new Controladora();

    private boolean mostrandoEnviados = false;
    private boolean modoEdicion = false;
    private Pedido pedidoEditando = null;

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
    private TableColumn<Pedido, Void> colAcciones;

    @FXML
    private ComboBox<Cliente> comboClientePedido;

    @FXML
    private VBox panelNuevoPedido;

    @FXML
    private VBox cardTablaPedidos;

    @FXML
    private ComboBox<Articulo> comboArticuloPedido;

    @FXML
    private TextField txtCantidadPedido;

    @FXML
    public void initialize() {

        comboClientes.getItems().add("Todos los clientes");
        comboClientes.getSelectionModel().selectFirst();

        comboArticuloPedido.getItems().addAll(
                controladora.listarArticulos()
        );

        comboClientePedido.getItems().addAll(
                controladora.listarClientes()
        );
        comboClientePedido.setEditable(true);
        comboArticuloPedido.setEditable(true);

        comboClientePedido.setConverter(
                new javafx.util.StringConverter<Cliente>() {

            @Override
            public String toString(
                    Cliente cliente
            ) {

                return cliente == null
                        ? ""
                        : cliente.getEmail();
            }

            @Override
            public Cliente fromString(
                    String email
            ) {

                return controladora
                        .buscarCliente(
                                email.trim()
                        );
            }
        });

        comboArticuloPedido.setConverter(
                new javafx.util.StringConverter<Articulo>() {

            @Override
            public String toString(
                    Articulo articulo
            ) {

                return articulo == null
                        ? ""
                        : articulo.getCodigo();
            }

            @Override
            public Articulo fromString(
                    String codigo
            ) {

                return controladora
                        .buscarArticulo(
                                codigo.trim()
                        );
            }
        });

        tablaPedidos.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS
        );

        colNumero.setCellValueFactory(data
                -> new javafx.beans.property.SimpleObjectProperty<>(
                        data.getValue().getNumeroPedido()
                )
        );

        colCliente.setCellValueFactory(data
                -> new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getCliente().getEmail()
                )
        );

        colArticulo.setCellValueFactory(data
                -> new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getArticulo().getCodigo()
                )
        );

        colCantidad.setCellValueFactory(data
                -> new javafx.beans.property.SimpleObjectProperty<>(
                        data.getValue().getCantidad()
                )
        );

        colFecha.setCellValueFactory(data
                -> new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getFechaHora().toString()
                )
        );

        colTotal.setCellValueFactory(data
                -> new javafx.beans.property.SimpleObjectProperty<>(
                        data.getValue().calcularTotal()
                )
        );

        colEstado.setCellValueFactory(data
                -> new javafx.beans.property.SimpleStringProperty(
                        data.getValue().estaEnviado()
                        ? "Enviado"
                        : "Pendiente"
                )
        );

        configurarColumnaAcciones();

        cargarPedidos();
    }

    private void configurarColumnaAcciones() {

        colAcciones.setCellFactory(param
                -> new TableCell<Pedido, Void>() {

            private final Button btnEditar
                    = new Button("✏");

            private final Button btnEliminar
                    = new Button("🗑");

            private final HBox botones
                    = new HBox(
                            10,
                            btnEditar,
                            btnEliminar
                    );

            {
                botones.setStyle(
                        "-fx-alignment: center;"
                );

                btnEditar.setStyle("""
                            -fx-background-color: transparent;
                            -fx-font-size: 16px;
                            -fx-cursor: hand;
                        """);

                btnEliminar.setStyle("""
                            -fx-background-color: transparent;
                            -fx-font-size: 16px;
                            -fx-cursor: hand;
                        """);

                btnEditar.setOnAction(event -> {

                    Pedido pedido
                            = getTableView()
                                    .getItems()
                                    .get(getIndex());

                    if (!pedidoPuedeModificarse(pedido)) {

                        System.out.println(
                                "No se puede editar este pedido"
                        );

                        return;
                    }

                    modoEdicion = true;
                    pedidoEditando = pedido;

                    panelNuevoPedido.setVisible(true);
                    panelNuevoPedido.setManaged(true);

                    cardTablaPedidos.setVisible(false);
                    cardTablaPedidos.setManaged(false);

                    comboClientePedido.setValue(
                            pedido.getCliente()
                    );

                    comboArticuloPedido.setValue(
                            pedido.getArticulo()
                    );

                    comboClientePedido.getEditor().setText(
                            pedido.getCliente()
                                    .getEmail()
                    );

                    comboArticuloPedido.getEditor().setText(
                            pedido.getArticulo()
                                    .getCodigo()
                    );

                    txtCantidadPedido.setText(
                            String.valueOf(
                                    pedido.getCantidad()
                            )
                    );
                });

                btnEliminar.setOnAction(event -> {

                    Pedido pedido
                            = getTableView()
                                    .getItems()
                                    .get(getIndex());

                    if (!pedidoPuedeModificarse(pedido)) {

                        System.out.println(
                                "No se puede eliminar este pedido"
                        );

                        return;
                    }

                    try {

                        controladora.eliminarPedido(
                                pedido.getNumeroPedido()
                        );

                        cargarPedidos();

                    } catch (Exception e) {

                        e.printStackTrace();
                    }
                });
            }

            @Override
            protected void updateItem(
                    Void item,
                    boolean empty
            ) {

                super.updateItem(
                        item,
                        empty
                );

                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(botones);
                }
            }
        }
        );
    }

    private boolean pedidoPuedeModificarse(
            Pedido pedido
    ) {

        if (pedido == null) {
            return false;
        }

        if (pedido.estaEnviado()) {
            return false;
        }

        return pedido.getFechaHora()
                .plusMinutes(
                        pedido.getArticulo()
                                .getTiempoPreparacion()
                )
                .isAfter(
                        LocalDateTime.now()
                );
    }

    private void cargarPedidos() {

        var pedidos
                = controladora.listarPedidos();

        var filtrados
                = pedidos.stream()
                        .filter(pedido
                                -> mostrandoEnviados
                                ? pedido.estaEnviado()
                                : !pedido.estaEnviado()
                        )
                        .toList();

        tablaPedidos.getItems().setAll(
                filtrados
        );

        if (mostrandoEnviados) {

            lblTituloTabla.setText(
                    "Pedidos Enviados ("
                    + filtrados.size()
                    + ")"
            );

        } else {

            lblTituloTabla.setText(
                    "Pedidos Pendientes de Envío ("
                    + filtrados.size()
                    + ")"
            );
        }
    }

    @FXML
    private void mostrarPendientes() {

        mostrandoEnviados = false;

        btnPendientes.setStyle(
                "-fx-background-color: #2563eb;"
                + "-fx-text-fill: white;"
                + "-fx-font-weight: bold;"
                + "-fx-background-radius: 12;"
                + "-fx-padding: 12 18;"
        );

        btnEnviados.setStyle(
                "-fx-background-color: #eef2f7;"
                + "-fx-text-fill: #334155;"
                + "-fx-font-weight: bold;"
                + "-fx-background-radius: 12;"
                + "-fx-padding: 12 18;"
        );

        cargarPedidos();
    }

    @FXML
    private void mostrarEnviados() {

        mostrandoEnviados = true;

        btnEnviados.setStyle(
                "-fx-background-color: #2563eb;"
                + "-fx-text-fill: white;"
                + "-fx-font-weight: bold;"
                + "-fx-background-radius: 12;"
                + "-fx-padding: 12 18;"
        );

        btnPendientes.setStyle(
                "-fx-background-color: #eef2f7;"
                + "-fx-text-fill: #334155;"
                + "-fx-font-weight: bold;"
                + "-fx-background-radius: 12;"
                + "-fx-padding: 12 18;"
        );

        cargarPedidos();
    }

    @FXML
    private void nuevoPedido() {

        modoEdicion = false;
        pedidoEditando = null;

        panelNuevoPedido.setVisible(true);
        panelNuevoPedido.setManaged(true);

        cardTablaPedidos.setVisible(false);
        cardTablaPedidos.setManaged(false);

        comboClientePedido
                .getSelectionModel()
                .clearSelection();

        comboArticuloPedido
                .getSelectionModel()
                .clearSelection();

        comboClientePedido
                .getEditor()
                .clear();

        comboArticuloPedido
                .getEditor()
                .clear();

        txtCantidadPedido.setText("1");
    }

    @FXML
    private void cancelarNuevoPedido() {

        modoEdicion = false;
        pedidoEditando = null;

        panelNuevoPedido.setVisible(false);
        panelNuevoPedido.setManaged(false);

        cardTablaPedidos.setVisible(true);
        cardTablaPedidos.setManaged(true);
    }

    @FXML
    private void crearPedido() {

        try {

            Cliente cliente =
        comboClientePedido.getValue();

Articulo articulo =
        comboArticuloPedido.getValue();

            int cantidad
                    = Integer.parseInt(
                            txtCantidadPedido
                                    .getText()
                                    .trim()
                    );

            if (cliente == null) {
                System.out.println(
                        "Debe seleccionar un cliente"
                );
                return;
            }

            if (articulo == null) {
                System.out.println(
                        "Debe seleccionar un artículo"
                );
                return;
            }

            if (modoEdicion) {

                if (pedidoEditando == null) {
                    return;
                }

                Pedido pedidoActualizado
                        = new Pedido(
                                pedidoEditando.getNumeroPedido(),
                                cliente,
                                articulo,
                                cantidad
                        );

                controladora.actualizarPedido(
                        pedidoActualizado
                );

            } else {

                int numeroPedido
                        = controladora
                                .listarPedidos()
                                .size() + 1;

                Pedido pedido
                        = new Pedido(
                                numeroPedido,
                                cliente,
                                articulo,
                                cantidad
                        );

                controladora.crearPedido(
                        pedido
                );
            }

            cargarPedidos();

            cancelarNuevoPedido();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
