
package com.compilers.onlinestore.view;


import com.compilers.onlinestore.controller.Controladora;
import com.compilers.onlinestore.model.Articulo;
import com.compilers.onlinestore.model.Cliente;
import com.compilers.onlinestore.model.ClienteEstandar;
import com.compilers.onlinestore.model.Pedido;
import java.util.List;
import java.util.Scanner;
public class ConsolaView {
    private Controladora controladora;
    private Scanner sc;

    public ConsolaView(Controladora controladora) {
        this.controladora = controladora;
        sc = new Scanner(System.in);
    }

    public void iniciar() {

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
            System.out.println("3. Eliminar");
            System.out.println("0. Volver");

            opcion = leerEntero("Opcion: ");

            switch (opcion) {
                case 1 -> crearCliente();
                case 2 -> listarClientes();
                case 3 -> eliminarCliente();
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

        boolean creado = false;

        if (tipo == 1) {
            creado = controladora.crearClienteEstandar(nombre, email, domicilio, nif);
        }

        if (tipo == 2) {
            creado = controladora.crearClientePremium(nombre, email, domicilio, nif);
        }

        if (creado)
            System.out.println("Cliente creado.");
        else
            System.out.println("Error: cliente ya existe.");
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

    private void eliminarCliente() {

        String email = leerTexto("Email cliente: ");

        if (controladora.eliminarCliente(email))
            System.out.println("Cliente eliminado.");
        else
            System.out.println("Cliente no encontrado.");
    }

    // ================= ARTICULOS =================

    private void menuArticulos() {

        int opcion;

        do {

            System.out.println("\n--- GESTION ARTICULOS ---");
            System.out.println("1. Crear");
            System.out.println("2. Listar");
            System.out.println("3. Eliminar");
            System.out.println("0. Volver");

            opcion = leerEntero("Opcion: ");

            switch (opcion) {
                case 1 -> crearArticulo();
                case 2 -> listarArticulos();
                case 3 -> eliminarArticulo();
            }

        } while (opcion != 0);
    }

    private void crearArticulo() {

        String codigo = leerTexto("Codigo: ");
        String descripcion = leerTexto("Descripcion: ");

        double precioVenta = leerDouble("Precio venta: ");
        double gastosEnvio = leerDouble("Gastos envio: ");

        int tiempoPreparacion = leerEntero("Tiempo preparacion (minutos): ");

        boolean creado = controladora.crearArticulo(
                codigo,
                descripcion,
                precioVenta,
                gastosEnvio,
                tiempoPreparacion
        );

        if (creado)
            System.out.println("Articulo creado.");
        else
            System.out.println("Articulo ya existe.");
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

        if (controladora.eliminarArticulo(codigo))
            System.out.println("Articulo eliminado.");
        else
            System.out.println("Articulo no encontrado.");
    }

    // ================= PEDIDOS =================

    private void menuPedidos() {

        int opcion;

        do {

            System.out.println("\n--- GESTION PEDIDOS ---");
            System.out.println("1. Crear");
            System.out.println("2. Listar");
            System.out.println("3. Eliminar");
            System.out.println("0. Volver");

            opcion = leerEntero("Opcion: ");

            switch (opcion) {
                case 1 -> crearPedido();
                case 2 -> listarPedidos();
                case 3 -> eliminarPedido();
            }

        } while (opcion != 0);
    }

    private void crearPedido() {

        int numero = leerEntero("Numero pedido: ");

        String email = leerTexto("Email cliente: ");

        String codigoArticulo = leerTexto("Codigo articulo: ");

        int cantidad = leerEntero("Cantidad: ");

        boolean creado = controladora.crearPedido(
                numero,
                email,
                codigoArticulo,
                cantidad
        );

        if (creado)
            System.out.println("Pedido creado.");
        else
            System.out.println("Error: cliente o articulo no existe.");
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

        if (controladora.eliminarPedido(numero))
            System.out.println("Pedido eliminado.");
        else
            System.out.println("Pedido no encontrado.");
    }

    // ================= LECTURA SEGURA =================

    private String leerTexto(String mensaje) {

        String texto;

        do {
            System.out.print(mensaje);
            texto = sc.nextLine().trim();

            if (texto.isEmpty())
                System.out.println("Campo obligatorio.");

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
    
    
    
    
    
    
    
    

