package com.compilers.onlinestore.model;

public class ClientePremium extends Cliente{

    private double cuotaAnual = 30.0;

    
    public ClientePremium(String nombre, String domicilio, String nif, String email) {
        super(nombre, domicilio, nif, email);
    }

    public double getCuotaAnual() {
        return cuotaAnual;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ClientePremium{" + "cuotaAnual=" + cuotaAnual + '}';
    }
    
    

    @Override
    public double calcularDescuentoEnvio() {
        return 0.20; // 20%
    }
}
