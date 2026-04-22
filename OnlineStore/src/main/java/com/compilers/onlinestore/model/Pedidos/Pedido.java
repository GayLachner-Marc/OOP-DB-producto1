package com.compilers.onlinestore.model.Pedidos;

import java.time.Duration;
import java.time.LocalDateTime;

import jakarta.persistence.*;

import com.compilers.onlinestore.model.Articulos.Articulo;
import com.compilers.onlinestore.model.Clientes.Cliente;

@Entity
@Table(name = "pedidos")


public class Pedido {
    @Id
    @Column(name = "numero_pedido")
    private int numeroPedido;

    @ManyToOne
    @JoinColumn(name = "email_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "codigo_articulo")
    private Articulo articulo;

    @Column(name = "fecha_pedido")
    private LocalDateTime fechaHora;

    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "enviado")
    private boolean enviado;
   
  

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
        return "Pedido{" +
                "numero=" + numeroPedido +
                ", cliente=" + (cliente != null ? cliente.getEmail() : "null") +
                ", articulo=" + (articulo != null ? articulo.getCodigo() : "null") +
                ", cantidad=" + cantidad +
                ", enviado=" + enviado +
                '}';
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
