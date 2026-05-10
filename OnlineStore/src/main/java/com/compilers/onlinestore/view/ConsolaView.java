package com.compilers.onlinestore.view;

import com.compilers.onlinestore.controller.Controladora;
import com.compilers.onlinestore.exceptions.ArticuloNoExisteException;
import com.compilers.onlinestore.exceptions.PedidoYaEnviadoException;
import com.compilers.onlinestore.exceptions.PedidoNoExisteException;
<<<<<<< HEAD
import com.compilers.onlinestore.model.Articulos.Articulo;
import com.compilers.onlinestore.model.Clientes.Cliente;
import com.compilers.onlinestore.model.Clientes.ClientePremium;
import com.compilers.onlinestore.model.Clientes.ClienteEstandar;
import com.compilers.onlinestore.model.Pedidos.Pedido;
import java.util.List;
=======

>>>>>>> origin/emanuel
import java.util.Scanner;

public class ConsolaView {

    private Controladora controladora;
    private Scanner sc;

    public ConsolaView(Controladora controladora) {
        this.controladora = controladora;
        sc = new Scanner(System.in);
    }

    public void iniciar() throws PedidoNoExisteException, ArticuloNoExisteException, ClienteNoExisteException, PedidoYaEnviadoException {

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

    // ================= REDIRECCION A MENUS =================
    private void menuClientes() {
        MenuClientes menu = new MenuClientes(controladora, sc);
        menu.iniciar();
    }

    private void menuArticulos() throws ArticuloNoExisteException {
        MenuArticulos menu = new MenuArticulos(controladora, sc);
        menu.iniciar();
    }

<<<<<<< HEAD
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

    if (controladora.eliminarArticulo(codigo)) {
        System.out.println("Articulo eliminado.");
    } else {
        System.out.println("Articulo no encontrado.");
    }
}


    // ================= PEDIDOS =================
=======
>>>>>>> origin/emanuel
    private void menuPedidos() throws PedidoNoExisteException, PedidoYaEnviadoException {
        MenuPedidos menu = new MenuPedidos(controladora, sc);
        menu.iniciar();
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