package com.compilers.onlinestore.model.Clientes;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("PREMIUM")
public class ClientePremium extends Cliente {

    @Column(name = "cuota_anual")
private double cuotaAnual = 30.0;

    public ClientePremium() {
        super();
    }

    public ClientePremium(
            String nombre,
            String domicilio,
            String nif,
            String email
    ) {
        super(
                nombre,
                domicilio,
                nif,
                email
        );
    }

    public ClientePremium(
            String nombre,
            String domicilio,
            String nif,
            String email,
            double cuotaAnual
    ) {

        super(
                nombre,
                domicilio,
                nif,
                email
        );

        this.cuotaAnual = cuotaAnual;
    }

    public double getCuotaAnual() {
        return cuotaAnual;
    }

    public void setCuotaAnual(double cuotaAnual) {
        this.cuotaAnual = cuotaAnual;
    }

    @Override
    public double calcularDescuentoEnvio() {
        return 0.20;
    }

    @Override
    public String toString() {
        return nombre + " - " + email;
    }
}