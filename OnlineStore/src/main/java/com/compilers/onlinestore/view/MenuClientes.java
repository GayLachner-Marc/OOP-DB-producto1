package com.compilers.onlinestore.view;

import com.compilers.onlinestore.controller.Controladora;
import com.compilers.onlinestore.model.Clientes.*;
import java.util.List;
import java.util.Scanner;

public class MenuClientes {

    private Controladora controladora;
    private Scanner sc;

    public MenuClientes(Controladora c, Scanner sc) {
        this.controladora = c;
        this.sc = sc;
    }

    public void iniciar() {

        int opcion;

        do {
            System.out.println("\n--- CLIENTES ---");
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

        System.out.println("1. Estandar  2. Premium");
        int tipo = leerEntero("Tipo: ");

        String nombre = leerTexto("Nombre: ");
        String email = leerTexto("Email: ");
        String domicilio = leerTexto("Domicilio: ");
        String nif = leerTexto("NIF: ");

        Cliente c = (tipo == 2)
                ? new ClientePremium(nombre, domicilio, nif, email)
                : new ClienteEstandar(nombre, domicilio, nif, email);

        controladora.crearCliente(c);
        System.out.println("Cliente creado.");
    }

    private void listar() {
        List<Cliente> lista = controladora.listarClientes();
        lista.forEach(System.out::println);
    }

    private void actualizar() {
        String email = leerTexto("Email: ");
        Cliente c = controladora.buscarCliente(email);

        if (c == null) {
            System.out.println("No existe.");
            return;
        }
        c.setNombre(leerTexto("Nombre: "));
        c.setDomicilio(leerTexto("Domicilio: "));
        c.setNif(leerTexto("NIF: "));

        controladora.actualizarCliente(c);
    }

    private void eliminar() {
        controladora.eliminarCliente(leerTexto("Email: "));
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
