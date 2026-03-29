package com.compilers.onlinestore.model.Clientes;

public class ClienteEstandar extends Cliente {

    public ClienteEstandar(int id, String nombre, String email, String domicilio, String nif) {
        super(id, nombre, email, domicilio, nif);
    }

    public ClienteEstandar(String nombre, String email, String domicilio, String nif) {
        super(nombre, email, domicilio, nif);
    }

    @Override
    public double calcularDescuentoEnvio() {
        return 0.0;
    }

    @Override
    public String toString() {
        return "ClienteEstandar{"
            + "nombre=" + getNombre()
            + ", domicilio=" + getDomicilio()
            + ", nif=" + getNif()
            + ", email=" + getEmail()
            + '}';
    }
}
