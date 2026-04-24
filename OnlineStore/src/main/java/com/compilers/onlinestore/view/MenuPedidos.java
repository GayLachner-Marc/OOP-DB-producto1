package com.compilers.onlinestore.view;

import com.compilers.onlinestore.controller.Controladora;
import com.compilers.onlinestore.model.Pedidos.Pedido;
import com.compilers.onlinestore.model.Clientes.Cliente;
import com.compilers.onlinestore.model.Articulos.Articulo;
import com.compilers.onlinestore.exceptions.PedidoNoExisteException;
import com.compilers.onlinestore.exceptions.PedidoYaEnviadoException;

import java.util.List;
import java.util.Scanner;

public class MenuPedidos {

    private Controladora controladora;
    private Scanner sc;

    public MenuPedidos(Controladora c, Scanner sc) {
        this.controladora = c;
        this.sc = sc;
    }

    public void iniciar() {

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
                case 1 -> crear();
                case 2 -> listar();
                case 3 -> actualizar();
                case 4 -> eliminar();
            }

        } while (opcion != 0);
    }

    private void crear() {

        int numero = leerEntero("Numero de pedido: ");
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

        lista.forEach(System.out::println);
    }

    private void actualizar() {

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

            System.out.println(e.getMessage());
        }
    }

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

            System.out.println("Nota: " + e.getMessage());
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
                return Integer.parseInt(sc.nextLine().trim());

            } catch (NumberFormatException e) {
                System.out.println("Los datos a introducir deben ser numeros.");
            }
        }
    }

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

                System.out.println("Debe introducir un numero.");
            }
        }
    }

    private double leerDouble(String mensaje) {

        while (true) {

            try {

                System.out.print(mensaje);
                return Double.parseDouble(sc.nextLine().trim());

            } catch (NumberFormatException e) {

                System.out.println("Debe introducir un numero valido.");
            }
        }
    }
}