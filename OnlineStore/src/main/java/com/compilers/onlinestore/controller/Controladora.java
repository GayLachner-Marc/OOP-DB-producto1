package com.compilers.onlinestore.controller;
import com.compilers.onlinestore.model.Articulos.Articulo;
import com.compilers.onlinestore.model.Clientes.Cliente;
import com.compilers.onlinestore.model.Pedidos.Pedido;
import com.compilers.onlinestore.util.JPAUtil;
import jakarta.persistence.EntityManager;
import com.compilers.onlinestore.exceptions.*;
import java.util.List;

public class Controladora {

   public Controladora(){}

    

    // ================= CLIENTES =================

    public void crearCliente(Cliente c) {

        EntityManager em = JPAUtil.getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally{
            em.close();
        }

        }
        
    public Cliente buscarCliente(String email) {
        EntityManager em = JPAUtil.getEntityManager();
        Cliente c = em.find(Cliente.class, email);
        em.close();
        return c;
    }

    public List<Cliente> listarClientes() {
        EntityManager em = JPAUtil.getEntityManager();

        List<Cliente> lista = em
                .createQuery("SELECT c FROM Cliente c", Cliente.class)
                .getResultList();
            em.close();
            return lista;
    }

    public void actualizarCliente(Cliente cliente) throws ClienteNoExisteException {
        EntityManager em = JPAUtil.getEntityManager();
        try{
            em.getTransaction().begin();
            Cliente existente = em.find(Cliente.class, cliente.getEmail());
            if (existente == null){
                throw new ClienteNoExisteException("Cliente no encontrado");
            }
            existente.setNombre(cliente.getNombre());
            existente.setDomicilio(cliente.getDomicilio());
            existente.setNif(cliente.getNif());

            em.getTransaction().commit();
        } catch (Exception e){
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public boolean eliminarCliente(String email) {
        EntityManager em = JPAUtil.getEntityManager();
        try{
            em.getTransaction().begin();
            Cliente c = em.find(Cliente.class, email);
            if (c==null) {
                return false;
            }
            em.remove(c);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }}

    // ================= ARTICULOS =================

    public void crearArticulo(Articulo a) {
         EntityManager em = JPAUtil.getEntityManager();

    try {
        em.getTransaction().begin();

        em.persist(a); 

        em.getTransaction().commit();

    } catch (Exception e) {
        em.getTransaction().rollback();
        e.printStackTrace();
    } finally {
        em.close();
    }
    }

    public Articulo buscarArticulo(Integer codigo) {
        EntityManager em = JPAUtil.getEntityManager();
        Articulo a = em.find(Articulo.class, codigo);
        em.close();
        return a;
    }

    public List<Articulo> listarArticulos() {
        EntityManager em =JPAUtil.getEntityManager();
        List<Articulo> lista = em
                .createQuery("SELECT a FROM Articulo a", Articulo.class)
                .getResultList();

        em.close();
        return lista;
    }

    public void actualizarArticulo(Articulo a)
            throws ArticuloNoExisteException {
        EntityManager em= JPAUtil.getEntityManager();

        try{
            em.getTransaction().begin();
            em.merge(a);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally{
            em.close();
        }
        }
         
       
    public void eliminarArticulo(Integer codigo)
        throws ArticuloNoExisteException {

    EntityManager em = JPAUtil.getEntityManager();

    try{
        em.getTransaction().begin();
        Articulo a = em.find(Articulo.class, codigo);
        if(a!= null) {
            em.remove(a);
        }
        em.getTransaction().commit();
    }catch (Exception e) {
        em.getTransaction().rollback();
        e.printStackTrace();
    } finally{
        em.close();
    }

    }
    
   


    // ================= PEDIDOS =================

    public void crearPedido(Pedido p) {

    EntityManager em = JPAUtil.getEntityManager();

    try {
        em.getTransaction().begin();

        // Opcional: validar que existen en BD
        Cliente cliente = em.find(Cliente.class, p.getCliente().getEmail());
        Articulo articulo = em.find(Articulo.class, p.getArticulo().getCodigo());

        if (cliente == null || articulo == null) {
            System.out.println("Cliente o articulo no existen");
            return;
        }

        // Reasociar entidades gestionadas
        p.setCliente(cliente);
        p.setArticulo(articulo);

        em.persist(p);

        em.getTransaction().commit();

    } catch (Exception e) {
        em.getTransaction().rollback();
        e.printStackTrace();
    } finally {
        em.close();
    }
}

    public Pedido buscarPedido(int numero) {
        EntityManager em = JPAUtil.getEntityManager();
        Pedido p = em.find(Pedido.class, numero);
        em.close();
        return p;
    }

    public List<Pedido> listarPedidos() {
        EntityManager em = JPAUtil.getEntityManager();
        List<Pedido> lista = em
            .createQuery("SELECT p FROM Pedido p", Pedido.class)
            .getResultList();
        em.close();
        return lista;
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
        em.getTransaction().rollback();
        throw e;
    } finally {
        em.close();
    }
}
    public boolean eliminarPedido(int numero)
        throws PedidoYaEnviadoException {
            EntityManager em = JPAUtil.getEntityManager();

            try{
                em.getTransaction().begin();
                Pedido p = em.find(Pedido.class, numero);

                if(p==null){
                    return false;
                }
                if(p.estaEnviado()) {
                    throw new PedidoYaEnviadoException("No se puede eliminar el pedido, ya fue enviado");
                }
                em.remove(p);
                em.getTransaction().commit();

                return true;
            } catch (Exception e) {
                em.getTransaction().rollback();
                throw e;
            } finally {
                em.close();
            }
}
    
}