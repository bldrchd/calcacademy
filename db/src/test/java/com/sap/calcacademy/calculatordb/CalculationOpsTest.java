package com.sap.calcacademy.calculatordb;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sap.calcacademy.calculator.db.CalculationOps;
import com.sap.calcacademy.calculator.db.model.Calculation;

public class CalculationOpsTest {
    @Mock
    private EntityManager em;

    @Mock
    private EntityTransaction mockedTransaction;

    @Mock
    private Query mockedQuery;

    private Calculation calculation;
    private String expression = "";
    private String result = "";
    private int id = 1;

    private CalculationOps cops;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        calculation = new Calculation();
        cops = new CalculationOps(em);
    }

    @Test
    public void testGetAll() {
        List<Calculation> expected = new ArrayList<Calculation>();
        when(em.createNamedQuery("Calculation.findAll")).thenReturn(mockedQuery);
        when(mockedQuery.getResultList()).thenReturn(expected);

        List<Calculation> actual = cops.getAll();

        verify(em).createNamedQuery("Calculation.findAll");
        verify(mockedQuery).getResultList();
        assertEquals(expected, actual);
    }
}
