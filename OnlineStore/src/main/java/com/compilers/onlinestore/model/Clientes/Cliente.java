package com.compilers.onlinestore.model.Clientes;

public abstract class Cliente {
    protected int id;
    protected String nombre;
    protected String domicilio;
    protected String nif;
    protected String email; // identificador
    
    public Cliente(int id, String nombre, String domicilio, String nif, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.domicilio = domicilio;
        this.nif = nif;       
    }

    public Cliente(String nombre, String domicilio, String nif, String email) {
        this.nombre = nombre;
        this.email = email;
        this.domicilio = domicilio;
        this.nif = nif;       
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public abstract double calcularDescuentoEnvio();

    @Override
    public String toString() {
        return "Cliente{" + "nombre=" + nombre + ", domicilio=" + domicilio + ", nif=" + nif + ", email=" + email + '}';
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }
}
