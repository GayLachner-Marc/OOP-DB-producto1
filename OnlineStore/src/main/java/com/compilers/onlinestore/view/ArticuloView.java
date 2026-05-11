package com.compilers.onlinestore.view;

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
        //contenedorPrincipal.setPadding(new Insets(30));
        contenedorPrincipal.setPadding(
                new Insets(10, 0, 0, 0)
        );
        HBox cabeceraArticulo = new HBox();

        Label titulo = new Label("Gestión de Artículos");
        titulo.setStyle("""
            -fx-font-size: 28px;
            -fx-font-weight: bold;
            """);

        Region espacioFlexible = new Region();
        HBox.setHgrow(espacioFlexible, Priority.ALWAYS);

        Button btnAgregarArt = new Button("+ Añadir Artículo");

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
        panelArticulos.setPadding(new Insets(25));
        panelArticulos.setSpacing(20);

        panelArticulos.setStyle("""
            -fx-background-color: white;
            -fx-background-radius: 12;
            -fx-border-color: #d1d5db;
            -fx-border-radius: 12;
            """);

        // CARGA LA TABLA INICIAL
        mostrarTablaArticulos();

        contenedorPrincipal.getChildren().addAll(
                cabeceraArticulo,
                panelArticulos
        );

        ventanaPrincipal.setCenter(contenedorPrincipal);
    }

    private void mostrarTablaArticulos() {

        panelArticulos.getChildren().clear();

        Label subtitulo = new Label("Artículos");

        subtitulo.setStyle("""
            -fx-font-size: 22px;
            -fx-font-weight: bold;
            """);

        TableView tablaArticulos = new TableView();

        TableColumn columnaCodigo
                = new TableColumn("Código");

        TableColumn columnaDescripcion
                = new TableColumn("Descripción");

        TableColumn columnaPrecio
                = new TableColumn("Precio");

        TableColumn columnaGastosEnvio
                = new TableColumn("Gastos envío");

        TableColumn columnaTiempoPrep
                = new TableColumn("Tiempo preparación");

        TableColumn columnaAcciones
                = new TableColumn("Acciones");

        tablaArticulos.getColumns().addAll(
                columnaCodigo,
                columnaDescripcion,
                columnaPrecio,
                columnaGastosEnvio,
                columnaTiempoPrep,
                columnaAcciones
        );

        tablaArticulos.setPlaceholder(
                new Label("No hay artículos registrados")
        );

        tablaArticulos.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
        );

        tablaArticulos.setPrefHeight(350);

        panelArticulos.getChildren().addAll(
                subtitulo,
                tablaArticulos
        );
    }

    private void mostrarFormularioArticulo() {

        panelArticulos.getChildren().clear();

        Label tituloFormulario = new Label("Nuevo Artículo");
        tituloFormulario.setStyle(
                "-fx-font-size: 24px;"
                + "-fx-font-weight: bold;"
                + "-fx-text-fill: #2c3e50;"
        );

        GridPane formulario = new GridPane();
        formulario.setHgap(15);
        formulario.setVgap(15);
        formulario.setPadding(new Insets(20));
        formulario.setStyle(
                "-fx-background-color: white;"
                + "-fx-background-radius: 10;"
                + "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.08), 10, 0, 0, 4);"
        );

        Label lblNombre = new Label("Nombre:");
        TextField txtNombre = new TextField();
        txtNombre.setPromptText("Introduce el nombre del artículo");

        Label lblDescripcion = new Label("Descripción:");
        TextArea txtDescripcion = new TextArea();
        txtDescripcion.setPromptText("Introduce una descripción");
        txtDescripcion.setPrefRowCount(3);

        Label lblPrecio = new Label("Precio:");
        TextField txtPrecio = new TextField();
        txtPrecio.setPromptText("Ejemplo: 19.99");

        Label lblStock = new Label("Stock:");
        TextField txtStock = new TextField();
        txtStock.setPromptText("Cantidad disponible");

        formulario.add(lblNombre, 0, 0);
        formulario.add(txtNombre, 1, 0);

        formulario.add(lblDescripcion, 0, 1);
        formulario.add(txtDescripcion, 1, 1);

        formulario.add(lblPrecio, 0, 2);
        formulario.add(txtPrecio, 1, 2);

        formulario.add(lblStock, 0, 3);
        formulario.add(txtStock, 1, 3);

        Button btnGuardar = new Button("Guardar");
        Button btnCancelar = new Button("Cancelar");

        HBox botones = new HBox(10);
        botones.setAlignment(Pos.CENTER_RIGHT);
        botones.getChildren().addAll(btnCancelar, btnGuardar);

        VBox contenedorFormulario = new VBox(20);
        contenedorFormulario.getChildren().addAll(tituloFormulario, formulario, botones);

        panelArticulos.getChildren().add(contenedorFormulario);

        btnCancelar.setOnAction(e -> {
            mostrarTablaArticulos();
        });
    }
}
