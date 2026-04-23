package com.compilers.onlinestore.test;
import com.compilers.onlinestore.controller.Controladora;
import com.compilers.onlinestore.exceptions.ArticuloNoExisteException;
import com.compilers.onlinestore.model.Articulos.Articulo;

public class testJPA {

    public static void main(String[] args) throws ArticuloNoExisteException {

        Controladora c = new Controladora();

        // Crear artículo
        Articulo a = new Articulo(1, 999, "Test JPA", 10.0, 2.0, 5);
        c.crearArticulo(a);

        // Listar
        System.out.println(c.listarArticulos());

        // Modificar
        a.setDescripcion("Modificado JPA");
        c.actualizarArticulo(a);

        // Ver resultado
        System.out.println(c.buscarArticulo(999));
    }
}
