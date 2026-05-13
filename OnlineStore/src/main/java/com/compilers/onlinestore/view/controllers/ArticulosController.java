package com.compilers.onlinestore.view.controllers;

import java.util.Optional;

import com.compilers.onlinestore.controller.Controladora;
import com.compilers.onlinestore.model.Articulos.Articulo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Parent;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


import java.util.Optional;

public class ArticulosController {

    @FXML
    private TableView<Articulo> tablaArticulos;

    @FXML
    private TableColumn<Articulo, String> colCodigo;

    @FXML
    private TableColumn<Articulo, String> colDescripcion;

    @FXML
    private TableColumn<Articulo, Double> colPrecio;

    @FXML
    private TableColumn<Articulo, Double> colEnvio;

    @FXML
    private TableColumn<Articulo, Integer> colTiempo;

    private final Controladora controladora = new Controladora();

   @FXML
public void initialize() {

    colCodigo.setCellValueFactory(data ->
            new javafx.beans.property.SimpleObjectProperty(
                    data.getValue().getCodigo()
            )
    );

    colDescripcion.setCellValueFactory(data ->
            new javafx.beans.property.SimpleStringProperty(
                    data.getValue().getDescripcion()
            )
    );

    colPrecio.setCellValueFactory(data ->
            new javafx.beans.property.SimpleObjectProperty<>(
                    data.getValue().getPrecioVenta()
            )
    );

    colEnvio.setCellValueFactory(data ->
            new javafx.beans.property.SimpleObjectProperty<>(
                    data.getValue().getGastosEnvio()
            )
    );

    colTiempo.setCellValueFactory(data ->
            new javafx.beans.property.SimpleObjectProperty<>(
                    data.getValue().getTiempoPreparacion()
            )
    );

    cargarArticulos();
}

    private void cargarArticulos() {

        ObservableList<Articulo> lista =
                FXCollections.observableArrayList(
                        controladora.listarArticulos()
                );

        tablaArticulos.setItems(lista);
    }

    @FXML
private void abrirFormularioArticulo() {

    try {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/articulo-form.fxml")
        );

        Parent root = loader.load();

        Stage stage = new Stage();

        stage.setTitle("Añadir Artículo");

        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setScene(new Scene(root));

        stage.showAndWait();

        cargarArticulos();

    } catch (Exception e) {
        e.printStackTrace();
    }
}

@FXML
private void editarArticulo() {

    try {

        Articulo seleccionado =
                tablaArticulos.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            return;
        }

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/articulo-form.fxml")
        );

        Parent root = loader.load();

        ArticuloFormController controller =
                loader.getController();

        controller.setArticulo(seleccionado);

        Stage stage = new Stage();

        stage.setTitle("Editar Artículo");

        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setScene(new Scene(root));

        stage.showAndWait();

        cargarArticulos();

    } catch (Exception e) {
        e.printStackTrace();
    }
}

@FXML
private void eliminarArticulo() {

    try {

        Articulo seleccionado =
                tablaArticulos.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Eliminar artículo");
        alert.setHeaderText("¿Eliminar el artículo seleccionado?");
        alert.setContentText(
                seleccionado.getDescripcion()
        );

        Optional<ButtonType> resultado = alert.showAndWait();

        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                controladora.eliminarArticulo( seleccionado.getCodigo());
                cargarArticulos();
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}

}
