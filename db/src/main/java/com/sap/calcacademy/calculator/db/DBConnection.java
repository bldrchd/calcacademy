package com.sap.calcacademy.calculator.db;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBConnection {
    private static final String PERSISTENCE_UNIT_NAME = "db";
    private static EntityManagerFactory factory;
    private final EntityManager em;

    public DBConnection() {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        em = factory.createEntityManager();
    }

    DBConnection(String persistenceUnit, EntityManager em) {
        factory =  Persistence.createEntityManagerFactory(persistenceUnit);
        this.em = em;
    }

    public EntityManager getEntityManager() {
        return em;
    }
    
    public void closeDbConnection() {
        factory.close();
    }
}
