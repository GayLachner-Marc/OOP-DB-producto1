package com.compilers.onlinestore;


import com.compilers.onlinestore.model.Tienda;
import com.compilers.onlinestore.model.Articulos.Articulo;
import com.compilers.onlinestore.model.Clientes.Cliente;
import com.compilers.onlinestore.model.Clientes.ClienteEstandar;
import com.compilers.onlinestore.model.Clientes.ClientePremium;
import com.compilers.onlinestore.model.Pedidos.Pedido;

public class OnlineStore {

    public static void main(String[] args) {

        //TEST
        // Crear tienda
        Tienda tienda = new Tienda("Online Store");

        // Crear controlador lógico
        ControladoraLogicaTiendaOnline controladora = new ControladoraLogicaTiendaOnline(tienda);

        // Crear clientes
        Cliente c1 = new ClienteEstandar("Juan", "Calle 1", "123A", "juan@email.com");
        Cliente c2 = new ClientePremium("Ana", "Calle 2", "456B", "ana@email.com");

        tienda.añadirCliente(c1);
        tienda.añadirCliente(c2);

        // Crear artículos (String codigo, String descripcion, double precioVenta,double gastosEnvio, int tiempoPreparacion) 
        Articulo a1 = new Articulo("A1", "Portátil", 1000, 20, 60);
        tienda.añadirArticulo(a1);

        // Crear pedido (int numeroPedido, Cliente cliente, Articulo articulo, int cantidad)
        Pedido p1 = new Pedido(1, c2, a1, 2);
        tienda.añadirPedido(p1);

        // Probar cálculo total
        System.out.println("Total pedido: " + p1.calcularTotal());

        // Probar estado envío
        System.out.println("Esta enviado? " + p1.estaEnviado());

        // Mostrar pedidos pendientes
        System.out.println("Pedidos pendientes:");
        for (Pedido p : tienda.mostrarPedidosPendientes()) {
            System.out.println("Pedido numero " + p);
        }
    }

}

