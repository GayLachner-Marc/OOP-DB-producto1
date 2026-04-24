package com.compilers.onlinestore.model.Pedidos;

import jakarta.persistence.*;

import java.time.Duration;
import java.time.LocalDateTime;

import com.compilers.onlinestore.model.Articulos.Articulo;
import com.compilers.onlinestore.model.Clientes.Cliente;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "numero_pedido", nullable = false, unique = true)
    private int numeroPedido;

    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    @Column(nullable = false)
    private int cantidad;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "articulo_id", nullable = false)
    private Articulo articulo;

    public Pedido() {
    }

    public Pedido(int numeroPedido, Cliente cliente, Articulo articulo, int cantidad) {
        this.numeroPedido = numeroPedido;
        this.cliente = cliente;
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.fechaHora = LocalDateTime.now();
    }

    public Pedido(int id, int numeroPedido, Cliente cliente, Articulo articulo, int cantidad) {
        this.id = id;
        this.numeroPedido = numeroPedido;
        this.cliente = cliente;
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.fechaHora = LocalDateTime.now();
    }

    @PrePersist
    public void asignarFechaCreacion() {
        if (fechaHora == null) {
            fechaHora = LocalDateTime.now();
        }
    }

    public int getId() {
        return id;
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public int getCantidad() {
        return cantidad;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNumeroPedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public double calcularTotal() {

        double subtotal = articulo.getPrecioVenta() * cantidad;

        double envio = articulo.getGastosEnvio();

        double descuento = envio * cliente.calcularDescuentoEnvio();

        envio -= descuento;

        return subtotal + envio;
    }

    public boolean estaEnviado() {

        long minutosTranscurridos
                = Duration.between(fechaHora, LocalDateTime.now()).toMinutes();

        return minutosTranscurridos >= articulo.getTiempoPreparacion();
    }

    public boolean puedeCancelarse() {
        return !estaEnviado();
    }

    @Override
    public String toString() {

        java.time.format.DateTimeFormatter formatoFecha
                = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd");

        java.time.format.DateTimeFormatter formatoHora
                = java.time.format.DateTimeFormatter.ofPattern("HH:mm");

        return "Pedido{"
                + "id= " + id
                + ", numeroPedido= " + numeroPedido
                + ", fecha= " + fechaHora.format(formatoFecha)
                + ", hora= " + fechaHora.format(formatoHora)
                + ", cantidad= " + cantidad
                + ", cliente= " + cliente.getEmail()
                + ", articulo= " + articulo.getCodigo()
                + ", total= " + calcularTotal()
                + '}';
    }
}