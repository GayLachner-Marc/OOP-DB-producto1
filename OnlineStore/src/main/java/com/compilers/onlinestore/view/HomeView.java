package com.compilers.onlinestore.view;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
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
                        "Bienvenido"
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
                        "Administra la información de tus clientes",
                        crearIconoClientes()
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
                        "Controla tu inventario y productos",
                        crearIconoArticulos()
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
                        "Administra los pedidos de tus clientes",
                        crearIconoPedidos()
                );

        card.setOnMouseClicked(e
                -> abrirPedidos.run()
        );

        return card;
    }

    private VBox crearCardBase(
            String tituloTexto,
            String descripcionTexto,
            Node icono
    ) {

        StackPane iconoContainer
                = new StackPane(icono);

        iconoContainer.setPrefSize(56, 56);
        iconoContainer.setMaxSize(56, 56);
        iconoContainer.setMinSize(56, 56);
        iconoContainer.setAlignment(Pos.CENTER);

        iconoContainer.setStyle("""
        -fx-background-color: #eef2ff;
        -fx-background-radius: 16;
        """);

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
                iconoContainer,
                titulo,
                descripcion,
                spacer,
                accederBox
        );
        card.setOnMouseEntered(e -> {

            card.setTranslateY(-3);

            card.setStyle("""
            -fx-background-color: white;
            -fx-background-radius: 18;
            -fx-border-radius: 18;
            -fx-border-color: #e2e8f0;
            -fx-border-width: 1;
            -fx-cursor: hand;
            -fx-effect: dropshadow(
                three-pass-box,
                rgba(0,0,0,0.12),
                18,
                0,
                0,
                6
            );
            """);
        });

        card.setOnMouseExited(e -> {

            card.setTranslateY(0);

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
        });

        return card;
    }

    private Node crearIconoClientes() {

        SVGPath cuerpo = new SVGPath();
        cuerpo.setContent("""
            M18 21A8 8 0 0 0 2 21
            M22 20C22 16.63 20 13.5 18 12
            """);

        Circle cabeza = new Circle(10, 8, 5);

        cuerpo.setStyle("""
            -fx-fill: transparent;
            -fx-stroke: #2563eb;
            -fx-stroke-width: 2;
            """);

        cabeza.setStyle("""
            -fx-fill: transparent;
            -fx-stroke: #2563eb;
            -fx-stroke-width: 2;
            """);

        Group icono = new Group(
                cuerpo,
                cabeza
        );

        icono.setScaleX(1.5);
        icono.setScaleY(1.5);

        return icono;
    }

    private Node crearIconoArticulos() {

        SVGPath caja = new SVGPath();

        caja.setContent("""
            M11 21.73a2 2 0 0 0 2 0l7-4A2 2 0 0 0 21 16V8
            a2 2 0 0 0-1-1.73l-7-4a2 2 0 0 0-2 0l-7 4
            A2 2 0 0 0 3 8v8a2 2 0 0 0 1 1.73z
            M12 22V12
            M3.29 7L12 12L20.71 7
            M7.5 4.27L16.5 9.42
            """);

        caja.setStyle("""
            -fx-fill: transparent;
            -fx-stroke: #16a34a;
            -fx-stroke-width: 2;
            """);

        caja.setScaleX(1.5);
        caja.setScaleY(1.5);

        return caja;
    }

    private Node crearIconoPedidos() {

        SVGPath carrito = new SVGPath();

        carrito.setContent("""
            M2.05 2.05H4.05
            L6.71 14.47
            A2 2 0 0 0 8.71 16.05
            H18.49
            A2 2 0 0 0 20.44 14.48
            L22.09 7.05
            H5.12
            """);

        Circle rueda1 = new Circle(8, 21, 1);
        Circle rueda2 = new Circle(19, 21, 1);

        carrito.setStyle("""
            -fx-fill: transparent;
            -fx-stroke: #9333ea;
            -fx-stroke-width: 2;
            """);

        rueda1.setStyle("""
            -fx-fill: transparent;
            -fx-stroke: #9333ea;
            -fx-stroke-width: 2;
            """);

        rueda2.setStyle("""
            -fx-fill: transparent;
            -fx-stroke: #9333ea;
            -fx-stroke-width: 2;
            """);

        Group icono = new Group(
                carrito,
                rueda1,
                rueda2
        );

        icono.setScaleX(1.5);
        icono.setScaleY(1.5);

        return icono;
    }
}
