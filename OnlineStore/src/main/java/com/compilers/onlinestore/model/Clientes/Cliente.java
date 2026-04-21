package com.compilers.onlinestore.model.Clientes;
import jakarta.persistence.*;
@Entity
@Table(name = "clientes")

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_cliente")

public abstract class Cliente {
    protected int id;
    protected String nombre;
    protected String domicilio;
    protected String nif;
    @Id
    protected String email; // identificador
    
    //Este constructor se utiliza cuando leemos los datos del cliente de la DB,
    //Debido a que tenemos que tener en cuenta que cuando leemos el registro de 
    //cliente debemos tomar en cuenta el ID generado por la DB
    public Cliente(int id, String nombre, String domicilio, String nif, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.domicilio = domicilio;
        this.nif = nif;       
    }
    
    //Este constructor se utilizar para crear un registro, 
    //no agregamos ID porque la DB le asigna un id
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
