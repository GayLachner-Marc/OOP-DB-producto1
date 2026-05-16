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
private TextField txtCuotaAnual;

    private final Controladora controladora =
            new Controladora();
    
    private boolean modoEdicion = false;

private Cliente clienteEditando = null;

    @FXML
    public void initialize() {

        colNombre.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getNombre()
                )
        );

        colEmail.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getEmail()
                )
        );

        colDomicilio.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getDomicilio()
                )
        );

        colNif.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getNif()
                )
        );

        // Tipo cliente
        colTipoCliente.setCellValueFactory(data -> {

            String tipo =
                    data.getValue()
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

    boolean premium =
            "PREMIUM".equals(
                    comboTipoCliente.getValue()
            );

    txtCuotaAnual.setVisible(
            premium
    );

    txtCuotaAnual.setManaged(
            premium
    );

    if (!premium) {
        txtCuotaAnual.clear();
    }
});

configurarColumnaAcciones();
        
        

        cargarClientes();
    }
    
    private void configurarColumnaAcciones() {

    colAcciones.setCellFactory(param ->
            new TableCell<Cliente, Void>() {

        private final Button btnEditar =
                new Button("✏");

        private final Button btnEliminar =
                new Button("🗑");

        private final HBox botones =
                new HBox(
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

                Cliente cliente =
                        getTableView()
                                .getItems()
                                .get(getIndex());

                modoEdicion = true;
                clienteEditando = cliente;

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
                }
            });

            btnEliminar.setOnAction(event -> {

                Cliente cliente =
                        getTableView()
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

        ObservableList<Cliente> lista =
                FXCollections.observableArrayList(
                        controladora.listarClientes()
                );

        tablaClientes.setItems(lista);
    }
    
    
    @FXML
private void abrirFormularioCliente() {

    modoEdicion = false;
    clienteEditando = null;

    panelNuevoCliente.setVisible(true);
    panelNuevoCliente.setManaged(true);

    cardTablaClientes.setVisible(false);
    cardTablaClientes.setManaged(false);

    txtNombre.clear();
    txtEmail.clear();
    txtDomicilio.clear();
    txtNif.clear();
    txtCuotaAnual.clear();

    txtEmail.setDisable(false);

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

        String nombre =
                txtNombre.getText().trim();

        String email =
                txtEmail.getText().trim();

        String domicilio =
                txtDomicilio.getText().trim();

        String nif =
                txtNif.getText().trim();

        String tipo =
                comboTipoCliente.getValue();

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

            if (clienteEditando
                    instanceof ClientePremium premium) {

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

                double cuota =
                        Double.parseDouble(
                                txtCuotaAnual
                                        .getText()
                                        .trim()
                        );

                cliente =
                        new ClientePremium(
                                nombre,
                                domicilio,
                                nif,
                                email,
                                cuota
                        );

            } else {

                cliente =
                        new ClienteEstandar(
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




    
}