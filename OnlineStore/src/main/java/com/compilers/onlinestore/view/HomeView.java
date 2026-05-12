package com.compilers.onlinestore.view;

import javafx.scene.shape.SVGPath;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class HomeView extends VBox {

    private final Runnable abrirClientes;
    private final Runnable abrirArticulos;
    private final Runnable abrirPedidos;

    public HomeView(
            Runnable abrirClientes,
            Runnable abrirArticulos,
            Runnable abrirPedidos
    ) {

        this.abrirClientes
                = abrirClientes;

        this.abrirArticulos
                = abrirArticulos;

        this.abrirPedidos
                = abrirPedidos;

        crearContenido();
    }

    private void crearContenido() {

        setSpacing(30);
        setAlignment(Pos.TOP_CENTER);

        setPadding(
                new Insets(10, 0, 0, 0)
        );

        Label titulo
                = new Label(
                        "Bienvenido de nuevo"
                );

        titulo.setStyle("""
                -fx-font-size: 34px;
                -fx-font-weight: bold;
                -fx-text-fill: #0f172a;
                """);

        Label subtitulo
                = new Label(
                        "Selecciona un módulo para comenzar"
                );

        subtitulo.setStyle("""
                -fx-font-size: 18px;
                -fx-text-fill: #64748b;
                """);

        VBox cabecera
                = new VBox(8);

        cabecera.setAlignment(
                Pos.CENTER_LEFT
        );

        cabecera.getChildren().addAll(
                titulo,
                subtitulo
        );

        HBox tarjetas
                = new HBox(24);
        tarjetas.setAlignment(
                Pos.CENTER
        );

        tarjetas.getChildren().addAll(
                crearCardClientes(),
                crearCardArticulos(),
                crearCardPedidos()
        );

        getChildren().addAll(
                cabecera,
                tarjetas
        );
    }

    private VBox crearCardClientes() {

        VBox card
                = crearCardBase(
                        "Gestión de Clientes",
                        "Administra la información de tus clientes"
                );

        card.setOnMouseClicked(e
                -> abrirClientes.run()
        );

        return card;
    }

    private VBox crearCardArticulos() {

        VBox card
                = crearCardBase(
                        "Gestión de Artículos",
                        "Controla tu inventario y productos"
                );

        card.setOnMouseClicked(e
                -> abrirArticulos.run()
        );

        return card;
    }

    private VBox crearCardPedidos() {

        VBox card
                = crearCardBase(
                        "Gestión de Pedidos",
                        "Administra los pedidos de tus clientes"
                );

        card.setOnMouseClicked(e
                -> abrirPedidos.run()
        );

        return card;
    }

    private VBox crearCardBase(
            String tituloTexto,
            String descripcionTexto
    ) {

        Label titulo
                = new Label(tituloTexto);

        titulo.setStyle("""
            -fx-font-size: 24px;
            -fx-font-weight: bold;
            -fx-text-fill: #0f172a;
            """);

        Label descripcion
                = new Label(
                        descripcionTexto
                );

        descripcion.setWrapText(true);

        descripcion.setStyle("""
            -fx-font-size: 16px;
            -fx-text-fill: #475569;
            """);

        Label acceder
                = new Label("Acceder");

        acceder.setStyle("""
            -fx-font-size: 16px;
            -fx-font-weight: bold;
            -fx-text-fill: #4f46e5;
            """);

        SVGPath flecha = new SVGPath();

        flecha.setContent("""
        M6 17 L11 12 L6 7
        M13 17 L18 12 L13 7
        """);

        flecha.setStyle("""
        -fx-stroke: #4f46e5;
        -fx-fill: transparent;
        -fx-stroke-width: 2;
        """);

        HBox accederBox = new HBox(6);

        accederBox.setAlignment(
                Pos.CENTER_LEFT
        );

        accederBox.getChildren().addAll(
                acceder,
                flecha
        );

        Region spacer = new Region();

        VBox.setVgrow(
                spacer,
                Priority.ALWAYS
        );

        VBox card
                = new VBox(16);

        card.setAlignment(
                Pos.TOP_LEFT
        );

        card.setPadding(
                new Insets(28)
        );

        card.setPrefWidth(400);
        card.setPrefHeight(320);

        card.setStyle("""
            -fx-background-color: white;
            -fx-background-radius: 18;
            -fx-border-radius: 18;
            -fx-cursor: hand;
            -fx-effect: dropshadow(
                three-pass-box,
                rgba(0,0,0,0.08),
                10,
                0,
                0,
                3
            );
            """);

        card.getChildren().addAll(
                titulo,
                descripcion,
                spacer,
                accederBox
        );

        return card;
    }
}
