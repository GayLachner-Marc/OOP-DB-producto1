package com.compilers.onlinestore.dao;

import com.compilers.onlinestore.model.Articulos.Articulo;
import com.compilers.onlinestore.util.JpaDbUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ArticuloDAOImpl implements ArticuloDAO {

    @Override
    public void crear(Articulo a) {

        EntityManager em = JpaDbUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            em.persist(a);

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
    public void eliminar(String codigo) {

        EntityManager em = JpaDbUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            TypedQuery<Articulo> query = em.createQuery(
                    "SELECT a FROM Articulo a WHERE a.codigo = :codigo",
                    Articulo.class
            );

            query.setParameter("codigo", codigo);

            List<Articulo> lista = query.getResultList();

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

    @Override
    public void actualizar(Articulo a) {

        EntityManager em = JpaDbUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            em.merge(a);

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
    public Articulo obtenerPorCodigo(String codigo) {

        EntityManager em = JpaDbUtil.getEntityManager();

        try {

            TypedQuery<Articulo> query = em.createQuery(
                    "SELECT a FROM Articulo a WHERE a.codigo = :codigo",
                    Articulo.class
            );

            query.setParameter("codigo", codigo);

            List<Articulo> lista = query.getResultList();

            if (lista.isEmpty()) {
                return null;
            }

            return lista.get(0);

        } finally {
            em.close();
        }
    }

    @Override
    public List<Articulo> obtenerTodos() {

        EntityManager em = JpaDbUtil.getEntityManager();

        try {

            TypedQuery<Articulo> query = em.createQuery(
                    "SELECT a FROM Articulo a",
                    Articulo.class
            );

            return query.getResultList();

        } finally {
            em.close();
        }
    }
}