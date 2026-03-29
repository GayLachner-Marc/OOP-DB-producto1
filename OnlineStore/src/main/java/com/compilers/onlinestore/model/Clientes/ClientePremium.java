package com.compilers.onlinestore.model.Clientes;

public class ClientePremium extends Cliente {

    private double cuotaAnual = 30.0;

    public ClientePremium(int id, String nombre,String email, String domicilio, String nif) {
        super(id, nombre, email, domicilio, nif);
    }

    public ClientePremium(String nombre, String email, String domicilio, String nif) {
        super(nombre, email, domicilio, nif);
    }

    public double getCuotaAnual() {
        return cuotaAnual;
    }

    @Override
    public String toString() {
        return "ClientePremium{"
                + "nombre=" + getNombre()
                + ", domicilio=" + getDomicilio()
                + ", nif=" + getNif()
                + ", email=" + getEmail()
                + ", cuotaAnual=" + cuotaAnual
                + '}';
    }

    @Override
    public double calcularDescuentoEnvio() {
        return 0.20; // 20%
    }
}
