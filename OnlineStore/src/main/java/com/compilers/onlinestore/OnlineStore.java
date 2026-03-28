package com.compilers.onlinestore;

import com.compilers.onlinestore.controller.Controladora;
import com.compilers.onlinestore.exceptions.ArticuloNoExisteException;
import com.compilers.onlinestore.exceptions.PedidoYaEnviadoException;
import com.compilers.onlinestore.exceptions.ClienteNoExisteException;
import com.compilers.onlinestore.exceptions.PedidoNoExisteException;
import com.compilers.onlinestore.view.MenuPrincipal;

public class OnlineStore {

    public static void main(String[] args) throws ClienteNoExisteException, PedidoNoExisteException, ArticuloNoExisteException, PedidoYaEnviadoException {
        Controladora controladora = new Controladora();
        MenuPrincipal vista = new MenuPrincipal(controladora);

        vista.iniciar();
    }
}
