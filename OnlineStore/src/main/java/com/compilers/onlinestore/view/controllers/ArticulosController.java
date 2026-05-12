package com.compilers.onlinestore.view.controllers;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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

    colCodigo.setCellValueFactory(
            new javafx.scene.control.cell
                    .PropertyValueFactory<>(
                    "codigo"
            )
    );

    colDescripcion.setCellValueFactory(
            new javafx.scene.control.cell
                    .PropertyValueFactory<>(
                    "descripcion"
            )
    );

    colPrecio.setCellValueFactory(
            new javafx.scene.control.cell
                    .PropertyValueFactory<>(
                    "precioVenta"
            )
    );

    colEnvio.setCellValueFactory(
            new javafx.scene.control.cell
                    .PropertyValueFactory<>(
                    "gastosEnvio"
            )
    );

    colTiempo.setCellValueFactory(
            new javafx.scene.control.cell
                    .PropertyValueFactory<>(
                    "tiempoPreparacion"
            )
    );

    // ESTILO TABLA
    tablaArticulos.setColumnResizePolicy(
            TableView.CONSTRAINED_RESIZE_POLICY
    );

    tablaArticulos.setFixedCellSize(52);

    tablaArticulos.setStyle("""
        -fx-background-color: white;
        -fx-control-inner-background: white;
        -fx-border-color: transparent;
        -fx-table-cell-border-color: transparent;
    """);

    tablaArticulos.skinProperty().addListener(
            (obs, oldSkin, newSkin) -> {

                var header = tablaArticulos.lookup(
                        ".column-header-background"
                );

                if (header != null) {

                    header.setStyle("""
                        -fx-background-color: #eef1f5;
                        -fx-border-color: transparent;
                        -fx-background-radius: 12 12 0 0;
                    """);
                }
            }
    );

    cargarArticulos();
}

    private void cargarArticulos() {

    try {

        ObservableList<Articulo> listaArticulos =
                FXCollections.observableArrayList(
                        controladora
                                .listarArticulos()
                );

        tablaArticulos.setItems(
                listaArticulos
        );

    } catch (Exception e) {

        System.out.println(
                "Error cargando artículos"
        );

        e.printStackTrace();
    }
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
private void eliminarArticulo() {

    Articulo articuloSeleccionado =
            tablaArticulos
                    .getSelectionModel()
                    .getSelectedItem();

    if (articuloSeleccionado == null) {
        return;
    }

    try {

        boolean eliminado =
                controladora
                        .eliminarArticulo(
                                articuloSeleccionado
                                        .getCodigo()
                        );

        if (eliminado) {

            cargarArticulos();
        }

    } catch (Exception e) {

        e.printStackTrace();
    }
}

@FXML
private void editarArticulo() { 

    Articulo articuloSeleccionado = tablaArticulos.getSelectionModel().getSelectedItem();

    if (articuloSeleccionado == null) {
        return;
    }

    try {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/articulo-form.fxml")
        );

        Parent root = loader.load();

        ArticuloFormController formController = loader.getController();
        formController.setArticulo(articuloSeleccionado);

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

}
