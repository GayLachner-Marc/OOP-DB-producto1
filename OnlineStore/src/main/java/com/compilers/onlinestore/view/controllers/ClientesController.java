package com.compilers.onlinestore.view.controllers;

import com.compilers.onlinestore.controller.Controladora;
import com.compilers.onlinestore.model.Clientes.Cliente;
import com.compilers.onlinestore.model.Clientes.ClientePremium;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import com.compilers.onlinestore.model.Clientes.ClienteEstandar;
import javafx.scene.control.Label;
import javafx.scene.shape.SVGPath;

public class ClientesController {

    @FXML
    private TableView<Cliente> tablaClientes;

    @FXML
    private TableColumn<Cliente, String> colNombre;

    @FXML
    private TableColumn<Cliente, String> colEmail;

    @FXML
    private TableColumn<Cliente, String> colDomicilio;

    @FXML
    private TableColumn<Cliente, String> colNif;

    @FXML
    private TableColumn<Cliente, String> colTipoCliente;

    @FXML
    private TableColumn<Cliente, Double> colCuotaAnual;

    @FXML
    private TableColumn<Cliente, Void> colAcciones;

    @FXML
    private VBox panelNuevoCliente;

    @FXML
    private VBox cardTablaClientes;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtDomicilio;

    @FXML
    private TextField txtNif;

    @FXML
    private ComboBox<String> comboTipoCliente;

    @FXML
    private Button btnTodos;

    @FXML
    private Button btnEstandar;

    @FXML
    private Button btnPremium;

    private String filtroCliente = "TODOS";

    @FXML
    private VBox contenedorCuota;

    @FXML
    private Label lblCuota;

    @FXML
    private Button btnGuardarCliente;

    @FXML
    private TextField txtCuotaAnual;

    private final Controladora controladora
            = new Controladora();

    private boolean modoEdicion = false;

    private Cliente clienteEditando = null;

    @FXML
    public void initialize() {

        colNombre.setCellValueFactory(data
                -> new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getNombre()
                )
        );

        colEmail.setCellValueFactory(data
                -> new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getEmail()
                )
        );

        colDomicilio.setCellValueFactory(data
                -> new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getDomicilio()
                )
        );

        colNif.setCellValueFactory(data
                -> new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getNif()
                )
        );

        // Tipo cliente
        colTipoCliente.setCellValueFactory(data -> {

            String tipo
                    = data.getValue()
                            .getClass()
                            .getSimpleName();

            tipo = tipo.replace(
                    "Cliente", ""
            );

            return new javafx.beans.property.SimpleStringProperty(
                    tipo
            );
        });

        // Cuota anual
        colCuotaAnual.setCellValueFactory(data -> {

            double cuota = 0.0;

            if (data.getValue() instanceof ClientePremium premium) {
                cuota = premium.getCuotaAnual();
            }

            return new javafx.beans.property.SimpleObjectProperty<>(
                    cuota
            );
        });

        //añadido
        comboTipoCliente.getItems().addAll(
                "ESTANDAR",
                "PREMIUM"
        );

        comboTipoCliente.setOnAction(event -> {

            boolean premium
                    = "PREMIUM".equals(
                            comboTipoCliente.getValue()
                    );

            contenedorCuota.setVisible(
                    premium
            );

            contenedorCuota.setManaged(
                    premium
            );

            txtCuotaAnual.setVisible(
                    premium
            );

            txtCuotaAnual.setManaged(
                    premium
            );

            lblCuota.setVisible(
                    premium
            );

            lblCuota.setManaged(
                    premium
            );

            if (premium) {

                txtCuotaAnual.setText("30");

            } else {

                txtCuotaAnual.clear();
            }
        });

        configurarColumnaAcciones();

        mostrarTodosClientes();

        tablaClientes.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS
        );

        colEmail.setStyle(
                "-fx-alignment: CENTER-LEFT;"
        );

        colNombre.setStyle(
                "-fx-alignment: CENTER-LEFT;"
        );

        colNif.setStyle(
                "-fx-alignment: CENTER-LEFT;"
        );

        colDomicilio.setStyle(
                "-fx-alignment: CENTER-LEFT;"
        );

        colTipoCliente.setStyle(
                "-fx-alignment: CENTER-LEFT;"
        );

        colAcciones.setStyle(
                "-fx-alignment: CENTER-LEFT;"
        );
        cargarClientes();
    }

    private void configurarColumnaAcciones() {

        colAcciones.setCellFactory(param
            -> new TableCell<Cliente, Void>() {

        // ==========================
        // ICONO SVG EDITAR
        // ==========================
        private final SVGPath iconoEditar
                = new SVGPath();

        {
            iconoEditar.setContent(
                    "M21.174 6.812a1 1 0 0 0-3.986-3.987L3.842 16.174a2 2 0 0 0-.5.83l-1.321 4.352a.5.5 0 0 0 .623.622l4.353-1.32a2 2 0 0 0 .83-.497z "
                    + "M15 5 19 9"
            );

            iconoEditar.setStyle("""
                -fx-fill: transparent;
                -fx-stroke: #2563eb;
                -fx-stroke-width: 2;
            """);

            iconoEditar.setScaleX(0.75);
            iconoEditar.setScaleY(0.75);
        }

        // ==========================
        // ICONO SVG ELIMINAR
        // ==========================
        private final SVGPath iconoEliminar
                = new SVGPath();

        {
            iconoEliminar.setContent(
                    "M10 11V17 "
                    + "M14 11V17 "
                    + "M19 6V20A2 2 0 0 1 17 22H7A2 2 0 0 1 5 20V6 "
                    + "M3 6H21 "
                    + "M8 6V4A2 2 0 0 1 10 2H14A2 2 0 0 1 16 4V6"
            );

            iconoEliminar.setStyle("""
                -fx-fill: transparent;
                -fx-stroke: #ef4444;
                -fx-stroke-width: 2;
            """);

            iconoEliminar.setScaleX(0.75);
            iconoEliminar.setScaleY(0.75);
        }

        // ==========================
        // BOTONES
        // ==========================
        private final Button btnEditar
                = new Button();

        private final Button btnEliminar
                = new Button();

        private final HBox botones
                = new HBox(
                        12,
                        btnEditar,
                        btnEliminar
                );

        {

            btnEditar.setGraphic(
                    iconoEditar
            );

            btnEliminar.setGraphic(
                    iconoEliminar
            );

            botones.setStyle(
                    "-fx-alignment: center-left;"
            );

            btnEditar.setStyle("""
                -fx-background-color: transparent;
                -fx-cursor: hand;
                -fx-padding: 0;
            """);

            btnEliminar.setStyle("""
                -fx-background-color: transparent;
                -fx-cursor: hand;
                -fx-padding: 0;
            """);

                // ==================
                // EDITAR
                // ==================
                btnEditar.setOnAction(event -> {

                    Cliente cliente
                            = getTableView()
                                    .getItems()
                                    .get(getIndex());

                    modoEdicion = true;
                    clienteEditando = cliente;

                    btnGuardarCliente.setText(
                            "Actualizar Cliente"
                    );

                    panelNuevoCliente.setVisible(true);
                    panelNuevoCliente.setManaged(true);

                    cardTablaClientes.setVisible(false);
                    cardTablaClientes.setManaged(false);

                    txtNombre.setText(
                            cliente.getNombre()
                    );

                    txtEmail.setText(
                            cliente.getEmail()
                    );

                    txtDomicilio.setText(
                            cliente.getDomicilio()
                    );

                    txtNif.setText(
                            cliente.getNif()
                    );

                    txtEmail.setDisable(true);

                    if (cliente instanceof ClientePremium premium) {

                        comboTipoCliente.setValue(
                                "PREMIUM"
                        );

                        txtCuotaAnual.setVisible(true);
                        txtCuotaAnual.setManaged(true);

                        lblCuota.setVisible(true);
                        lblCuota.setManaged(true);

                        txtCuotaAnual.setText(
                                String.valueOf(
                                        premium.getCuotaAnual()
                                )
                        );

                    } else {

                        comboTipoCliente.setValue(
                                "ESTANDAR"
                        );

                        txtCuotaAnual.clear();

                        txtCuotaAnual.setVisible(false);
                        txtCuotaAnual.setManaged(false);

                        lblCuota.setVisible(false);
                        lblCuota.setManaged(false);
                    }
                });

                // ==================
                // ELIMINAR
                // ==================
                btnEliminar.setOnAction(event -> {

                    Cliente cliente
                            = getTableView()
                                    .getItems()
                                    .get(getIndex());

                    try {

                        controladora.eliminarCliente(
                                cliente.getEmail()
                        );

                        cargarClientes();

                    } catch (Exception e) {

                        System.out.println(
                                e.getMessage()
                        );
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
        });
    }

    private void cargarClientes() {

        ObservableList<Cliente> lista
                = FXCollections.observableArrayList(
                        controladora.listarClientes()
                );

        var filtrados = lista.stream()
                .filter(cliente -> {

                    switch (filtroCliente) {

                        case "ESTANDAR":
                            return cliente instanceof ClienteEstandar;

                        case "PREMIUM":
                            return cliente instanceof ClientePremium;

                        default:
                            return true;
                    }
                })
                .toList();

        tablaClientes.getItems().setAll(
                filtrados
        );
    }

    @FXML
    private void abrirFormularioCliente() {

        modoEdicion = false;
        clienteEditando = null;

        btnGuardarCliente.setText(
                "Guardar Cliente"
        );

        panelNuevoCliente.setVisible(true);
        panelNuevoCliente.setManaged(true);

        cardTablaClientes.setVisible(false);
        cardTablaClientes.setManaged(false);

        txtNombre.clear();
        txtEmail.clear();
        txtDomicilio.clear();
        txtNif.clear();
        txtCuotaAnual.clear();

        comboTipoCliente
                .getSelectionModel()
                .clearSelection();
    }

    @FXML
    private void cancelarNuevoCliente() {

        modoEdicion = false;
        clienteEditando = null;

        panelNuevoCliente.setVisible(false);
        panelNuevoCliente.setManaged(false);

        cardTablaClientes.setVisible(true);
        cardTablaClientes.setManaged(true);

        txtEmail.setDisable(false);
    }

    @FXML
    private void guardarCliente() {

        try {

            String nombre
                    = txtNombre.getText().trim();

            String email
                    = txtEmail.getText().trim();

            String domicilio
                    = txtDomicilio.getText().trim();

            String nif
                    = txtNif.getText().trim();

            String tipo
                    = comboTipoCliente.getValue();

            if (nombre.isBlank()
                    || email.isBlank()
                    || domicilio.isBlank()
                    || nif.isBlank()
                    || tipo == null) {

                System.out.println(
                        "Todos los campos son obligatorios"
                );

                return;
            }

            if (modoEdicion) {

                clienteEditando.setNombre(
                        nombre
                );

                clienteEditando.setDomicilio(
                        domicilio
                );

                clienteEditando.setNif(
                        nif
                );

                if (clienteEditando instanceof ClientePremium premium) {

                    premium.setCuotaAnual(
                            Double.parseDouble(
                                    txtCuotaAnual
                                            .getText()
                                            .trim()
                            )
                    );
                }

                controladora.actualizarCliente(
                        clienteEditando
                );

            } else {

                Cliente cliente;

                if ("PREMIUM".equals(tipo)) {

                    double cuota
                            = Double.parseDouble(
                                    txtCuotaAnual
                                            .getText()
                                            .trim()
                            );

                    cliente
                            = new ClientePremium(
                                    nombre,
                                    domicilio,
                                    nif,
                                    email,
                                    cuota
                            );

                } else {

                    cliente
                            = new ClienteEstandar(
                                    nombre,
                                    domicilio,
                                    nif,
                                    email
                            );
                }

                controladora.crearCliente(
                        cliente
                );
            }

            cargarClientes();

            cancelarNuevoCliente();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @FXML
    private void mostrarTodosClientes() {

        filtroCliente = "TODOS";

        btnTodos.setStyle(
                "-fx-background-color: #2563eb;"
                + "-fx-text-fill: white;"
                + "-fx-font-weight: bold;"
                + "-fx-background-radius: 12;"
                + "-fx-padding: 12 18;"
        );

        btnEstandar.setStyle(
                "-fx-background-color: #eef2f7;"
                + "-fx-text-fill: #334155;"
                + "-fx-font-weight: bold;"
                + "-fx-background-radius: 12;"
                + "-fx-padding: 12 18;"
        );

        btnPremium.setStyle(
                "-fx-background-color: #eef2f7;"
                + "-fx-text-fill: #334155;"
                + "-fx-font-weight: bold;"
                + "-fx-background-radius: 12;"
                + "-fx-padding: 12 18;"
        );

        cargarClientes();
    }

    @FXML
    private void mostrarClientesEstandar() {

        filtroCliente = "ESTANDAR";

        btnEstandar.setStyle(
                "-fx-background-color: #2563eb;"
                + "-fx-text-fill: white;"
                + "-fx-font-weight: bold;"
                + "-fx-background-radius: 12;"
                + "-fx-padding: 12 18;"
        );

        btnTodos.setStyle(
                "-fx-background-color: #eef2f7;"
                + "-fx-text-fill: #334155;"
                + "-fx-font-weight: bold;"
                + "-fx-background-radius: 12;"
                + "-fx-padding: 12 18;"
        );

        btnPremium.setStyle(
                "-fx-background-color: #eef2f7;"
                + "-fx-text-fill: #334155;"
                + "-fx-font-weight: bold;"
                + "-fx-background-radius: 12;"
                + "-fx-padding: 12 18;"
        );

        cargarClientes();
    }

    @FXML
    private void mostrarClientesPremium() {

        filtroCliente = "PREMIUM";

        btnPremium.setStyle(
                "-fx-background-color: #2563eb;"
                + "-fx-text-fill: white;"
                + "-fx-font-weight: bold;"
                + "-fx-background-radius: 12;"
                + "-fx-padding: 12 18;"
        );

        btnTodos.setStyle(
                "-fx-background-color: #eef2f7;"
                + "-fx-text-fill: #334155;"
                + "-fx-font-weight: bold;"
                + "-fx-background-radius: 12;"
                + "-fx-padding: 12 18;"
        );

        btnEstandar.setStyle(
                "-fx-background-color: #eef2f7;"
                + "-fx-text-fill: #334155;"
                + "-fx-font-weight: bold;"
                + "-fx-background-radius: 12;"
                + "-fx-padding: 12 18;"
        );

        cargarClientes();
    }

}
