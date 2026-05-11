package com.compilers.onlinestore.view.controllers;

import com.compilers.onlinestore.controller.Controladora;
import com.compilers.onlinestore.model.Articulos.Articulo;
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
import javafx.scene.control.TextField;

public class ClienteFormController {
    
    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtDomicilio;

    @FXML
    private TextField txtNif;

    @FXML
    private TextField txtTipoCliente;

    @FXML
    private TextField txtCuotaAnual;

    private final Controladora controladora = new Controladora();

@FXML
    private void guardarCliente() {

        try {

           /* Cliente c = new Cliente(
                    txtNombre.getText(),
                    txtEmail.getText(),
                    txtDomicilio.getText(),
                    txtNif.getText(),
                    txtTipoCliente.getText(),
                    Double.parseDouble(txtCuotaAnual.getText())
            );*/

            controladora.crearCliente(null);//c

            cerrarVentana();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cerrarVentana() {

        Stage stage = (Stage) txtNombre.getScene().getWindow();
        stage.close();
    }
/*
        public void cargarCliente(Cliente cliente) {
            txtNombre.setText(String.valueOf(cliente.getNombre()));
            txtEmail.setText(String.valueOf(cliente.getEmail()));
            txtDomicilio.setText(cliente.getDomicilio());
            txtNif.setText(String.valueOf(cliente.getNif()));
            txtTipoCliente.setText(String.valueOf(cliente.getTipoCliente()));
            txtCuotaAnual.setText(String.valueOf(cliente.getCuotaAnual()));
        }*/

/*
void setCliente(Cliente cliente) {
        cargarCliente(cliente);
    }*/

}