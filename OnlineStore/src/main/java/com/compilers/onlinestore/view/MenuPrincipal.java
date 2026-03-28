package com.compilers.onlinestore.view;

import com.compilers.onlinestore.controller.Controladora;
import com.compilers.onlinestore.exceptions.ArticuloNoExisteException;
import com.compilers.onlinestore.exceptions.PedidoYaEnviadoException;
import java.util.Scanner;

public class MenuPrincipal {

    private Controladora controladora;
    private Scanner sc;

    private MenuClientes menuClientes;
    private MenuArticulos menuArticulos;
    private MenuPedidos menuPedidos;

    public MenuPrincipal(Controladora controladora) {
        this.controladora = controladora;
        this.sc = new Scanner(System.in);

        menuClientes = new MenuClientes(controladora, sc);
        menuArticulos = new MenuArticulos(controladora, sc);
        menuPedidos = new MenuPedidos(controladora, sc);
    }

    public void iniciar() throws ArticuloNoExisteException, PedidoYaEnviadoException {

        int opcion;

        do {
            System.out.println("\n==== MENU PRINCIPAL ====");
            System.out.println("1. Cliente");
            System.out.println("2. Articulo");
            System.out.println("3. Pedido");
            System.out.println("0. Salir");

            opcion = leerEntero("Opcion: ");

            switch (opcion) {
                case 1 -> menuClientes.iniciar();
                case 2 -> menuArticulos.iniciar();
                case 3 -> menuPedidos.iniciar();
            }

        } while (opcion != 0);
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
}