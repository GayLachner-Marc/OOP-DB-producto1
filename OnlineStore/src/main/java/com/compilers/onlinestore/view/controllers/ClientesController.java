package com.compilers.onlinestore.view.controllers;


import com.compilers.onlinestore.controller.Controladora;
import com.compilers.onlinestore.model.Clientes.Cliente;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Parent;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

    private final Controladora controladora = new Controladora();

   @FXML
    private void initialize() {

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
/*
        colTipoCliente.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getTipoCliente()
                )
        );

        colCuotaAnual.setCellValueFactory(data ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        data.getValue().getCuotaAnual()
                )
        );*/
    }

    @FXML
    private void abrirFormularioCliente() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/nuevo_cliente.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Nuevo Cliente");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();

            // Después de cerrar el formulario, recargar la lista de clientes
            cargarClientes();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cargarClientes() {
        ObservableList<Cliente> lista =
                FXCollections.observableArrayList(
                        controladora.listarClientes()
                );

        tablaClientes.setItems(lista);
}

@FXML
private void editarCliente() {
    Cliente clienteSeleccionado = tablaClientes.getSelectionModel().getSelectedItem();

    if (clienteSeleccionado != null) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/editar_cliente.fxml"));
            Parent root = loader.load();

            ClienteFormController controller = loader.getController();
            /*controller.setCliente(clienteSeleccionado);*/

            Stage stage = new Stage();
            stage.setTitle("Editar Cliente");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();

            // Después de cerrar el formulario, recargar la lista de clientes
            cargarClientes();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
}