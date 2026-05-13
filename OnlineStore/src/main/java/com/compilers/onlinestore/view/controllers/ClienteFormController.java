package com.compilers.onlinestore.view.controllers;

import com.compilers.onlinestore.controller.Controladora;
import com.compilers.onlinestore.model.Clientes.Cliente;
import com.compilers.onlinestore.model.Clientes.ClientePremium;
import com.compilers.onlinestore.model.Clientes.ClienteEstandar;

import javafx.stage.Stage;
import javafx.scene.control.ComboBox;
import javafx.fxml.FXML;
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
    private ComboBox<String> comboTipoCliente;

    @FXML
    private TextField txtCuotaAnual;

    private final Controladora controladora = new Controladora();


@FXML
private void initialize() {

    comboTipoCliente.getItems().addAll(
            "ESTANDAR",
                         "PREMIUM"
    );
}

@FXML
private void guardarCliente() {

    try {

        String nombre = txtNombre.getText();
        String email = txtEmail.getText();
        String domicilio = txtDomicilio.getText();
        String nif = txtNif.getText();

        String tipo = comboTipoCliente.getValue();

        Cliente cliente;

        if ("Premium".equals(tipo)) {

            double cuota = Double.parseDouble(
                    txtCuotaAnual.getText()
            );

            cliente = new ClientePremium(
                    nombre,
                    domicilio,
                    nif,
                    email,
                    cuota
            );

        } else {

            cliente = new ClienteEstandar(
                    nombre,
                    domicilio,
                    nif,
                    email
            );
        }

        controladora.crearCliente(cliente);

        cerrarVentana();

    } catch (Exception e) {
        e.printStackTrace();
    }
}

private void cerrarVentana() {

Stage stage = (Stage) txtNombre.getScene().getWindow();
stage.close();
}


void setCliente(Cliente cliente) {
        cargarCliente(cliente);
    }

public void cargarCliente(Cliente cliente) {

    txtNombre.setText(String.valueOf(cliente.getNombre()));
    txtEmail.setText(String.valueOf(cliente.getEmail()));
    txtDomicilio.setText(cliente.getDomicilio());
    txtNif.setText(String.valueOf(cliente.getNif()));

    comboTipoCliente.setValue(cliente.getTipoCliente());

    txtCuotaAnual.setText(
            String.valueOf(cliente.getCuotaAnual())
    );
}

}
