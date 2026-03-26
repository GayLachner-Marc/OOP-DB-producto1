package com.compilers.onlinestore.model.Clientes;

public class ClienteEstandar extends Cliente {
    public ClienteEstandar(String nombre, String domicilio, String nif, String email) {
        super(nombre, domicilio, nif, email);
    }

    @Override
    public double calcularDescuentoEnvio() {
        return 0.0;
    }
}
