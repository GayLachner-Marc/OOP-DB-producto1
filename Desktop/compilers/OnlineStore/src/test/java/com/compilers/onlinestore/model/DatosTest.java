package com.compilers.onlinestore.model;

import com.compilers.onlinestore.exceptions.ArticuloNoExisteException;
import com.compilers.onlinestore.exceptions.ClienteNoExisteException;
import com.compilers.onlinestore.exceptions.PedidoNoExisteException;
import com.compilers.onlinestore.exceptions.PedidoYaEnviadoException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DatosTest {

    private Datos datos;

    @BeforeEach
    public void setUp() {
        datos = new Datos();
    }

    // ================= CLIENTES =================

    @Test
    public void testCrearClienteEstandar() {

        boolean creado = datos.crearClienteEstandar(
                "Juan",
                "juan@email.com",
                "Madrid",
                "12345678A"
        );

        assertTrue(creado);
    }

    @Test
    public void testCrearClienteDuplicado() {

        datos.crearClienteEstandar(
                "Juan",
                "juan@email.com",
                "Madrid",
                "12345678A"
        );

        boolean creado = datos.crearClienteEstandar(
                "Juan",
                "juan@email.com",
                "Madrid",
                "12345678A"
        );

        assertFalse(creado);
    }

    @Test
    public void testBuscarCliente() {

        datos.crearClienteEstandar(
                "Juan",
                "juan@email.com",
                "Madrid",
                "12345678A"
        );

        Cliente c = datos.buscarCliente("juan@email.com");

        assertNotNull(c);
        assertEquals("Juan", c.getNombre());
    }

    @Test
    public void testEliminarCliente() {

        datos.crearClienteEstandar(
                "Juan",
                "juan@email.com",
                "Madrid",
                "12345678A"
        );

        boolean eliminado = datos.eliminarCliente("juan@email.com");

        assertTrue(eliminado);
    }

    @Test
    public void testModificarCliente() throws ClienteNoExisteException {

        datos.crearClienteEstandar(
                "Juan",
                "juan@email.com",
                "Madrid",
                "12345678A"
        );

        Cliente c = datos.modificarCliente(
                "juan@email.com",
                "Pedro",
                "Barcelona",
                "99999999B"
        );

        assertEquals("Pedro", c.getNombre());
        assertEquals("Barcelona", c.getDomicilio());
    }

    // ================= ARTICULOS =================

    @Test
    public void testCrearArticulo() {

        boolean creado = datos.crearArticulo(
                "A1",
                "Teclado",
                50,
                5,
                10
        );

        assertTrue(creado);
    }

    @Test
    public void testBuscarArticulo() {

        datos.crearArticulo(
                "A1",
                "Teclado",
                50,
                5,
                10
        );

        Articulo a = datos.buscarArticulo("A1");

        assertNotNull(a);
        assertEquals("Teclado", a.getDescripcion());
    }

    @Test
    public void testModificarArticulo() throws ArticuloNoExisteException {

        datos.crearArticulo(
                "A1",
                "Teclado",
                50,
                5,
                10
        );

        Articulo a = datos.modificarArticulo(
                "A1",
                "Teclado mecanico",
                80,
                7,
                15
        );

        assertEquals("Teclado mecanico", a.getDescripcion());
        assertEquals(80, a.getPrecioVenta());
    }

    @Test
    public void testEliminarArticulo() throws ArticuloNoExisteException {

        datos.crearArticulo(
                "A1",
                "Teclado",
                50,
                5,
                10
        );

        boolean eliminado = datos.eliminarArticulo("A1");

        assertTrue(eliminado);
    }

    // ================= PEDIDOS =================

    @Test
    public void testCrearPedido() {

        datos.crearClienteEstandar(
                "Juan",
                "juan@email.com",
                "Madrid",
                "12345678A"
        );

        datos.crearArticulo(
                "A1",
                "Teclado",
                50,
                5,
                10
        );

        boolean creado = datos.crearPedido(
                1,
                "juan@email.com",
                "A1",
                2
        );

        assertTrue(creado);
    }

    @Test
    public void testBuscarPedido() {

        datos.crearClienteEstandar(
                "Juan",
                "juan@email.com",
                "Madrid",
                "12345678A"
        );

        datos.crearArticulo(
                "A1",
                "Teclado",
                50,
                5,
                10
        );

        datos.crearPedido(
                1,
                "juan@email.com",
                "A1",
                2
        );

        Pedido p = datos.buscarPedido(1);

        assertNotNull(p);
        assertEquals(2, p.getCantidad());
    }

    @Test
    public void testModificarPedido() throws PedidoYaEnviadoException, PedidoNoExisteException {

        datos.crearClienteEstandar(
                "Juan",
                "juan@email.com",
                "Madrid",
                "12345678A"
        );

        datos.crearArticulo(
                "A1",
                "Teclado",
                50,
                5,
                10
        );

        datos.crearPedido(
                1,
                "juan@email.com",
                "A1",
                2
        );

        Pedido p = datos.modificarPedido(1, 5);

        assertEquals(5, p.getCantidad());
    }

    @Test
    public void testEliminarPedido() throws PedidoYaEnviadoException {

        datos.crearClienteEstandar(
                "Juan",
                "juan@email.com",
                "Madrid",
                "12345678A"
        );

        datos.crearArticulo(
                "A1",
                "Teclado",
                50,
                5,
                10
        );

        datos.crearPedido(
                1,
                "juan@email.com",
                "A1",
                2
        );

        boolean eliminado = datos.eliminarPedido(1);

        assertTrue(eliminado);
    }

    // ================= LISTAR =================

    @Test
    public void testListarClientes() {

        datos.crearClienteEstandar(
                "Juan",
                "juan@email.com",
                "Madrid",
                "12345678A"
        );

        List<Cliente> lista = datos.listarClientes();

        assertFalse(lista.isEmpty());
    }

    @Test
    public void testListarArticulos() {

        datos.crearArticulo(
                "A1",
                "Teclado",
                50,
                5,
                10
        );

        List<Articulo> lista = datos.listarArticulos();

        assertFalse(lista.isEmpty());
    }

    @Test
    public void testListarPedidos() {

        datos.crearClienteEstandar(
                "Juan",
                "juan@email.com",
                "Madrid",
                "12345678A"
        );

        datos.crearArticulo(
                "A1",
                "Teclado",
                50,
                5,
                10
        );

        datos.crearPedido(
                1,
                "juan@email.com",
                "A1",
                2
        );

        List<Pedido> lista = datos.listarPedidos();

        assertFalse(lista.isEmpty());
    }
}
