package com.compilers.onlinestore.view;

import com.compilers.onlinestore.controller.Controladora;
import com.compilers.onlinestore.exceptions.ArticuloNoExisteException;
import com.compilers.onlinestore.exceptions.PedidoYaEnviadoException;
import com.compilers.onlinestore.exceptions.ClienteNoExisteException;
import com.compilers.onlinestore.exceptions.PedidoNoExisteException;

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