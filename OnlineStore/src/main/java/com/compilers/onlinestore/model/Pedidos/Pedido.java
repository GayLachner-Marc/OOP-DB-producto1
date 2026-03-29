package com.compilers.onlinestore.model.Pedidos;

import java.time.Duration;
import java.time.LocalDateTime;

import com.compilers.onlinestore.model.Articulos.Articulo;
import com.compilers.onlinestore.model.Clientes.Cliente;


public class Pedido {
    private int id;
    private int numeroPedido;
    private LocalDateTime fechaHora;
    private int cantidad;
    private Cliente cliente;
    private Articulo articulo;
    
     public Pedido(int id, int numeroPedido, Cliente cliente, Articulo articulo, int cantidad) {
        this.id = id;
        this.numeroPedido = numeroPedido;
        this.cliente = cliente;
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.fechaHora = LocalDateTime.now();
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    @Override
    public String toString() {
        // Importante: importa java.time.format.DateTimeFormatter
        java.time.format.DateTimeFormatter formatoFecha
                = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd");
        java.time.format.DateTimeFormatter formatoHora
                = java.time.format.DateTimeFormatter.ofPattern("HH:mm");

        String fecha = fechaHora.format(formatoFecha);
        String hora = fechaHora.format(formatoHora);

        return "Pedido{"
                + "numeroPedido=" + numeroPedido
                + ", fecha=" + fecha
                + ", hora=" + hora
                + ", cantidad=" + cantidad
                + ", cliente=" + cliente
                + ", articulo=" + articulo
                + ", total=" + calcularTotal()
                + '}';
    }

    public Pedido() {
    }

    public Pedido(int numeroPedido, Cliente cliente, Articulo articulo, int cantidad) {
        this.numeroPedido = numeroPedido;
        this.cliente = cliente;
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.fechaHora = LocalDateTime.now();
    }

    public double calcularTotal() {
        double subtotal = articulo.getPrecioVenta() * cantidad;

        double envio = articulo.getGastosEnvio();
        double descuento = envio * cliente.calcularDescuentoEnvio();//0.20 descuento premium
        envio -= descuento;

        return subtotal + envio;
    }

    public boolean estaEnviado() {
        LocalDateTime ahora = LocalDateTime.now();
        long minutosTranscurridos = Duration.between(fechaHora, ahora).toMinutes();
        return minutosTranscurridos >= articulo.getTiempoPreparacion();
    }

    public boolean puedeCancelarse() {
        return !estaEnviado();
    }

    public Cliente getCliente() {
        return cliente;
    }
    
}
