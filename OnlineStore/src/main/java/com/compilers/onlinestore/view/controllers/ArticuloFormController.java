package com.compilers.onlinestore.view.controllers;

import com.compilers.onlinestore.controller.Controladora;
import com.compilers.onlinestore.model.Articulos.Articulo;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ArticuloFormController {

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private TextField txtPrecio;

    @FXML
    private TextField txtEnvio;

    @FXML
    private TextField txtTiempo;

    private final Controladora controladora = new Controladora();

    @FXML
    private void guardarArticulo() {

        try {

            Articulo a = new Articulo(
                    Integer.parseInt(txtId.getText()),
                    Integer.parseInt(txtCodigo.getText()),
                    txtDescripcion.getText(),
                    Double.parseDouble(txtPrecio.getText()),
                    Double.parseDouble(txtEnvio.getText()),
                    Integer.parseInt(txtTiempo.getText())
            );

            controladora.crearArticulo(a);

            cerrarVentana();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cerrarVentana() {

        Stage stage = (Stage) txtCodigo.getScene().getWindow();
        stage.close();
    }

        public void cargarArticulo(Articulo articulo) {
            txtId.setText(String.valueOf(articulo.getId()));
            txtCodigo.setText(String.valueOf(articulo.getCodigo()));
            txtDescripcion.setText(articulo.getDescripcion());
            txtPrecio.setText(String.valueOf(articulo.getPrecioVenta()));
            txtEnvio.setText(String.valueOf(articulo.getGastosEnvio()));
            txtTiempo.setText(String.valueOf(articulo.getTiempoPreparacion()));
        }
    
    void setArticulo(Articulo articulo) {
        cargarArticulo(articulo);
    }
}