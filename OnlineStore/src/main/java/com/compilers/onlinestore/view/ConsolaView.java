package com.compilers.onlinestore.view;

import com.compilers.onlinestore.controller.Controladora;
import com.compilers.onlinestore.exceptions.ArticuloNoExisteException;
import com.compilers.onlinestore.exceptions.PedidoYaEnviadoException;
import com.compilers.onlinestore.exceptions.ClienteNoExisteException;
import com.compilers.onlinestore.exceptions.PedidoNoExisteException;
import com.compilers.onlinestore.model.Articulos.Articulo;
import com.compilers.onlinestore.model.Clientes.Cliente;
import com.compilers.onlinestore.model.Clientes.ClientePremium;
import com.compilers.onlinestore.model.Clientes.ClienteEstandar;
import com.compilers.onlinestore.model.Pedidos.Pedido;
import com.compilers.onlinestore.dao.ArticuloDAOImpl;
import com.compilers.onlinestore.dao.ClienteDAOImpl;
import com.compilers.onlinestore.dao.PedidoDAOImpl;
import java.util.List;
import java.util.Scanner;

public class ConsolaView {

    private Controladora controladora;
    private Scanner sc;

    public ConsolaView(Controladora controladora) {
        this.controladora = controladora;
        sc = new Scanner(System.in);
    }

    public void iniciar() throws PedidoNoExisteException, ArticuloNoExisteException, PedidoYaEnviadoException {

        int opcion;

        do {

            System.out.println("\n==== MENU PRINCIPAL ====");
            System.out.println("1. Cliente");
            System.out.println("2. Articulo");
            System.out.println("3. Pedido");
            System.out.println("0. Salir");

            opcion = leerEntero("Opcion: ");

            switch (opcion) {
                case 1 -> menuClientes();
                case 2 -> menuArticulos();
                case 3 -> menuPedidos();
            }

        } while (opcion != 0);
    }

    // ================= CLIENTES =================
    private void menuClientes() {

        int opcion;

        do {

            System.out.println("\n--- GESTION CLIENTES ---");
            System.out.println("1. Crear");
            System.out.println("2. Listar");
            System.out.println("3. Modificar");
            System.out.println("4. Eliminar");
            System.out.println("0. Volver");

            opcion = leerEntero("Opcion: ");

            switch (opcion) {
                case 1 -> crearCliente();
                case 2 -> listarClientes();
                case 3 -> actualizarCliente();
                case 4 -> eliminarCliente();
            }

        } while (opcion != 0);
    }

    private void crearCliente() {

    System.out.println("\n1. Cliente Estandar");
    System.out.println("2. Cliente Premium");

    int tipo = leerEntero("Tipo cliente: ");

    String nombre = leerTexto("Nombre: ");
    String email = leerTexto("Email: ");
    String domicilio = leerTexto("Domicilio: ");
    String nif = leerTexto("NIF: ");

    Cliente cliente;

    if (tipo == 2) {
        cliente = new ClientePremium(nombre, domicilio, nif, email);
    } else {
        cliente = new ClienteEstandar(nombre, domicilio, nif, email);
    }

    controladora.crearCliente(cliente);

    System.out.println("Cliente creado.");
}

    private void listarClientes() {

        List<Cliente> lista = controladora.listarClientes();

        if (lista.isEmpty()) {
            System.out.println("No hay clientes.");
            return;
        }

        for (Cliente c : lista) {
            System.out.println(c);
        }
    }

    private void actualizarCliente() {

    String email = leerTexto("Email del cliente a actualizar: ");

    Cliente c = controladora.buscarCliente(email);

    if (c == null) {
        System.out.println("Cliente no encontrado.");
        return;
    }

    String nombre = leerTexto("Nuevo nombre: ");
    String domicilio = leerTexto("Nuevo domicilio: ");
    String nif = leerTexto("Nuevo NIF: ");

    c.setNombre(nombre);
    c.setDomicilio(domicilio);
    c.setNif(nif);

    controladora.actualizarCliente(c);

    System.out.println("Cliente actualizado.");
}

   private void eliminarCliente() {

    String email = leerTexto("Email cliente: ");

    controladora.eliminarCliente(email);

    System.out.println("Cliente eliminado (si existía).");
}

    // ================= ARTICULOS =================
    private void menuArticulos() throws ArticuloNoExisteException {

        int opcion;

        do {

            System.out.println("\n--- GESTION ARTICULOS ---");
            System.out.println("1. Crear");
            System.out.println("2. Listar");
            System.out.println("3. Modificar");
            System.out.println("4. Eliminar");
            System.out.println("0. Volver");

            opcion = leerEntero("Opcion: ");

            switch (opcion) {
                case 1 -> crearArticulo();
                case 2 -> listarArticulos();
                case 3 -> actualizarArticulo();
                case 4 -> eliminarArticulo();
            }

        } while (opcion != 0);
    }

   private void crearArticulo() {

    String codigo = leerTexto("Codigo: ");
    String descripcion = leerTexto("Descripcion: ");
    double precioVenta = leerDouble("Precio venta: ");
    double gastosEnvio = leerDouble("Gastos envio: ");
    int tiempoPreparacion = leerEntero("Tiempo preparacion: ");

    Articulo a = new Articulo(
            codigo,
            descripcion,
            precioVenta,
            gastosEnvio,
            tiempoPreparacion
    );

    controladora.crearArticulo(a);

    System.out.println("Articulo creado.");
}

    private void actualizarArticulo() throws ArticuloNoExisteException {

    String codigo = leerTexto("Codigo articulo: ");
    Articulo a = controladora.buscarArticulo(codigo);

    if (a == null) {
        System.out.println("Articulo no encontrado.");
        return;
    }

    String descripcion = leerTexto("Nueva descripcion: ");
    double precio = leerDouble("Nuevo precio: ");
    double envio = leerDouble("Nuevos gastos envio: ");
    int tiempo = leerEntero("Nuevo tiempo de preparacion: ");

    a.setDescripcion(descripcion);
    a.setPrecioVenta(precio);
    a.setGastosEnvio(envio);
    a.setTiempoPreparacion(tiempo);

    controladora.actualizarArticulo(a);

    System.out.println("Articulo modificado.");
}

    private void listarArticulos() {

        List<Articulo> lista = controladora.listarArticulos();

        if (lista.isEmpty()) {
            System.out.println("No hay articulos.");
            return;
        }

        for (Articulo a : lista) {
            System.out.println(a);
        }
    }

   private void eliminarArticulo() {

    String codigo = leerTexto("Codigo articulo: ");

    try {

        if (controladora.eliminarArticulo(codigo)) {
            System.out.println("Articulo eliminado.");
        } else {
            System.out.println("Articulo no encontrado.");
        }

    } catch (ArticuloNoExisteException e) {
        System.out.println("Error: " + e.getMessage());
    }
}


    // ================= PEDIDOS =================
    private void menuPedidos() throws PedidoNoExisteException, PedidoYaEnviadoException {

        int opcion;

        do {

            System.out.println("\n--- GESTION PEDIDOS ---");
            System.out.println("1. Crear");
            System.out.println("2. Listar");
            System.out.println("3. Modificar");
            System.out.println("4. Eliminar");
            System.out.println("0. Volver");

            opcion = leerEntero("Opcion: ");

            switch (opcion) {
                case 1 -> crearPedido();
                case 2 -> listarPedidos();
                case 3 -> actualizarPedido();
                case 4 -> eliminarPedido();
            }

        } while (opcion != 0);
    }

    private void crearPedido() {

    int numero = leerEntero("Numero pedido: ");
    String email = leerTexto("Email cliente: ");
    String codigo = leerTexto("Codigo articulo: ");
    int cantidad = leerEntero("Cantidad: ");

    Cliente cliente = controladora.buscarCliente(email);
    Articulo articulo = controladora.buscarArticulo(codigo);

    if (cliente == null || articulo == null) {
        System.out.println("Error: cliente o articulo no existe.");
        return;
    }

    Pedido p = new Pedido(numero, cliente, articulo, cantidad);

    controladora.crearPedido(p);

    System.out.println("Pedido creado.");
}

    private void actualizarPedido() {

    try {

        int numero = leerEntero("Numero pedido: ");
        Pedido p = controladora.buscarPedido(numero);

        if (p == null) {
            System.out.println("Pedido no encontrado.");
            return;
        }

        int cantidad = leerEntero("Nueva cantidad: ");
        p.setCantidad(cantidad);

        controladora.actualizarPedido(p);

        System.out.println("Pedido modificado.");

    } catch (PedidoYaEnviadoException e) {
        System.out.println(e.getMessage());
    }
}
    private void listarPedidos() {

        List<Pedido> lista = controladora.listarPedidos();

        if (lista.isEmpty()) {
            System.out.println("No hay pedidos.");
            return;
        }

        for (Pedido p : lista) {
            System.out.println(p);
        }
    }

   private void eliminarPedido() {

    int numero = leerEntero("Numero pedido: ");

    try {
        controladora.eliminarPedido(numero);
        System.out.println("Pedido eliminado (si existía).");

    } catch (PedidoYaEnviadoException e) {
        System.out.println(e.getMessage());
    }
}

    // ================= LECTURA SEGURA =================
    private String leerTexto(String mensaje) {

        String texto;

        do {

            System.out.print(mensaje);
            texto = sc.nextLine().trim();

            if (texto.isEmpty()) {
                System.out.println("Campo obligatorio.");
            }

        } while (texto.isEmpty());

        return texto;
    }

    private int leerEntero(String mensaje) {

        while (true) {

            try {

                System.out.print(mensaje);
                return Integer.parseInt(sc.nextLine());

            } catch (NumberFormatException e) {

                System.out.println("Debe introducir un numero.");

            }
        }
    }

    private double leerDouble(String mensaje) {

        while (true) {

            try {

                System.out.print(mensaje);
                return Double.parseDouble(sc.nextLine());

            } catch (NumberFormatException e) {

                System.out.println("Debe introducir un numero valido.");

            }
        }
    }
}