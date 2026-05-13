package com.compilers.onlinestore.model.Clientes;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

import com.compilers.onlinestore.model.Pedidos.Pedido;

@Entity
@Table(name = "clientes")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_cliente")
public abstract class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @Column(nullable = false)
    protected String nombre;

    @Column(nullable = false)
    protected String domicilio;

    @Column(nullable = false, unique = true)
    protected String nif;

    @Column(nullable = false, unique = true)
    protected String email;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    protected List<Pedido> pedidos = new ArrayList<>();

    // Constructor vacío obligatorio para JPA
    public Cliente() {
    }

    // Constructor para crear nuevos clientes
    public Cliente(String nombre, String domicilio, String nif, String email) {
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.nif = nif;
        this.email = email;
    }

    // Constructor opcional con id
    public Cliente(int id, String nombre, String domicilio, String nif, String email) {
        this.id = id;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.nif = nif;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public String getNif() {
        return nif;
    }

    public String getEmail() {
        return email;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public abstract double calcularDescuentoEnvio();
/*
    @Override
    public String toString() {
        return "Cliente{"
                
                + "nombre= " + nombre
                + ", domicilio= " + domicilio
                + ", nif= " + nif
                + ", email= " + email
                + '}';
    }*/
    @Override
public String toString() {
    return nombre + " - " + email;
}
}