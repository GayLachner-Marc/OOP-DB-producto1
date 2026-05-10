package com.compilers.onlinestore.model.Clientes;

import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;

@Entity
@DiscriminatorValue("ESTANDAR")
public class ClienteEstandar extends Cliente {

<<<<<<< HEAD
    // Constructor vacío obligatorio para JPA
    public ClienteEstandar() {
    }

    // Constructor para nuevos clientes
=======
    public ClienteEstandar() {
        super();
    }
>>>>>>> origin/emanuel
    public ClienteEstandar(String nombre, String domicilio, String nif, String email) {
        super(nombre, domicilio, nif, email);
    }

    // Constructor opcional con id
    public ClienteEstandar(int id, String nombre, String domicilio, String nif, String email) {
        super(id, nombre, domicilio, nif, email);
    }

    @Override
    public double calcularDescuentoEnvio() {
        return 0.0;
    }

    @Override
    public String toString() {
        return "ClienteEstandar{"
                + "id= " + getId()
                + ", nombre= " + getNombre()
                + ", domicilio= " + getDomicilio()
                + ", nif= " + getNif()
                + ", email= " + getEmail()
                + '}';
    }
}
