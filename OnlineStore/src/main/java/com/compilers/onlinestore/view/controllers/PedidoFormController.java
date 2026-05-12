package com.compilers.onlinestore.view.controllers;

import com.compilers.onlinestore.controller.Controladora;
import com.compilers.onlinestore.model.Articulos.Articulo;
import com.compilers.onlinestore.model.Clientes.Cliente;
import com.compilers.onlinestore.model.Pedidos.Pedido;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class PedidoFormController {

    @FXML
    private TextField txtNumeroPedido;

    @FXML
    private ComboBox<Cliente> comboCliente;

    @FXML
    private ComboBox<Articulo> comboArticulo;

    @FXML
    private TextField txtCantidad;

    @FXML
    private TextField txtPrecioUnitario;

    @FXML
    private TextField txtTotal;

    private final Controladora controladora =
            new Controladora();

    @FXML
public void initialize() {

    comboCliente.getItems().addAll(
            controladora.listarClientes()
    );

    comboArticulo.getItems().addAll(
            controladora.listarArticulos()
    );

    comboCliente.setOnAction(
            e -> actualizarTotal()
    );

    comboArticulo.setOnAction(
            e -> actualizarPrecio()
    );
}

    @FXML
    private void actualizarPrecio() {

        Articulo articulo =
                comboArticulo.getValue();

        if (articulo != null) {

            txtPrecioUnitario.setText(
                    String.valueOf(
                            articulo.getPrecioVenta()
                    )
            );

            actualizarTotal();
        }
    }

    @FXML
    private void actualizarTotal() {

        try {

            Articulo articulo =
                    comboArticulo.getValue();

            Cliente cliente =
                    comboCliente.getValue();

            if (articulo == null ||
                cliente == null ||
                txtCantidad.getText().isBlank()) {
                return;
            }

            int cantidad =
                    Integer.parseInt(
                            txtCantidad.getText()
                    );

            Pedido pedido = new Pedido(
                    0,
                    cliente,
                    articulo,
                    cantidad
            );

            txtTotal.setText(
                    String.format(
                            "%.2f",
                            pedido.calcularTotal()
                    )
            );

        } catch (Exception e) {
        }
    }

   
@FXML
private void guardarPedido() {

    try {

        if (txtNumeroPedido.getText().isBlank()
                || txtCantidad.getText().isBlank()
                || comboCliente.getValue() == null
                || comboArticulo.getValue() == null) {

            System.out.println(
                    "Todos los campos son obligatorios."
            );

            return;
        }

        int numeroPedido =
                Integer.parseInt(
                        txtNumeroPedido.getText()
                );

        int cantidad =
                Integer.parseInt(
                        txtCantidad.getText()
                );

        if (cantidad <= 0) {

            System.out.println(
                    "La cantidad debe ser mayor a 0."
            );

            return;
        }

        Cliente cliente =
                comboCliente.getValue();

        Articulo articulo =
                comboArticulo.getValue();

        Pedido pedido = new Pedido(
                numeroPedido,
                cliente,
                articulo,
                cantidad
        );

        controladora.crearPedido(
                pedido
        );

        txtCantidad.getScene()
                .getWindow()
                .hide();

        System.out.println(
                "Pedido creado."
        );

    } catch (NumberFormatException e) {

        System.out.println(
                "Debe introducir números válidos."
        );

    } catch (Exception e) {
        e.printStackTrace();
    }
}

    @FXML
    private void cancelar() {

        txtCantidad.getScene()
                .getWindow()
                .hide();
    }
}