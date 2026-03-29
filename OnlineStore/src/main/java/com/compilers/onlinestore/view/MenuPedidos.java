package com.compilers.onlinestore.view;

import com.compilers.onlinestore.controller.Controladora;
import com.compilers.onlinestore.model.Pedidos.Pedido;
import com.compilers.onlinestore.model.Clientes.Cliente;
import com.compilers.onlinestore.model.Articulos.Articulo;
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

    public void iniciar() throws PedidoYaEnviadoException {

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
                case 1 ->
                    crear();
                case 2 ->
                    listar();
                case 3 ->
                    actualizar();
                case 4 ->
                    eliminar();
            }

        } while (opcion != 0);
    }

    private void crear() {

        int numero = leerEntero("Numero de pedido: ");
        Cliente c = controladora.buscarCliente(leerTexto("Email cliente: "));
        Articulo a = controladora.buscarArticulo(leerTexto("Codigo de artículo: "));

        if (c == null || a == null) {
            return;
        }

        controladora.crearPedido(new Pedido(numero, c, a, leerEntero("Cantidad: ")));
        System.out.println("Cliente ID: " + c.getId());
        System.out.println("Articulo ID: " + a.getId());
    }

    private void listar() {
        List<Pedido> lista = controladora.listarPedidos();
        lista.forEach(System.out::println);
    }

    private void actualizar() {
        try {
            Pedido p = controladora.buscarPedido(leerEntero("Numero de pedido: "));
            if (p == null) {
                return;
            }

            p.setCantidad(leerEntero("Cantidad: "));
            controladora.actualizarPedido(p);

        } catch (PedidoYaEnviadoException e) {
            System.out.println(e.getMessage());
        }
    }

    private void eliminar() throws PedidoYaEnviadoException {
        try {
            controladora.eliminarPedido(leerEntero("Numero de pedido: "));
            System.out.println("Pedido eliminado correctamente");

        } catch (PedidoYaEnviadoException e) {

            System.out.println("Nota:" + e.getMessage());

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
    
    private int leerEntero(String m) {
        System.out.print(m);
        return Integer.parseInt(sc.nextLine().trim());
    }

    private int leerEnteroOpciones(String mensaje) {

        while (true) {
            try {
                
                System.out.print(mensaje);
                int numeroOpcion = Integer.parseInt(sc.nextLine().trim());
                if (numeroOpcion >=0 && numeroOpcion <=4) {
                    return numeroOpcion;
                    
                } else {
                    
                    System.out.println("Debe introducir una opcion valida.");
                }
                

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
