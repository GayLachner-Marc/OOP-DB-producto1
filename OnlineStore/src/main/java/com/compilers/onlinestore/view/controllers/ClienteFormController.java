package com.compilers.onlinestore.view.controllers;

import com.compilers.onlinestore.controller.Controladora;
import com.compilers.onlinestore.model.Clientes.Cliente;
import com.compilers.onlinestore.model.Clientes.ClienteEstandar;
import com.compilers.onlinestore.model.Clientes.ClientePremium;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
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

    private final Controladora controladora =
            new Controladora();

    @FXML
    public void initialize() {

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

            if (premium) {
                txtCuotaAnual.setText("30");
            } else {
                txtCuotaAnual.clear();
            }
        });
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

            // Validación básica
            if (nombre.isBlank()
                    || email.isBlank()
                    || domicilio.isBlank()
                    || nif.isBlank()
                    || tipo == null) {

                System.out.println(
                        "Todos los campos son obligatorios."
                );

                return;
            }

            Cliente cliente;

            if ("PREMIUM".equals(tipo)) {

                cliente = new ClientePremium(
                        nombre,
                        domicilio,
                        nif,
                        email
                );

            } else {

                cliente = new ClienteEstandar(
                        nombre,
                        domicilio,
                        nif,
                        email
                );
            }

            controladora.crearCliente(
                    cliente
            );

            txtNombre.getScene()
                    .getWindow()
                    .hide();

            System.out.println(
                    "Cliente creado."
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}