package com.compilers.onlinestore.view;

import com.compilers.onlinestore.controller.Controladora;
import com.compilers.onlinestore.exceptions.ClienteNoExisteException;
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

        System.out.println("1. Estandar  2. Premium");
        int tipo = leerEnteroTipoCliente("Tipo: ");

        String nombre = leerTexto("Nombre: ");
        String email = leerTexto("Email: ");
        String domicilio = leerTexto("Domicilio: ");
        String nif = leerTexto("NIF: ");

        Cliente c = (tipo == 2)
                ? new ClientePremium(nombre, domicilio, nif, email)
                : new ClienteEstandar(nombre, domicilio, nif, email);

        if (controladora.crearCliente(c)) {
            System.out.println("Cliente creado.");
        } else {
            System.out.println("No se pudo crear el cliente.");
        }
    }

    private void listar() {

        List<Cliente> lista = controladora.listarClientes();

        if (lista.isEmpty()) {
            System.out.println("No hay clientes.");
            return;
        }

        lista.forEach(System.out::println);
    }

    private void actualizar() {

        String email = leerTexto("Email: ");
        Cliente c = controladora.buscarCliente(email);

        if (c == null) {
            System.out.println("Cliente no existe.");
            return;
        }

        c.setNombre(leerTexto("Nombre: "));
        c.setDomicilio(leerTexto("Domicilio: "));
        c.setNif(leerTexto("NIF: "));

        try {

            controladora.actualizarCliente(c);
            System.out.println("Cliente actualizado.");

        } catch (ClienteNoExisteException e) {

            System.out.println("Error: " + e.getMessage());
        }
    }

    private void eliminar() {

        String email = leerTexto("Email: ");

        try {

            boolean eliminado = controladora.eliminarCliente(email);

            if (eliminado) {
                System.out.println("Cliente eliminado correctamente");
            } else {
                System.out.println("Cliente no existe");
            }

        } catch (RuntimeException e) {

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

    private int leerEnteroTipoCliente(String mensaje) {

        while (true) {

            try {

                System.out.print(mensaje);
                int numeroOpcion = Integer.parseInt(sc.nextLine().trim());

                if (numeroOpcion >= 1 && numeroOpcion <= 2) {
                    return numeroOpcion;
                }

                System.out.println("Debe introducir una opcion valida.");

            } catch (NumberFormatException e) {

                System.out.println("Debe introducir un numero.");
            }
        }
    }

    private int leerEnteroOpciones(String mensaje) {

        while (true) {

            try {

                System.out.print(mensaje);
                int numeroOpcion = Integer.parseInt(sc.nextLine().trim());

                if (numeroOpcion >= 0 && numeroOpcion <= 4) {
                    return numeroOpcion;
                }

                System.out.println("Debe introducir una opcion valida.");

            } catch (NumberFormatException e) {

                System.out.println("Debe introducir un numero.");
            }
        }
    }
}
