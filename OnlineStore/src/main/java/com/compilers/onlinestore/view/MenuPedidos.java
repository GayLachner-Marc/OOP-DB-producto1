package com.compilers.onlinestore.view;

import com.compilers.onlinestore.controller.Controladora;
import com.compilers.onlinestore.model.Pedidos.Pedido;
import com.compilers.onlinestore.model.Clientes.Cliente;
import com.compilers.onlinestore.model.Articulos.Articulo;
<<<<<<< HEAD
import com.compilers.onlinestore.exceptions.PedidoYaEnviadoException;
=======
import com.compilers.onlinestore.exceptions.PedidoNoExisteException;
import com.compilers.onlinestore.exceptions.PedidoYaEnviadoException;

>>>>>>> origin/emanuel
import java.util.List;
import java.util.Scanner;

public class MenuPedidos {

    private Controladora controladora;
    private Scanner sc;

    public MenuPedidos(Controladora c, Scanner sc) {
        this.controladora = c;
        this.sc = sc;
    }

<<<<<<< HEAD
    public void iniciar() throws PedidoYaEnviadoException {
=======
    public void iniciar() {
>>>>>>> origin/emanuel

        int opcion;

        do {
            System.out.println("\n--- PEDIDOS ---");
            System.out.println("1. Crear");
            System.out.println("2. Listar");
            System.out.println("3. Modificar");
            System.out.println("4. Eliminar");
            System.out.println("0. Volver");

            opcion = leerEnteroOpciones("Opcion: ");

            switch (opcion) {
<<<<<<< HEAD
                case 1 ->
                    crear();
                case 2 ->
                    listar();
                case 3 ->
                    actualizar();
                case 4 ->
                    eliminar();
=======
                case 1 -> crear();
                case 2 -> listar();
                case 3 -> actualizar();
                case 4 -> eliminar();
>>>>>>> origin/emanuel
            }

        } while (opcion != 0);
    }

    private void crear() {

        int numero = leerEntero("Numero de pedido: ");
<<<<<<< HEAD
        Cliente c = controladora.buscarCliente(leerTexto("Email cliente: "));
        Articulo a = controladora.buscarArticulo(leerTexto("Codigo de articulo: "));
        
        // Se comprueba si cliente y articulo existen
        if (c == null) {
            System.out.println("Pedido no creado: cliente no existe");
            return;
        }
        if (a == null) {
            System.out.println("Pedido no creado: Articulo no existe");
            return;
        }

        controladora.crearPedido(new Pedido(numero, c, a, leerEntero("Cantidad: ")));
        System.out.println("Cliente ID: " + c.getId());
        System.out.println("Articulo ID: " + a.getId());
    }

    private void listar() {
        List<Pedido> lista = controladora.listarPedidos();
=======
        String email = leerTexto("Email cliente: ");
        String codigo = leerTexto("Codigo de articulo: ");
        int cantidad = leerEntero("Cantidad: ");

        Cliente c = controladora.buscarCliente(email);
        Articulo a = controladora.buscarArticulo(codigo);

        if (c == null) {
            System.out.println("Pedido no creado: cliente no existe.");
            return;
        }

        if (a == null) {
            System.out.println("Pedido no creado: articulo no existe.");
            return;
        }

        Pedido p = new Pedido(numero, c, a, cantidad);

        controladora.crearPedido(p);
    }

    private void listar() {

        List<Pedido> lista = controladora.listarPedidos();

        if (lista.isEmpty()) {
            System.out.println("No hay pedidos.");
            return;
        }

>>>>>>> origin/emanuel
        lista.forEach(System.out::println);
    }

    private void actualizar() {
<<<<<<< HEAD
        try {
            Pedido p = controladora.buscarPedido(leerEntero("Numero de pedido: "));
            
            //Comprobamos que el pedido existe
            if (p == null) {
                System.out.println("Pedido no existe");
                return;
            }

            p.setCantidad(leerEntero("Cantidad: "));
            controladora.actualizarPedido(p);

        } catch (PedidoYaEnviadoException e) {
=======

        try {

            int numero = leerEntero("Numero de pedido: ");

            Pedido p = controladora.buscarPedido(numero);

            if (p == null) {
                System.out.println("Pedido no existe.");
                return;
            }

            p.setCantidad(leerEntero("Nueva cantidad: "));

            controladora.actualizarPedido(p);

            System.out.println("Pedido actualizado.");

        } catch (PedidoYaEnviadoException e) {

            System.out.println(e.getMessage());

        } catch (PedidoNoExisteException e) {

>>>>>>> origin/emanuel
            System.out.println(e.getMessage());
        }
    }

<<<<<<< HEAD
    private void eliminar() throws PedidoYaEnviadoException {
        int numero = leerEntero("Numero de pedido: ");

        try {
            boolean eliminado = controladora.eliminarPedido(numero);

            if (eliminado) {
                System.out.println("Pedido eliminado correctamente");
            } else {
                System.out.println("Pedido no existe");
            }
            //Captura la excepción cuando el pedido no se puede borrar por que ha sido enviado
        } catch (PedidoYaEnviadoException e) {
=======
    private void eliminar() {

        int numero = leerEntero("Numero de pedido: ");

        try {

            boolean eliminado = controladora.eliminarPedido(numero);

            if (eliminado) {
                System.out.println("Pedido eliminado correctamente.");
            } else {
                System.out.println("Pedido no existe.");
            }

        } catch (PedidoYaEnviadoException e) {

>>>>>>> origin/emanuel
            System.out.println("Nota: " + e.getMessage());
        }
    }

    // ================= LECTURA SEGURA =================
<<<<<<< HEAD
=======

>>>>>>> origin/emanuel
    private String leerTexto(String mensaje) {

        String texto;

        do {
<<<<<<< HEAD

=======
>>>>>>> origin/emanuel
            System.out.print(mensaje);
            texto = sc.nextLine().trim();

            if (texto.isEmpty()) {
                System.out.println("Campo obligatorio.");
            }

        } while (texto.isEmpty());

        return texto;
    }

<<<<<<< HEAD
    //Esta función se utilizada para leer enteros en general,
    //donde no es necesario realizar un filtro
    private int leerEntero(String m) {
        while (true) {

            try {
                System.out.print(m);
=======
    private int leerEntero(String mensaje) {

        while (true) {

            try {
                System.out.print(mensaje);
>>>>>>> origin/emanuel
                return Integer.parseInt(sc.nextLine().trim());

            } catch (NumberFormatException e) {
                System.out.println("Los datos a introducir deben ser numeros.");
            }
        }
    }

<<<<<<< HEAD
    //Esta función se utilizada filtrar los numeros del menu
    private int leerEnteroOpciones(String mensaje) {

        while (true) {
            try {

                System.out.print(mensaje);
                int numeroOpcion = Integer.parseInt(sc.nextLine().trim());
                if (numeroOpcion >= 0 && numeroOpcion <= 4) {
                    return numeroOpcion;

                } else {

                    System.out.println("Debe introducir una opcion valida.");
                }

            } catch (NumberFormatException e) {
=======
    private int leerEnteroOpciones(String mensaje) {

        while (true) {

            try {

                System.out.print(mensaje);

                int opcion = Integer.parseInt(sc.nextLine().trim());

                if (opcion >= 0 && opcion <= 4) {
                    return opcion;
                }

                System.out.println("Debe introducir una opcion valida.");

            } catch (NumberFormatException e) {

>>>>>>> origin/emanuel
                System.out.println("Debe introducir un numero.");
            }
        }
    }

    private double leerDouble(String mensaje) {

        while (true) {

            try {

                System.out.print(mensaje);
<<<<<<< HEAD
                return Double.parseDouble(sc.nextLine());
=======
                return Double.parseDouble(sc.nextLine().trim());
>>>>>>> origin/emanuel

            } catch (NumberFormatException e) {

                System.out.println("Debe introducir un numero valido.");
<<<<<<< HEAD

            }
        }
    }

}
=======
            }
        }
    }
}
>>>>>>> origin/emanuel
