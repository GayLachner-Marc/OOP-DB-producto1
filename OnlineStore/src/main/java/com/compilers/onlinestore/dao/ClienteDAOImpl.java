package com.compilers.onlinestore.dao;

import com.compilers.onlinestore.model.Clientes.Cliente;
import com.compilers.onlinestore.util.JpaDbUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ClienteDAOImpl implements ClienteDAO {

    @Override
    public void crear(Cliente cliente) {

        EntityManager em = JpaDbUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            em.persist(cliente);

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

    @Override
    public Cliente obtenerPorEmail(String email) {

        EntityManager em = JpaDbUtil.getEntityManager();

        try {

            TypedQuery<Cliente> query = em.createQuery(
                    "SELECT c FROM Cliente c WHERE c.email = :email",
                    Cliente.class
            );

            query.setParameter("email", email);

            List<Cliente> lista = query.getResultList();

            if (lista.isEmpty()) {
                return null;
            }

            return lista.get(0);

        } finally {
            em.close();
        }
    }

    @Override
    public List<Cliente> obtenerTodos() {

        EntityManager em = JpaDbUtil.getEntityManager();

        try {

            TypedQuery<Cliente> query = em.createQuery(
                    "SELECT c FROM Cliente c",
                    Cliente.class
            );

            return query.getResultList();

        } finally {
            em.close();
        }
    }

    @Override
    public void actualizar(Cliente cliente) {

        EntityManager em = JpaDbUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            em.merge(cliente);

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

    @Override
    public void eliminar(String email) {

        EntityManager em = JpaDbUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            TypedQuery<Cliente> query = em.createQuery(
                    "SELECT c FROM Cliente c WHERE c.email = :email",
                    Cliente.class
            );

            query.setParameter("email", email);

            List<Cliente> lista = query.getResultList();

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
}