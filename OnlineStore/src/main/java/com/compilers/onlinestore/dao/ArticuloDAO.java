package com.compilers.onlinestore.dao;

import java.util.List;
import com.compilers.onlinestore.model.Articulos.Articulo;

public interface ArticuloDAO {

    void crear(Articulo articulo);
    void eliminar(String codigo);

    Articulo obtenerPorCodigo(String codigo);

    List<Articulo> obtenerTodos();
}