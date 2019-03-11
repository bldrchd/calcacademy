package com.sap.calcacademy.calculatordb;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.sap.calcacademy.calculatordb.model.Calculation;

public class CalculationOps {
    private final EntityManager em;

    public CalculationOps(EntityManager em) {
        this.em = em;
    }
    public List<Calculation> getAll() {
        Query q = em.createNamedQuery("Calculation.findAll");
        List<Calculation> all = q.getResultList();
        return all;
    }
}
