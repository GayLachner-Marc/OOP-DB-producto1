package com.compilers.onlinestore.view;

import com.compilers.onlinestore.controller.Controladora;
import com.compilers.onlinestore.model.Articulos.Articulo;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
//import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
//import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ArticuloView extends VBox {

    private final Controladora controladora = new Controladora();
    private TableView<Articulo> tablaArticulos;
    private ObservableList<Articulo> listaArticulos;

    private BorderPane ventanaPrincipal;
    private VBox contenedorPrincipal;
    private VBox panelArticulos;

    public ArticuloView() {

        ventanaPrincipal = new BorderPane();
        ventanaPrincipal.setStyle("-fx-background-color: #f5f6f8;");

        crearContenido();

        getChildren().add(ventanaPrincipal);
    }

    private void crearContenido() {

        contenedorPrincipal = new VBox();
        contenedorPrincipal.setSpacing(18);

        contenedorPrincipal.setPadding(
                new Insets(10, 0, 0, 0)
        );

        HBox cabeceraArticulo = new HBox();

        Label titulo
                = new Label("Gestión de Artículos");

        titulo.setStyle("""
        -fx-font-size: 28px;
        -fx-font-weight: bold;
        """);

        Region espacioFlexible = new Region();
        HBox.setHgrow(
                espacioFlexible,
                Priority.ALWAYS
        );

        Button btnAgregarArt
                = new Button("+ Añadir Artículo");

        btnAgregarArt.setStyle("""
        -fx-background-color: #2563eb;
        -fx-text-fill: white;
        -fx-font-size: 15px;
        -fx-font-weight: bold;
        -fx-background-radius: 10;
        -fx-padding: 12 22;
        """);

        btnAgregarArt.setOnAction(e -> {
            mostrarFormularioArticulo();
        });

        cabeceraArticulo.getChildren().addAll(
                titulo,
                espacioFlexible,
                btnAgregarArt
        );

        panelArticulos = new VBox();

        panelArticulos.setFillWidth(true);
        panelArticulos.setMaxWidth(Double.MAX_VALUE);

        panelArticulos.setPadding(
                new Insets(12, 0, 25, 0)
        );

        panelArticulos.setSpacing(30);

        panelArticulos.setStyle("""
        -fx-background-color: white;
        -fx-background-radius: 12;
        -fx-border-color: #d1d5db;
        -fx-border-radius: 12;
        """);

        mostrarTablaArticulos();

        contenedorPrincipal.getChildren().addAll(
                cabeceraArticulo,
                panelArticulos
        );

        ventanaPrincipal.setCenter(
                contenedorPrincipal
        );
    }

    private void mostrarTablaArticulos() {

        panelArticulos.getChildren().clear();

        Label subtitulo
                = new Label("Artículos");

        subtitulo.setStyle("""
        -fx-font-size: 18px;
        -fx-font-weight: bold;
        """);

        subtitulo.setPadding(
                new Insets(4, 0, -17, 22)
        );

        TableView tablaArticulos
                = new TableView();

        tablaArticulos.setStyle("""
        -fx-background-color: white;
        -fx-control-inner-background: white;
        -fx-table-cell-border-color: transparent;
        -fx-border-color: transparent;
        """);

        TableColumn columnaCodigo
                = new TableColumn("CÓDIGO");

        TableColumn columnaDescripcion
                = new TableColumn("DESCRIPCIÓN");

        TableColumn columnaPrecio
                = new TableColumn("PRECIO");

        TableColumn columnaGastosEnvio
                = new TableColumn("GASTOS ENVÍO");

        TableColumn columnaTiempoPrep
                = new TableColumn("TIEMPO PREPARACIÓN");

        TableColumn columnaAcciones
                = new TableColumn("ACCIONES");

        columnaCodigo.prefWidthProperty()
                .bind(tablaArticulos.widthProperty().multiply(0.10));

        columnaDescripcion.prefWidthProperty()
                .bind(tablaArticulos.widthProperty().multiply(0.28));

        columnaPrecio.prefWidthProperty()
                .bind(tablaArticulos.widthProperty().multiply(0.14));

        columnaGastosEnvio.prefWidthProperty()
                .bind(tablaArticulos.widthProperty().multiply(0.16));

        columnaTiempoPrep.prefWidthProperty()
                .bind(tablaArticulos.widthProperty().multiply(0.18));

        columnaAcciones.prefWidthProperty()
                .bind(tablaArticulos.widthProperty().multiply(0.14));

        tablaArticulos.getColumns().addAll(
                columnaCodigo,
                columnaDescripcion,
                columnaPrecio,
                columnaGastosEnvio,
                columnaTiempoPrep,
                columnaAcciones
        );

        tablaArticulos.setPlaceholder(
                new Label(
                        "No hay artículos registrados"
                )
        );

        // Las columnas ocupan todo el ancho
        tablaArticulos.setColumnResizePolicy(
                TableView.UNCONSTRAINED_RESIZE_POLICY
        );

        // Filas más altas
        tablaArticulos.setFixedCellSize(50);

        tablaArticulos.setPrefHeight(520);

        // Hace que la tabla ocupe todo el ancho
        tablaArticulos.setPrefWidth(
                Double.MAX_VALUE
        );

        tablaArticulos.setMaxWidth(
                Double.MAX_VALUE
        );

        // Hace que crezca dentro del VBox
        VBox.setVgrow(
                tablaArticulos,
                Priority.ALWAYS
        );

        tablaArticulos.skinProperty().addListener(
                (obs, oldSkin, newSkin) -> {

                    Region header
                    = (Region) tablaArticulos.lookup(
                            ".column-header-background"
                    );

                    if (header != null) {

                        header.setStyle("""
                            -fx-background-color: #dfe4e8;
                            -fx-border-color: transparent;
                            -fx-pref-height: 40px;
                            -fx-min-height: 40px;
                            -fx-max-height: 40px;
                        """);
                        tablaArticulos.lookupAll(".column-header")
                                .forEach(node -> {

                                    node.setStyle("""
                                        -fx-alignment: center;
                                        -fx-pref-height: 20px;
                                        -fx-padding: 0;
                                    """);
                                }
                                );
                    }
                }
        );

        panelArticulos.getChildren().addAll(
                subtitulo,
                tablaArticulos
        );
    }

    private void mostrarFormularioArticulo() {

        panelArticulos.getChildren().clear();

        Label tituloFormulario
                = new Label("Nuevo Artículo");

        tituloFormulario.setStyle("""
            -fx-font-size: 18px;
            -fx-font-weight: bold;
            """);

        GridPane formulario = new GridPane();

        formulario.setHgap(15);
        formulario.setVgap(18);

        Label lblCodigo
                = new Label("Código");

        TextField txtCodigo
                = new TextField();

        txtCodigo.setPromptText(
                "Introduce el código"
        );

        Label lblDescripcion
                = new Label("Descripción");

        TextArea txtDescripcion
                = new TextArea();

        txtDescripcion.setPromptText(
                "Introduce la descripción"
        );

        txtDescripcion.setPrefRowCount(3);

        Label lblPrecio
                = new Label("Precio");

        TextField txtPrecio
                = new TextField();

        txtPrecio.setPromptText(
                "Ejemplo: 19.99"
        );

        Label lblGastosEnvio
                = new Label("Gastos de envío");

        TextField txtGastosEnvio
                = new TextField();

        txtGastosEnvio.setPromptText(
                "Ejemplo: 4.50"
        );

        Label lblTiempoPrep
                = new Label("Tiempo preparación");

        TextField txtTiempoPrep
                = new TextField();

        txtTiempoPrep.setPromptText(
                "Ejemplo: 2 días"
        );

        formulario.add(lblCodigo, 0, 0);
        formulario.add(txtCodigo, 0, 1);

        formulario.add(lblDescripcion, 0, 2);
        formulario.add(txtDescripcion, 0, 3);

        formulario.add(lblPrecio, 0, 4);
        formulario.add(txtPrecio, 0, 5);

        formulario.add(lblGastosEnvio, 0, 6);
        formulario.add(txtGastosEnvio, 0, 7);

        formulario.add(lblTiempoPrep, 0, 8);
        formulario.add(txtTiempoPrep, 0, 9);

        Button btnCancelar
                = new Button("Cancelar");

        Button btnGuardar
                = new Button("Guardar");

        HBox botones = new HBox(10);
        botones.setAlignment(Pos.CENTER_RIGHT);

        botones.getChildren().addAll(
                btnCancelar,
                btnGuardar
        );

        VBox contenedorFormulario
                = new VBox(25);

        contenedorFormulario.getChildren().addAll(
                tituloFormulario,
                formulario,
                botones
        );

        panelArticulos.getChildren()
                .add(contenedorFormulario);

        btnCancelar.setOnAction(e -> {
            mostrarTablaArticulos();
        });
    }
}
