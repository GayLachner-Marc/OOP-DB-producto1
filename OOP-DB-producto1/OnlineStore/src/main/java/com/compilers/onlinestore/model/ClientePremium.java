package com.compilers.onlinestore.model;

public class ClientePremium extends Cliente{

   private double cuotaAnual = 30.0;

    public ClientePremium(String nombre, String domicilio, String nif, String email) {
        super(nombre, domicilio, nif, email);
    }

    public double getCuotaAnual() {
        return cuotaAnual;
    }

    @Override
    public double calcularDescuentoEnvio() {
        return 0.20;
    }

    @Override
    public String toString() {
        return super.toString() + " ClientePremium{cuotaAnual=" + cuotaAnual + "}";
    }
}
