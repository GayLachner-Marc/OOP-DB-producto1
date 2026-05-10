package com.compilers.onlinestore.controller;

<<<<<<< HEAD
import com.compilers.onlinestore.dao.*;
=======
>>>>>>> origin/emanuel
import com.compilers.onlinestore.model.Articulos.Articulo;
import com.compilers.onlinestore.model.Clientes.Cliente;
import com.compilers.onlinestore.model.Pedidos.Pedido;
import com.compilers.onlinestore.util.JPAUtil;
import jakarta.persistence.EntityManager;
import com.compilers.onlinestore.exceptions.*;
<<<<<<< HEAD

=======
>>>>>>> origin/emanuel
import java.util.List;

public class Controladora {

<<<<<<< HEAD
    private ClienteDAO clienteDAO;
    private ArticuloDAO articuloDAO;
    private PedidoDAO pedidoDAO;

    public Controladora() {

        clienteDAO = new ClienteDAOImpl();
        articuloDAO = new ArticuloDAOImpl();
        pedidoDAO = new PedidoDAOImpl();
    }

    // ================= CLIENTES =================
    public void crearCliente(Cliente cliente) {
        clienteDAO.crear(cliente);
=======
    public Controladora() {
    }

    // ================= CLIENTES =================
    public boolean crearCliente(Cliente c) {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            Long existe = em.createQuery(
                    "SELECT COUNT(c) FROM Cliente c WHERE c.nif = :nif OR c.email = :email",
                    Long.class)
                    .setParameter("nif", c.getNif())
                    .setParameter("email", c.getEmail())
                    .getSingleResult();

            if (existe > 0) {
                System.out.println("Ya existe un cliente con ese numero de documento o email.");
                return false;
            }

            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();

            return true;

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            System.out.println("Error al crear cliente.");
            return false;

        } finally {
            em.close();
        }
>>>>>>> origin/emanuel
    }

    public Cliente buscarCliente(String email) {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            return em.createQuery(
                    "SELECT c FROM Cliente c WHERE c.email = :email",
                    Cliente.class)
                    .setParameter("email", email)
                    .getResultStream()
                    .findFirst()
                    .orElse(null);

        } finally {
            em.close();
        }
    }

    public List<Cliente> listarClientes() {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            return em.createQuery(
                    "SELECT c FROM Cliente c",
                    Cliente.class)
                    .getResultList();

        } finally {
            em.close();
        }
    }

    public void actualizarCliente(Cliente cliente) throws ClienteNoExisteException {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            em.getTransaction().begin();

            Cliente existente = em.createQuery(
                    "SELECT c FROM Cliente c WHERE c.email = :email",
                    Cliente.class)
                    .setParameter("email", cliente.getEmail())
                    .getResultStream()
                    .findFirst()
                    .orElse(null);

            if (existente == null) {
                throw new ClienteNoExisteException("Cliente no encontrado");
            }

            existente.setNombre(cliente.getNombre());
            existente.setDomicilio(cliente.getDomicilio());
            existente.setNif(cliente.getNif());

            em.getTransaction().commit();

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            throw e;

        } finally {
            em.close();
        }
    }

    public boolean eliminarCliente(String email) {

<<<<<<< HEAD
        Cliente c = clienteDAO.obtenerPorEmail(email);

        if (c == null) {
            return false;
        }

        if (pedidoDAO.pedidosPendientesORegistrados(c.getId())) {
            throw new RuntimeException("No se puede eliminar, el cliente tiene pedidos pendientes o registrados");
        }

        clienteDAO.eliminar(email);
        return true;
    }

    // ================= ARTICULOS =================
    public void crearArticulo(Articulo a) {
        articuloDAO.crear(a);
=======
        EntityManager em = JPAUtil.getEntityManager();

        try {

            em.getTransaction().begin();

            Cliente c = em.createQuery(
                    "SELECT c FROM Cliente c WHERE c.email = :email",
                    Cliente.class)
                    .setParameter("email", email)
                    .getResultStream()
                    .findFirst()
                    .orElse(null);

            if (c == null) {
                em.getTransaction().rollback();
                return false;
            }

            if (!c.getPedidos().isEmpty()) {
                em.getTransaction().rollback();
                throw new RuntimeException(
                        "No se puede eliminar, el cliente tiene pedidos asociados."
                );
            }

            em.remove(c);
            em.getTransaction().commit();

            return true;

        } catch (RuntimeException e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            throw e;

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            return false;

        } finally {
            em.close();
        }
    }

    // ================= ARTICULOS =================
    public boolean crearArticulo(Articulo a) {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            Long existe = em.createQuery(
                    "SELECT COUNT(a) FROM Articulo a WHERE a.codigo = :codigo",
                    Long.class)
                    .setParameter("codigo", a.getCodigo())
                    .getSingleResult();

            if (existe > 0) {
                System.out.println("Ya existe un articulo con ese codigo.");
                return false;
            }

            em.getTransaction().begin();
            em.persist(a);
            em.getTransaction().commit();

            return true;

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            System.out.println("Error al crear articulo.");
            return false;

        } finally {
            em.close();
        }
>>>>>>> origin/emanuel
    }

    public Articulo buscarArticulo(String codigo) {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            return em.createQuery(
                    "SELECT a FROM Articulo a WHERE a.codigo = :codigo",
                    Articulo.class)
                    .setParameter("codigo", codigo)
                    .getResultStream()
                    .findFirst()
                    .orElse(null);

        } finally {
            em.close();
        }
    }

    public List<Articulo> listarArticulos() {
        EntityManager em = JPAUtil.getEntityManager();
        List<Articulo> lista = em
                .createQuery("SELECT a FROM Articulo a", Articulo.class)
                .getResultList();

        em.close();
        return lista;
    }

<<<<<<< HEAD
    public void actualizarArticulo(Articulo a)
=======
    public void actualizarArticulo(Articulo articulo)
>>>>>>> origin/emanuel
            throws ArticuloNoExisteException {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            em.getTransaction().begin();

            Articulo existente = em.createQuery(
                    "SELECT a FROM Articulo a WHERE a.codigo = :codigo",
                    Articulo.class)
                    .setParameter("codigo", articulo.getCodigo())
                    .getResultStream()
                    .findFirst()
                    .orElse(null);

            if (existente == null) {
                throw new ArticuloNoExisteException("Articulo no existe");
            }

            existente.setDescripcion(articulo.getDescripcion());
            existente.setPrecioVenta(articulo.getPrecioVenta());
            existente.setGastosEnvio(articulo.getGastosEnvio());
            existente.setTiempoPreparacion(articulo.getTiempoPreparacion());

            em.getTransaction().commit();

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            throw e;

        } finally {
            em.close();
        }
    }

<<<<<<< HEAD
    public boolean eliminarArticulo(String codigo) {

        Articulo a = articuloDAO.obtenerPorCodigo(codigo);

        if (a == null) {
            return false;
        }

        if (pedidoDAO.articulosEnPedidos(a.getId())) {
            throw new RuntimeException("No se puede eliminar, el articulo está enlazado con pedidos");
        }

        articuloDAO.eliminar(codigo);

        return true;
=======
    public boolean eliminarArticulo(String codigo)
            throws ArticuloNoExisteException {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            em.getTransaction().begin();

            Articulo a = em.createQuery(
                    "SELECT a FROM Articulo a WHERE a.codigo = :codigo",
                    Articulo.class)
                    .setParameter("codigo", codigo)
                    .getResultStream()
                    .findFirst()
                    .orElse(null);

            if (a == null) {
                em.getTransaction().rollback();
                return false;
            }

            em.remove(a);
            em.getTransaction().commit();

            return true;

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            return false;

        } finally {
            em.close();
        }
>>>>>>> origin/emanuel
    }

    // ================= PEDIDOS =================
    public void crearPedido(Pedido p) {

        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();

            Cliente cliente = em.createQuery(
                    "SELECT c FROM Cliente c WHERE c.email = :email",
                    Cliente.class)
                    .setParameter("email", p.getCliente().getEmail())
                    .getResultStream()
                    .findFirst()
                    .orElse(null);

            Articulo articulo = em.createQuery(
                    "SELECT a FROM Articulo a WHERE a.codigo = :codigo",
                    Articulo.class)
                    .setParameter("codigo", p.getArticulo().getCodigo())
                    .getResultStream()
                    .findFirst()
                    .orElse(null);

            if (cliente == null) {
                System.out.println("Cliente no existe.");
                em.getTransaction().rollback();
                return;
            }

            if (articulo == null) {
                System.out.println("Articulo no existe.");
                em.getTransaction().rollback();
                return;
            }

            Pedido existe = em.find(Pedido.class, p.getNumeroPedido());

            if (existe != null) {
                System.out.println("Ya existe un pedido con ese numero.");
                em.getTransaction().rollback();
                return;
            }

            p.setCliente(cliente);
            p.setArticulo(articulo);

            em.persist(p);
            em.getTransaction().commit();

            System.out.println("Pedido creado.");

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            System.out.println("Error al crear pedido.");

        } finally {
            em.close();
        }
    }

    public Pedido buscarPedido(int numero) {

        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.find(Pedido.class, numero);
        } finally {
            em.close();
        }
    }

    public List<Pedido> listarPedidos() {

        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.createQuery(
                    "SELECT p FROM Pedido p",
                    Pedido.class
            ).getResultList();

        } finally {
            em.close();
        }
    }

    public void actualizarPedido(Pedido p)
            throws PedidoYaEnviadoException, PedidoNoExisteException {

        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();

            Pedido existente = em.find(Pedido.class, p.getNumeroPedido());

            if (existente == null) {
                throw new PedidoNoExisteException("Pedido no encontrado");
            }

            if (existente.estaEnviado()) {
                throw new PedidoYaEnviadoException("El pedido ya fue enviado");
            }

            existente.setCantidad(p.getCantidad());

            em.getTransaction().commit();

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            throw e;

        } finally {
            em.close();
        }
    }

    public boolean eliminarPedido(int numero)
            throws PedidoYaEnviadoException {

<<<<<<< HEAD
        if (p.estaEnviado()) {
            throw new PedidoYaEnviadoException("No se puede modificar, el pedido ya fue enviado");
=======
        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();

            Pedido p = em.find(Pedido.class, numero);

            if (p == null) {
                return false;
            }

            if (p.estaEnviado()) {
                throw new PedidoYaEnviadoException(
                        "No se puede eliminar el pedido, ya fue enviado"
                );
            }

            em.remove(p);
            em.getTransaction().commit();

            return true;

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            throw e;

        } finally {
            em.close();
>>>>>>> origin/emanuel
        }
    }
<<<<<<< HEAD

    public boolean eliminarPedido(int numero)
            throws PedidoYaEnviadoException {

        Pedido p = pedidoDAO.obtenerPorNumero(numero);

        if (p == null) {
            return false;
        }

        if (p.estaEnviado()) {
            throw new PedidoYaEnviadoException("No se puede eliminar, el pedido ya fue enviado");
        }

        pedidoDAO.eliminar(numero);

        return true;
    }
=======
>>>>>>> origin/emanuel
}
