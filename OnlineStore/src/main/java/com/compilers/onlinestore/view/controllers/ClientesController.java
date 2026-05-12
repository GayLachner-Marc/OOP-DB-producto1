package com.compilers.onlinestore.view.controllers;

import com.compilers.onlinestore.controller.Controladora;
import com.compilers.onlinestore.model.Clientes.Cliente;
import com.compilers.onlinestore.model.Clientes.ClientePremium;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

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

    private final Controladora controladora =
            new Controladora();

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

        cargarClientes();
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

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "/fxml/nuevo_cliente.fxml"
                            )
                    );

            Parent root = loader.load();

            Stage stage = new Stage();

            stage.setTitle(
                    "Nuevo Cliente"
            );

            stage.initModality(
                    Modality.APPLICATION_MODAL
            );

            stage.setScene(
                    new Scene(root)
            );

            stage.showAndWait();

            cargarClientes();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void editarCliente() {

        Cliente clienteSeleccionado =
                tablaClientes
                        .getSelectionModel()
                        .getSelectedItem();

        if (clienteSeleccionado == null) {
            return;
        }

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "/fxml/editar_cliente.fxml"
                            )
                    );

            Parent root =
                    loader.load();

            Stage stage =
                    new Stage();

            stage.setTitle(
                    "Editar Cliente"
            );

            stage.initModality(
                    Modality.APPLICATION_MODAL
            );

            stage.setScene(
                    new Scene(root)
            );

            stage.showAndWait();

            cargarClientes();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}