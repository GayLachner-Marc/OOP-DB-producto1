package com.compilers.onlinestore.model.Clientes;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PREMIUM")
public class ClientePremium extends Cliente {

    @Column(name = "cuota_anual")
    private double cuotaAnual = 30.0;

    // Constructor vacío obligatorio para JPA
    public ClientePremium() {
        super();
    }

    public ClientePremium(String nombre, String domicilio, String nif, String email) {
        super(nombre, domicilio, nif, email);
    }

    // Constructor opcional con id
    public ClientePremium(int id, String nombre, String domicilio, String nif, String email) {
        super(id, nombre, domicilio, nif, email);
    }

    public double getCuotaAnual() {
        return cuotaAnual;
    }

    public void setCuotaAnual(double cuotaAnual) {
        this.cuotaAnual = cuotaAnual;
    }

    @Override
    public double calcularDescuentoEnvio() {
        return 0.20; // 20%
    }

    @Override
    public String toString() {
        return "ClientePremium{"
                + "id= " + getId()
                + ", nombre= " + getNombre()
                + ", domicilio= " + getDomicilio()
                + ", nif= " + getNif()
                + ", email= " + getEmail()
                + ", cuotaAnual= " + cuotaAnual
                + '}';
    }
}