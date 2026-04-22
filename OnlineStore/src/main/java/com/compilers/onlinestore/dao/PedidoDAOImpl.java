package com.compilers.onlinestore.dao;
import com.compilers.onlinestore.model.Pedidos.Pedido;
import com.compilers.onlinestore.util.JpaDbUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;

public class PedidoDAOImpl implements PedidoDAO {

    // ===================== CREATE =====================
    @Override
    public void crear(Pedido p) {

        EntityManager em = JpaDbUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            p.setFechaHora(LocalDateTime.now());

            em.persist(p);

            tx.commit();

        } catch (Exception e) {

            if (tx.isActive()) {
                tx.rollback();
            }

            e.printStackTrace();

        } finally {
            em.close();
        }
    }

    // ===================== DELETE =====================
    @Override
    public void eliminar(int numero) {

        EntityManager em = JpaDbUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            TypedQuery<Pedido> query = em.createQuery(
                    "SELECT p FROM Pedido p WHERE p.numeroPedido = :numero",
                    Pedido.class
            );

            query.setParameter("numero", numero);

            List<Pedido> lista = query.getResultList();

            if (!lista.isEmpty()) {
                em.remove(lista.get(0));
            }

            tx.commit();

        } catch (Exception e) {

            if (tx.isActive()) {
                tx.rollback();
            }

            e.printStackTrace();

        } finally {
            em.close();
        }
    }

    // ===================== OBTENER UNO =====================
    @Override
    public Pedido obtenerPorNumero(int numero) {

        EntityManager em = JpaDbUtil.getEntityManager();

        try {

            TypedQuery<Pedido> query = em.createQuery(
                    "SELECT p FROM Pedido p WHERE p.numeroPedido = :numero",
                    Pedido.class
            );

            query.setParameter("numero", numero);

            List<Pedido> lista = query.getResultList();

            if (lista.isEmpty()) {
                return null;
            }

            return lista.get(0);

        } finally {
            em.close();
        }
    }

    // ===================== OBTENER TODOS =====================
    @Override
    public List<Pedido> obtenerTodos() {

        EntityManager em = JpaDbUtil.getEntityManager();

        try {

            TypedQuery<Pedido> query = em.createQuery(
                    "SELECT p FROM Pedido p",
                    Pedido.class
            );

            return query.getResultList();

        } finally {
            em.close();
        }
    }

    // ===================== ACTUALIZAR =====================
    @Override
    public void actualizar(Pedido p) {

        EntityManager em = JpaDbUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            em.merge(p);

            tx.commit();

        } catch (Exception e) {

            if (tx.isActive()) {
                tx.rollback();
            }

            e.printStackTrace();

        } finally {
            em.close();
        }
    }

    // ===================== OBTENER POR CLIENTE =====================
    @Override
    public List<Pedido> obtenerPorCliente(int id) {

        EntityManager em = JpaDbUtil.getEntityManager();

        try {

            TypedQuery<Pedido> query = em.createQuery(
                    "SELECT p FROM Pedido p WHERE p.cliente.id = :id",
                    Pedido.class
            );

            query.setParameter("id", id);

            return query.getResultList();

        } finally {
            em.close();
        }
    }

    // ===================== VALIDACIONES =====================
    @Override
    public boolean pedidosPendientesORegistrados(int clienteId) {

        EntityManager em = JpaDbUtil.getEntityManager();

        try {

            TypedQuery<Long> query = em.createQuery(
                    "SELECT COUNT(p) FROM Pedido p WHERE p.cliente.id = :id",
                    Long.class
            );

            query.setParameter("id", clienteId);

            return query.getSingleResult() > 0;

        } finally {
            em.close();
        }
    }

    @Override
    public boolean articulosEnPedidos(int articuloId) {

        EntityManager em = JpaDbUtil.getEntityManager();

        try {

            TypedQuery<Long> query = em.createQuery(
                    "SELECT COUNT(p) FROM Pedido p WHERE p.articulo.id = :id",
                    Long.class
            );

            query.setParameter("id", articuloId);

            return query.getSingleResult() > 0;

        } finally {
            em.close();
        }
    }
}
