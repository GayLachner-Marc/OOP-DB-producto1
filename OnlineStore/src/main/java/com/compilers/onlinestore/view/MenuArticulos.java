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
        if (a == null) {
            System.out.println("Articulo no existe");
            return;
        }

        a.setDescripcion(leerTexto("Descripcion: "));
        a.setPrecioVenta(leerDouble("Precio: "));
        a.setGastosEnvio(leerDouble("Envio: "));
        a.setTiempoPreparacion(leerEntero("Tiempo: "));

        controladora.actualizarArticulo(a);
    }

    /*private void eliminar() throws ArticuloNoExisteException {
        controladora.eliminarArticulo(leerTexto("Codigo: "));
    }*/
    private void eliminar() {

        String codigo = leerTexto("Codigo: ");

        boolean eliminado = controladora.eliminarArticulo(codigo);

        if (eliminado) {
            System.out.println("Articulo eliminado correctamente");
        } else {
            System.out.println("Articulo no existe");
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
                if (numeroOpcion >= 0 && numeroOpcion <= 4) {
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
