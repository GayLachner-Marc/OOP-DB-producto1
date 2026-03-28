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

            opcion = leerEntero("Opcion: ");

            switch (opcion) {
                case 1 -> crear();
                case 2 -> listar();
                case 3 -> actualizar();
                case 4 -> eliminar();
            }

        } while (opcion != 0);
    }

    private void crear() {

        int numero = leerEntero("Numero: ");
        Cliente c = controladora.buscarCliente(leerTexto("Email: "));
        Articulo a = controladora.buscarArticulo(leerTexto("Codigo: "));

        if (c == null || a == null) return;

        controladora.crearPedido(new Pedido(numero, c, a, leerEntero("Cantidad: ")));
    }

    private void listar() {
        List<Pedido> lista = controladora.listarPedidos();
        lista.forEach(System.out::println);
    }

    private void actualizar() {
        try {
            Pedido p = controladora.buscarPedido(leerEntero("Numero: "));
            if (p == null) return;

            p.setCantidad(leerEntero("Cantidad: "));
            controladora.actualizarPedido(p);

        } catch (PedidoYaEnviadoException e) {
            System.out.println(e.getMessage());
        }
    }

    private void eliminar() throws PedidoYaEnviadoException {
        controladora.eliminarPedido(leerEntero("Numero: "));
    }

    private String leerTexto(String m) {
        System.out.print(m);
        return sc.nextLine();
    }

    private int leerEntero(String m) {
        System.out.print(m);
        return Integer.parseInt(sc.nextLine());
    }
}
