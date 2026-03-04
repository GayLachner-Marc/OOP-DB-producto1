package com.compilers.onlinestore;

import com.compilers.onlinestore.controller.Controladora;
import com.compilers.onlinestore.view.ConsolaView;

public class OnlineStore {

    public static void main(String[] args) {
         Controladora controladora = new Controladora();
        ConsolaView vista = new ConsolaView(controladora);

        vista.iniciar(); }
}