package com.compilers.onlinestore.view.controllers;

import com.compilers.onlinestore.controller.Controladora;
import com.compilers.onlinestore.model.Articulos.Articulo;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.Control;

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

    @FXML
    private TableColumn<Articulo, Void> colAcciones;

    @FXML
    private VBox panelNuevoArticulo;

    @FXML
    private VBox cardTablaArticulos;

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
    public void initialize() {

        colCodigo.setCellValueFactory(
                new javafx.scene.control.cell.PropertyValueFactory<>(
                        "codigo"
                )
        );

        colDescripcion.setCellValueFactory(
                new javafx.scene.control.cell.PropertyValueFactory<>(
                        "descripcion"
                )
        );

        colPrecio.setCellValueFactory(
                new javafx.scene.control.cell.PropertyValueFactory<>(
                        "precioVenta"
                )
        );

        colEnvio.setCellValueFactory(
                new javafx.scene.control.cell.PropertyValueFactory<>(
                        "gastosEnvio"
                )
        );

        colTiempo.setCellValueFactory(
                new javafx.scene.control.cell.PropertyValueFactory<>(
                        "tiempoPreparacion"
                )
                
        );
        
        // ALINEACIÓN DE COLUMNAS
        alinearColumnas();

        
        
        // ==========================
        // COLUMNA ACCIONES
        // ==========================
        colAcciones.setCellFactory(param
                -> new TableCell<Articulo, Void>() {

            private final Button btnEditar
                    = new Button("✏");

            private final Button btnEliminar
                    = new Button("🗑");

            private final HBox botones
                    = new HBox(
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

                // BOTÓN EDITAR
                btnEditar.setOnAction(event -> {

                    Articulo articulo
                            = getTableView()
                                    .getItems()
                                    .get(getIndex());

                    panelNuevoArticulo.setVisible(true);
                    panelNuevoArticulo.setManaged(true);

                    cardTablaArticulos.setVisible(false);
                    cardTablaArticulos.setManaged(false);

                    txtCodigo.setText(
                            articulo.getCodigo()
                    );

                    txtDescripcion.setText(
                            articulo.getDescripcion()
                    );

                    txtPrecio.setText(
                            String.valueOf(
                                    articulo.getPrecioVenta()
                            )
                    );

                    txtEnvio.setText(
                            String.valueOf(
                                    articulo.getGastosEnvio()
                            )
                    );

                    txtTiempo.setText(
                            String.valueOf(
                                    articulo.getTiempoPreparacion()
                            )
                    );
                });

                // BOTÓN ELIMINAR
                btnEliminar.setOnAction(event -> {

                    Articulo articulo
                            = getTableView()
                                    .getItems()
                                    .get(getIndex());

                    try {

                        boolean eliminado
                                = controladora
                                        .eliminarArticulo(
                                                articulo.getCodigo()
                                        );

                        if (eliminado) {

                            cargarArticulos();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
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
        }
        );

// ==========================
// ESTILO TABLA
// ==========================

tablaArticulos.setColumnResizePolicy(
        TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS
);

tablaArticulos.setStyle("""
    -fx-background-color: white;
    -fx-control-inner-background: white;
    -fx-border-color: transparent;
    -fx-table-cell-border-color: #eef1f5;
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

            ObservableList<Articulo> listaArticulos
                    = FXCollections.observableArrayList(
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

        panelNuevoArticulo.setVisible(true);
        panelNuevoArticulo.setManaged(true);

        cardTablaArticulos.setVisible(false);
        cardTablaArticulos.setManaged(false);

        txtCodigo.clear();
        txtDescripcion.clear();
        txtPrecio.clear();
        txtEnvio.clear();
        txtTiempo.clear();
    }

    @FXML
    private void cancelarNuevoArticulo() {

        panelNuevoArticulo.setVisible(false);
        panelNuevoArticulo.setManaged(false);

        cardTablaArticulos.setVisible(true);
        cardTablaArticulos.setManaged(true);
    }

    @FXML
    private void guardarArticulo() {

        try {

            Articulo articulo
                    = new Articulo(
                            txtCodigo.getText(),
                            txtDescripcion.getText(),
                            Double.parseDouble(txtPrecio.getText()),
                            Double.parseDouble(txtEnvio.getText()),
                            Integer.parseInt(txtTiempo.getText())
                    );

            controladora.crearArticulo(
                    articulo
            );

            cargarArticulos();

            cancelarNuevoArticulo();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void alinearColumnas() {

    colCodigo.setStyle("-fx-alignment: CENTER-LEFT;");
    colDescripcion.setStyle("-fx-alignment: CENTER-LEFT;");
    colPrecio.setStyle("-fx-alignment: CENTER-LEFT;");
    colEnvio.setStyle("-fx-alignment: CENTER-LEFT;");
    colTiempo.setStyle("-fx-alignment: CENTER-LEFT;");
}

}
