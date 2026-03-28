package com.compilers.onlinestore.view;

import com.compilers.onlinestore.controller.Controladora;
import com.compilers.onlinestore.exceptions.ArticuloNoExisteException;
import com.compilers.onlinestore.model.Articulos.Articulo;
import java.util.List;
import java.util.Scanner;

public class MenuArticulos {

    private Controladora controladora;
    private Scanner sc;

    public MenuArticulos(Controladora c, Scanner sc) {
        this.controladora = c;
        this.sc = sc;
    }

    public void iniciar() throws ArticuloNoExisteException {

        int opcion;

        do {
            System.out.println("\n--- ARTICULOS ---");
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
        Articulo a = new Articulo(
                leerTexto("Codigo: "),
                leerTexto("Descripcion: "),
                leerDouble("Precio: "),
                leerDouble("Envio: "),
                leerEntero("Tiempo: ")
        );

        controladora.crearArticulo(a);
    }

    private void listar() {
        List<Articulo> lista = controladora.listarArticulos();
        lista.forEach(System.out::println);
    }

    private void actualizar() throws ArticuloNoExisteException {
        Articulo a = controladora.buscarArticulo(leerTexto("Codigo: "));
        if (a == null) return;

        a.setDescripcion(leerTexto("Descripcion: "));
        a.setPrecioVenta(leerDouble("Precio: "));
        a.setGastosEnvio(leerDouble("Envio: "));
        a.setTiempoPreparacion(leerEntero("Tiempo: "));

        controladora.actualizarArticulo(a);
    }

    private void eliminar() throws ArticuloNoExisteException {
        controladora.eliminarArticulo(leerTexto("Codigo: "));
    }

    private String leerTexto(String m) {
        System.out.print(m);
        return sc.nextLine();
    }

    private int leerEntero(String m) {
        System.out.print(m);
        return Integer.parseInt(sc.nextLine());
    }

    private double leerDouble(String m) {
        System.out.print(m);
        return Double.parseDouble(sc.nextLine());
    }
}