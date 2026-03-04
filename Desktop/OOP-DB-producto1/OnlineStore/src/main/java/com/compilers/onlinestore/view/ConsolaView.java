
package com.compilers.onlinestore.view;


import com.compilers.onlinestore.controller.Controladora;
import com.compilers.onlinestore.model.Articulo;
import com.compilers.onlinestore.model.Cliente;
import com.compilers.onlinestore.model.ClienteEstandar;
import com.compilers.onlinestore.model.Pedido;
import java.util.Scanner;
public class ConsolaView {
    private Controladora controladora;
    private Scanner sc = new Scanner(System.in);

    public ConsolaView(Controladora controladora) {
        this.controladora = controladora;
    }
   
    public void iniciar() {

        int opcion;

        do {            
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("\n1.Cliente");
            System.out.println("2.Articulo");
            System.out.println("3.Pedido");
            System.out.println("0.Salir");

            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1 -> menuClientes();
                case 2 -> menuArticulos();
                case 3 -> menuPedidos();
            }

        } while (opcion != 0);
    }

    // ================= CLIENTES =================

    private void menuClientes() {

        int opcion;

        do {
            System.out.println("\n--- GESTION CLIENTES ---");
            System.out.println("1. Crear");
            System.out.println("2. Listar");
            System.out.println("3. Modificar");
            System.out.println("4. Eliminar");
            System.out.println("0. Volver");

            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1 -> crearCliente();
                case 2 -> controladora.getClientes().forEach(System.out::println);
                case 3 -> modificarCliente();
                case 4 -> eliminarCliente();
            }

        } while (opcion != 0);
    }

    private void crearCliente() {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Domicilio: ");
        String domicilio = sc.nextLine();

        System.out.print("NIF: ");
        String nif = sc.nextLine();

        Cliente c = new ClienteEstandar(nombre, email, domicilio, nif);

        if (controladora.crearCliente(c))
            System.out.println("Cliente creado.");
        else
            System.out.println("Ya existe ese cliente.");
    }

    private void modificarCliente() {
        System.out.print("Email del cliente: ");
        String email = sc.nextLine();

        System.out.print("Nuevo nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Nuevo domicilio: ");
        String domicilio = sc.nextLine();

        System.out.print("Nuevo NIF: ");
        String nif = sc.nextLine();

        if (controladora.modificarCliente(email, nombre, domicilio, nif))
            System.out.println("Cliente modificado.");
        else
            System.out.println("Cliente no encontrado.");
    }

    private void eliminarCliente() {
        System.out.print("Email del cliente: ");
        String email = sc.nextLine();

        if (controladora.eliminarCliente(email))
            System.out.println("Cliente eliminado.");
        else
            System.out.println("Cliente no encontrado.");
    }

    // ================= ARTICULOS =================

    private void menuArticulos() {

        int opcion;

        do {
            System.out.println("\n--- GESTION ARTICULOS ---");
            System.out.println("1. Crear");
            System.out.println("2. Listar");
            System.out.println("3. Eliminar");
            System.out.println("0. Volver");

            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1 -> crearArticulo();
                case 2 -> controladora.getArticulos().forEach(System.out::println);
                case 3 -> eliminarArticulo();
            }

        } while (opcion != 0);
    }

    private void crearArticulo() {

        System.out.print("Codigo: ");
        String codigo = sc.nextLine();

        System.out.print("Descripcion: ");
        String descripcion = sc.nextLine();

        System.out.print("Precio venta: ");
        double precioVenta = Double.parseDouble(sc.nextLine());

        System.out.print("Gastos envio: ");
        double gastosEnvio = Double.parseDouble(sc.nextLine());

        System.out.print("Tiempo preparacion: ");
        int tiempoPreparacion = Integer.parseInt(sc.nextLine());

        Articulo a = new Articulo(
                codigo, descripcion,
                precioVenta, gastosEnvio,
                tiempoPreparacion
        );

        if (controladora.crearArticulo(a))
            System.out.println("Articulo creado.");
        else
            System.out.println("Ya existe ese articulo.");
    }

    private void eliminarArticulo() {
        System.out.print("Codigo del articulo: ");
        String codigo = sc.nextLine();

        if (controladora.eliminarArticulo(codigo))
            System.out.println("Articulo eliminado.");
        else
            System.out.println("Articulo no encontrado.");
    }

    // ================= PEDIDOS =================

    private void menuPedidos() {

        int opcion;

        do {
            System.out.println("\n--- GESTION PEDIDOS ---");
            System.out.println("1. Crear");
            System.out.println("2. Listar");
            System.out.println("3. Eliminar");
            System.out.println("0. Volver");

            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1 -> crearPedido();
                case 2 -> controladora.getPedidos().forEach(System.out::println);
                case 3 -> eliminarPedido();
            }

        } while (opcion != 0);
    }

    private void crearPedido() {

        System.out.print("Numero pedido: ");
        int numero = Integer.parseInt(sc.nextLine());

        System.out.print("Email cliente: ");
        String email = sc.nextLine();

        Cliente cliente = controladora.buscarCliente(email);

        if (cliente == null) {
            System.out.println("Cliente no existe.");
            return;
        }

        System.out.print("Codigo articulo: ");
        String codigo = sc.nextLine();

        Articulo articulo = controladora.buscarArticulo(codigo);

        if (articulo == null) {
            System.out.println("Articulo no existe.");
            return;
        }

        System.out.print("Cantidad: ");
        int cantidad = Integer.parseInt(sc.nextLine());

        Pedido p = new Pedido(numero, cliente, articulo, cantidad);

        if (controladora.crearPedido(p))
            System.out.println("Pedido creado.");
        else
            System.out.println("Ya existe ese pedido.");
    }

    private void eliminarPedido() {
        System.out.print("Numero pedido: ");
        int numero = Integer.parseInt(sc.nextLine());

        if (controladora.eliminarPedido(numero))
            System.out.println("Pedido eliminado.");
        else
            System.out.println("Pedido no encontrado.");
    }
}
    
    
    
    
    
    
    
    

