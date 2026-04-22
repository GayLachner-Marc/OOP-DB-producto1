package com.compilers.onlinestore.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaDbUtil {

    private static final EntityManagerFactory emf
            = Persistence.createEntityManagerFactory("OnlineStorePU");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
