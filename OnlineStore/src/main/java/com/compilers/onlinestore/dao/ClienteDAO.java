package com.compilers.onlinestore.dao;
import com.compilers.onlinestore.model.Clientes.Cliente;
import java.util.List;


public interface ClienteDAO {

    void crear(Cliente cliente);

    Cliente obtenerPorEmail(String email);

    List<Cliente> obtenerTodos();

    void actualizar(Cliente cliente);

    void eliminar(String email);
}