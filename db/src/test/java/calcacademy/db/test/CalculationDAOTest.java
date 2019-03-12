package calcacademy.db.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sap.calcacademy.calculator.db.CalculationDAO;
import com.sap.calcacademy.calculator.db.model.Calculation;

public class CalculationDAOTest {

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

    private CalculationDAO dao;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        calculation = new Calculation();
        dao = new CalculationDAO(em);
    }

    @Test
    public void testGetAll() {
        List<Calculation> expected = new ArrayList<>();
        when(em.createNamedQuery("Calculation.findAll")).thenReturn(mockedQuery);
        when(mockedQuery.getResultList()).thenReturn(expected);

        List<Calculation> actual = dao.getAll();

        verify(em).createNamedQuery("Calculation.findAll");
        verify(mockedQuery).getResultList();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetById() {
        when(em.find(Calculation.class, 1)).thenReturn(calculation);

        Calculation actual = dao.getById(1);

        verify(em).find(Calculation.class, 1);
        assertEquals(calculation, actual);
    }

    @Test
    public void testInsert() {
        calculation.setExpression(expression);
        calculation.setResult(result);
        when(em.getTransaction()).thenReturn(mockedTransaction);
        ArgumentCaptor<Calculation> argumentCaptor = ArgumentCaptor.forClass(Calculation.class);

        dao.insert(expression, result);

        verify(em, times(2)).getTransaction();
        verify(em).persist(argumentCaptor.capture());
        assertEquals(argumentCaptor.getValue().getExpression(), expression);
        assertEquals(argumentCaptor.getValue().getResult(), result);
    }

    @Test
    public void testInsertWrappedCalculation() {
        when(em.getTransaction()).thenReturn(mockedTransaction);
        ArgumentCaptor<Calculation> argumentCaptor = ArgumentCaptor.forClass(Calculation.class);

        dao.insertCalculation(calculation);
        verify(em, times(2)).getTransaction();
        verify(em).persist(argumentCaptor.capture());
    }

    @Test
    public void testDeleteById() {
        calculation.setId(id);
        when(em.getTransaction()).thenReturn(mockedTransaction);
        when(em.find(Calculation.class, id)).thenReturn(calculation);
        ArgumentCaptor<Calculation> argumentCaptor = ArgumentCaptor.forClass(Calculation.class);

        dao.deleteById(id);

        verify(em, times(2)).getTransaction();
        verify(em).remove(argumentCaptor.capture());
        assertEquals(argumentCaptor.getValue().getId(), id);
    }

    @Test
    public void testDeleteAll() {
        when(em.getTransaction()).thenReturn(mockedTransaction);
        when(em.createNamedQuery("Calculation.deleteAll")).thenReturn(mockedQuery);

        dao.deleteAll();

        verify(mockedQuery).executeUpdate();
    }

    @Test
    public void testSetResultById() {
        when(em.getTransaction()).thenReturn(mockedTransaction);
        when(em.createNamedQuery("Calculation.updateResultById")).thenReturn(mockedQuery);

        dao.setResult(id, result);

        verify(mockedQuery).executeUpdate();
        verify(em, times(2)).getTransaction();
    }

    @Test
    public void testSetResultByExpression() {
        when(em.getTransaction()).thenReturn(mockedTransaction);
        when(em.createNamedQuery("Calculation.updateResultByExpression")).thenReturn(mockedQuery);

        dao.setResult(expression, result);

        verify(mockedQuery).executeUpdate();
        verify(em, times(2)).getTransaction();
    }

}