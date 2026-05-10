package com.compilers.onlinestore.view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
//import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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
        contenedorPrincipal.setSpacing(25);
        contenedorPrincipal.setPadding(new Insets(30));

        // FILA TÍTULO + BOTÓN
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

        cabeceraArticulo.getChildren().addAll(titulo,
                espacioFlexible,
                btnAgregarArt
        );

        // CONTENEDOR DE TABLA
        panelArticulos = new VBox();
        panelArticulos.setPadding(new Insets(25));
        panelArticulos.setSpacing(20);

        panelArticulos.setStyle("""
                -fx-background-color: white;
                -fx-background-radius: 12;
                -fx-border-color: #d1d5db;
                -fx-border-radius: 12;
                """);

        Label subtitulo = new Label("Artículos");
        subtitulo.setStyle("""
                -fx-font-size: 22px;
                -fx-font-weight: bold;
                """);

        /*Label msjSinArticulos = new Label("Aquí aparecerán los artículos");

        panelArticulos.getChildren().addAll(subtitulo,
                msjSinArticulos
        );*/
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

        contenedorPrincipal.getChildren().addAll(cabeceraArticulo,
                panelArticulos
        );

        ventanaPrincipal.setCenter(contenedorPrincipal);
        
    }

    private void mostrarFormularioArticulo() {

        panelArticulos.getChildren().clear();

        Label tituloFormulario
                = new Label("Nuevo Artículo");

        tituloFormulario.setStyle("""
            -fx-font-size: 24px;
            -fx-font-weight: bold;
            """);

        Label mensaje
                = new Label("Aquí irá el formulario");

        panelArticulos.getChildren().addAll(
                tituloFormulario,
                mensaje
        );
    }
}
