package com.compilers.onlinestore.controller;
import com.compilers.onlinestore.dao.*;
import com.compilers.onlinestore.factory.DAOFactory;
import com.compilers.onlinestore.model.Articulos.Articulo;
import com.compilers.onlinestore.model.Clientes.Cliente;
import com.compilers.onlinestore.model.Pedidos.Pedido;
import com.compilers.onlinestore.util.JPAUtil;

import jakarta.persistence.EntityManager;

import com.compilers.onlinestore.exceptions.*;

import java.sql.Connection;
import java.sql.SQLException;
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

    public Articulo buscarArticulo(String codigo) {
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
         
       
    public void eliminarArticulo(String codigo)
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

    public void crearPedido(int numero, String email, String codigo, int cantidad)
            throws ClienteNoExisteException, ArticuloNoExisteException {
        EntityManager em = JPAUtil.getEntityManager();

        try{
            em.getTransaction().begin();

            Cliente cliente = em.find(Cliente.class, email);
            Articulo articulo = em.find(Articulo.class, codigo);

            if (cliente == null || articulo == null) {
               System.out.println("El cliente o el articulo no existen");
               return;
            }
            Pedido p = new Pedido(numero, cliente, articulo, cantidad);
            em.persist(p);
            em.getTransaction().commit();
        } catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally{
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

    public void actualizarPedido(int numero, int nuevaCantidad)
            throws PedidoYaEnviadoException, PedidoNoExisteException {
        EntityManager em = JPAUtil.getEntityManager();

        try{
            em.getTransaction().begin();
            Pedido p = em.find(Pedido.class, numero);

            if (p==null){
                throw new PedidoNoExisteException("Pedido no encontrado");
            }

            if (p.estaEnviado()) {
                throw new PedidoYaEnviadoException("El pedido ya fue enviado");
            }
            p.setCantidad(nuevaCantidad);
            em.getTransaction().commit();
        } catch (Exception e){
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