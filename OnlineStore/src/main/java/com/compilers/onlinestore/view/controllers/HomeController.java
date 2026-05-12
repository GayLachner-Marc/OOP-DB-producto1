package com.compilers.onlinestore.view.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HomeController {

    @FXML
    private VBox cardClientes;

    @FXML
    private VBox cardArticulos;

    @FXML
    private VBox cardPedidos;

    @FXML
    public void initialize() {

        configurarHover(cardClientes);
        configurarHover(cardArticulos);
        configurarHover(cardPedidos);

        cardClientes.setOnMouseClicked(e ->
                abrirModulo("clientes")
        );

        cardArticulos.setOnMouseClicked(e ->
                abrirModulo("articulos")
        );

        cardPedidos.setOnMouseClicked(e ->
                abrirModulo("pedidos")
        );
    }

    private void abrirModulo(String modulo) {

        try {

            Button btnArticulos =
                    (Button) cardClientes
                            .getScene()
                            .lookup("#btnArticulos");

            Button btnClientes =
                    (Button) cardClientes
                            .getScene()
                            .lookup("#btnClientes");

            Button btnPedidos =
                    (Button) cardClientes
                            .getScene()
                            .lookup("#btnPedidos");

            HBox menuSuperior =
                    (HBox) cardClientes
                            .getScene()
                            .lookup("#menuSuperior");

            // Mostrar menú
            menuSuperior.setVisible(true);
            menuSuperior.setManaged(true);

            switch (modulo) {

                case "articulos" ->
                        btnArticulos.fire();

                case "clientes" ->
                        btnClientes.fire();

                case "pedidos" ->
                        btnPedidos.fire();
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    private void configurarHover(VBox card) {

        String normal = """
            -fx-background-color: white;
            -fx-background-radius: 18;
            -fx-padding: 28;
            -fx-cursor: hand;
            -fx-effect: dropshadow(
                three-pass-box,
                rgba(0,0,0,0.08),
                10,
                0,
                0,
                3
            );
            """;

        String hover = """
            -fx-background-color: white;
            -fx-background-radius: 18;
            -fx-border-radius: 18;
            -fx-border-color: #e2e8f0;
            -fx-border-width: 1;
            -fx-padding: 28;
            -fx-cursor: hand;
            -fx-effect: dropshadow(
                three-pass-box,
                rgba(0,0,0,0.12),
                18,
                0,
                0,
                6
            );
            """;

        card.setStyle(normal);

        card.setOnMouseEntered(e -> {

            card.setTranslateY(-3);
            card.setStyle(hover);
        });

        card.setOnMouseExited(e -> {

            card.setTranslateY(0);
            card.setStyle(normal);
        });
    }
}