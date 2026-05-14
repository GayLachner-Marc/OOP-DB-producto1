package com.compilers.onlinestore.view;

import com.compilers.onlinestore.controller.Controladora;
import com.compilers.onlinestore.model.Clientes.Cliente;
import com.compilers.onlinestore.model.Clientes.ClientePremium;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.*;

public class ClienteView extends VBox {

    private final Controladora controladora
            = new Controladora();

    private TableView<Cliente> tablaClientes;
    private ObservableList<Cliente> listaClientes;

    private VBox panelClientes;

    public ClienteView() {

        setSpacing(18);

        setPadding(
                new Insets(10, 0, 0, 0)
        );

        crearContenido();
    }

    private void crearContenido() {

        HBox cabecera = new HBox();

        Label titulo
                = new Label(
                        "Gestión de Clientes"
                );

        titulo.setStyle("""
            -fx-font-size: 28px;
            -fx-font-weight: bold;
            """);

        Region espacioFlexible = new Region();

        HBox.setHgrow(
                espacioFlexible,
                Priority.ALWAYS
        );

        Button btnAgregar
                = new Button(
                        "+ Añadir Cliente"
                );

        btnAgregar.setStyle("""
            -fx-background-color: #2563eb;
            -fx-text-fill: white;
            -fx-font-size: 15px;
            -fx-font-weight: bold;
            -fx-background-radius: 10;
            -fx-padding: 12 22;
            """);

        btnAgregar.setOnAction(e -> {
            mostrarFormularioCliente();
        });

        cabecera.getChildren().addAll(
                titulo,
                espacioFlexible,
                btnAgregar
        );

        panelClientes = new VBox();

        panelClientes.setSpacing(20);

        panelClientes.setPadding(
                new Insets(20)
        );

        panelClientes.setStyle("""
            -fx-background-color: white;
            -fx-background-radius: 12;
            -fx-border-color: #d1d5db;
            -fx-border-radius: 12;
            """);

        mostrarTablaClientes();

        getChildren().addAll(
                cabecera,
                panelClientes
        );
    }

    private void mostrarTablaClientes() {

        panelClientes.getChildren().clear();

        tablaClientes = new TableView<>();

        TableColumn<Cliente, String> colNombre
                = new TableColumn<>("NOMBRE");

        colNombre.setCellValueFactory(
                new PropertyValueFactory<>("nombre")
        );

        TableColumn<Cliente, String> colEmail
                = new TableColumn<>("EMAIL");

        colEmail.setCellValueFactory(
                new PropertyValueFactory<>("email")
        );

        TableColumn<Cliente, String> colDomicilio
                = new TableColumn<>("DOMICILIO");

        colDomicilio.setCellValueFactory(
                new PropertyValueFactory<>("domicilio")
        );

        TableColumn<Cliente, String> colNif
                = new TableColumn<>("NIF");

        colNif.setCellValueFactory(
                new PropertyValueFactory<>("nif")
        );

        TableColumn<Cliente, String> colTipo
                = new TableColumn<>("TIPO");

        colTipo.setCellValueFactory(data -> {

            if (data.getValue() instanceof ClientePremium) {

                return new javafx.beans.property.SimpleStringProperty(
                        "PREMIUM"
                );
            }

            return new javafx.beans.property.SimpleStringProperty(
                    "ESTÁNDAR"
            );
        });

        TableColumn<Cliente, Void> colAcciones
        = new TableColumn<>("ACCIONES");

        colAcciones.setCellFactory(param -> new TableCell<>() {

    private final Button btnEditar
            = new Button("✏");

    private final Button btnEliminar
            = new Button("🗑");

    private final HBox contenedor
            = new HBox(10, btnEditar, btnEliminar);

    {

        contenedor.setAlignment(Pos.CENTER);
String estiloEditar = """
    -fx-background-color: #2563eb;
    -fx-text-fill: white;
    -fx-background-radius: 8;
    -fx-cursor: hand;
    -fx-font-size: 14px;
    -fx-font-weight: bold;
    -fx-padding: 6 10;
    """;

String estiloEditarHover = """
    -fx-background-color: #1d4ed8;
    -fx-text-fill: white;
    -fx-background-radius: 8;
    -fx-cursor: hand;
    -fx-font-size: 14px;
    -fx-font-weight: bold;
    -fx-padding: 6 10;
    -fx-effect: dropshadow(
        three-pass-box,
        rgba(37,99,235,0.4),
        10,
        0,
        0,
        2
    );
    """;

btnEditar.setStyle(estiloEditar);
btnEditar.setOnMouseEntered(e -> {
    btnEditar.setStyle(estiloEditarHover);
});

btnEditar.setOnMouseExited(e -> {
    btnEditar.setStyle(estiloEditar);
});


     String estiloEliminar = """
    -fx-background-color: #ef4444;
    -fx-text-fill: white;
    -fx-background-radius: 8;
    -fx-cursor: hand;
    -fx-font-size: 14px;
    -fx-font-weight: bold;
    -fx-padding: 6 10;
    """;

String estiloEliminarHover = """
    -fx-background-color: #dc2626;
    -fx-text-fill: white;
    -fx-background-radius: 8;
    -fx-cursor: hand;
    -fx-font-size: 14px;
    -fx-font-weight: bold;
    -fx-padding: 6 10;
    -fx-effect: dropshadow(
        three-pass-box,
        rgba(239,68,68,0.4),
        10,
        0,
        0,
        2
    );
    """;

btnEliminar.setStyle(estiloEliminar);

btnEliminar.setOnMouseEntered(e -> {
    btnEliminar.setStyle(estiloEliminarHover);
});

btnEliminar.setOnMouseExited(e -> {
    btnEliminar.setStyle(estiloEliminar);
});
        btnEditar.setOnAction(event -> {

            Cliente cliente
                    = getTableView()
                    .getItems()
                    .get(getIndex());

            mostrarFormularioEditar(cliente);
        });

        btnEliminar.setOnAction(event -> {

            Cliente cliente
                    = getTableView()
                    .getItems()
                    .get(getIndex());

            eliminarCliente(cliente);
        });
    }

    @Override
    protected void updateItem(Void item, boolean empty) {

        super.updateItem(item, empty);

        if (empty) {

            setGraphic(null);

        } else {

            setGraphic(contenedor);
        }
    }
});

       tablaClientes.getColumns().addAll(
        colNombre,
        colEmail,
        colDomicilio,
        colNif,
        colTipo,
        colAcciones
);
        tablaClientes.setPrefHeight(550);

        cargarClientes();

        panelClientes.getChildren().add(
                tablaClientes
        );
    }

    private void cargarClientes() {

        try {

            listaClientes
                    = FXCollections.observableArrayList(
                            controladora.listarClientes()
                    );

            tablaClientes.setItems(
                    listaClientes
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    private void mostrarFormularioCliente() {

        panelClientes.getChildren().clear();

        Label tituloFormulario
                = new Label(
                        "Nuevo Cliente"
                );

        tituloFormulario.setStyle("""
            -fx-font-size: 20px;
            -fx-font-weight: bold;
            """);

        GridPane formulario = new GridPane();

        formulario.setHgap(15);
        formulario.setVgap(15);

        TextField txtNombre = new TextField();
        txtNombre.setPromptText("Nombre");

        TextField txtEmail = new TextField();
        txtEmail.setPromptText("Email");

        TextField txtDomicilio = new TextField();
        txtDomicilio.setPromptText("Domicilio");

        TextField txtNif = new TextField();
        txtNif.setPromptText("NIF");

        ComboBox<String> comboTipo
                = new ComboBox<>();

        comboTipo.getItems().addAll(
                "ESTANDAR",
                "PREMIUM"
        );

        comboTipo.setPromptText(
                "Tipo Cliente"
        );

        TextField txtCuota = new TextField();

        txtCuota.setPromptText(
                "Cuota anual"
        );

        formulario.add(new Label("Nombre"), 0, 0);
        formulario.add(txtNombre, 1, 0);

        formulario.add(new Label("Email"), 0, 1);
        formulario.add(txtEmail, 1, 1);

        formulario.add(new Label("Domicilio"), 0, 2);
        formulario.add(txtDomicilio, 1, 2);

        formulario.add(new Label("NIF"), 0, 3);
        formulario.add(txtNif, 1, 3);

        formulario.add(new Label("Tipo"), 0, 4);
        formulario.add(comboTipo, 1, 4);

        formulario.add(new Label("Cuota"), 0, 5);
        formulario.add(txtCuota, 1, 5);

        Button btnCancelar
                = new Button(
                        "Cancelar"
                );

        Button btnGuardar
                = new Button(
                        "Guardar"
                );

        btnGuardar.setStyle("""
            -fx-background-color: #2563eb;
            -fx-text-fill: white;
            -fx-font-weight: bold;
            """);

        btnGuardar.setOnAction(e -> {

            try {

                String tipo = comboTipo.getValue();

                Cliente cliente;

                if ("PREMIUM".equals(tipo)) {

                    cliente = new ClientePremium(
                            txtNombre.getText(),
                            txtDomicilio.getText(),
                            txtNif.getText(),
                            txtEmail.getText(),
                            Double.parseDouble(
                                    txtCuota.getText()
                            )
                    );

                } else {

                    cliente = new com.compilers.onlinestore.model.Clientes.ClienteEstandar(
                            txtNombre.getText(),
                            txtDomicilio.getText(),
                            txtNif.getText(),
                            txtEmail.getText()
                    );
                }

                controladora.crearCliente(cliente);

                mostrarTablaClientes();

            } catch (Exception ex) {

                ex.printStackTrace();
            }
        });

        btnCancelar.setOnAction(e -> {
            mostrarTablaClientes();
        });

        HBox botones = new HBox(10);

        botones.setAlignment(
                Pos.CENTER_RIGHT
        );

        botones.getChildren().addAll(
                btnCancelar,
                btnGuardar
        );

        VBox contenedorFormulario
                = new VBox(20);

        contenedorFormulario.getChildren().addAll(
                tituloFormulario,
                formulario,
                botones
        );

        panelClientes.getChildren().add(
                contenedorFormulario
        );
    }

    private void eliminarCliente(Cliente cliente) {

    try {

        controladora.eliminarCliente(
                cliente.getEmail()
        );

        cargarClientes();

    } catch (Exception e) {

        e.printStackTrace();
    }
}
private void mostrarFormularioEditar(
        Cliente cliente
) {

    panelClientes.getChildren().clear();

    Label tituloFormulario
            = new Label(
                    "Editar Cliente"
            );

    tituloFormulario.setStyle("""
        -fx-font-size: 20px;
        -fx-font-weight: bold;
        """);

    GridPane formulario = new GridPane();

    formulario.setHgap(15);
    formulario.setVgap(15);

    TextField txtNombre
            = new TextField(
                    cliente.getNombre()
            );

    TextField txtEmail
            = new TextField(
                    cliente.getEmail()
            );

    txtEmail.setDisable(true);

    TextField txtDomicilio
            = new TextField(
                    cliente.getDomicilio()
            );

    TextField txtNif
            = new TextField(
                    cliente.getNif()
            );

    Button btnCancelar
            = new Button(
                    "Cancelar"
            );

    Button btnGuardar
            = new Button(
                    "Guardar"
            );

    btnGuardar.setStyle("""
        -fx-background-color: #2563eb;
        -fx-text-fill: white;
        """);

    formulario.add(new Label("Nombre"), 0, 0);
    formulario.add(txtNombre, 1, 0);

    formulario.add(new Label("Email"), 0, 1);
    formulario.add(txtEmail, 1, 1);

    formulario.add(new Label("Domicilio"), 0, 2);
    formulario.add(txtDomicilio, 1, 2);

    formulario.add(new Label("NIF"), 0, 3);
    formulario.add(txtNif, 1, 3);

    btnGuardar.setOnAction(e -> {

        try {

            cliente.setNombre(
                    txtNombre.getText()
            );

            cliente.setDomicilio(
                    txtDomicilio.getText()
            );

            cliente.setNif(
                    txtNif.getText()
            );

            controladora.actualizarCliente(
                    cliente
            );

            mostrarTablaClientes();

        } catch (Exception ex) {

            ex.printStackTrace();
        }
    });

    btnCancelar.setOnAction(e -> {
        mostrarTablaClientes();
    });

    HBox botones = new HBox(
            10,
            btnCancelar,
            btnGuardar
    );

    botones.setAlignment(
            Pos.CENTER_RIGHT
    );

    VBox contenedor = new VBox(
            20,
            tituloFormulario,
            formulario,
            botones
    );

    panelClientes.getChildren().add(
            contenedor
    );
}

}