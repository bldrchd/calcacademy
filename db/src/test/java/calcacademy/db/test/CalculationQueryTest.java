package calcacademy.db.test;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.sap.calcacademy.calculator.db.CalculationDAO;
import com.sap.calcacademy.calculator.db.CalculationQuery;
import com.sap.calcacademy.calculator.db.exceptions.DBFailureException;
import com.sap.calcacademy.calculator.db.model.Calculation;

public class CalculationQueryTest {

    @Mock
    private CalculationDAO dao;

    private CalculationQuery calcQuery;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        calcQuery = new CalculationQuery(dao);
    }

    @Test
    public void testGetAllCalculations() throws DBFailureException {
        calcQuery.getAllCalculations();
        verify(dao).getAll();
    }
    

    @Test(expected=DBFailureException.class)
    public void testGetAllCalculations_Error() throws DBFailureException {
        doThrow(Exception.class).when(dao).getAll();
        calcQuery.getAllCalculations();
        verify(dao).getAll();
    }

    @Test
    public void testInsertCalculation() throws DBFailureException {
        calcQuery.insertCalculation(anyString(), anyString());
        verify(dao).insert(anyString(), anyString());
    }
    
    @Test
    public void testInsertCalculation_withObject() throws DBFailureException {
        Calculation empty = new Calculation();
        calcQuery.insertCalculation(empty);
        verify(dao).insertCalculation(empty);
    }
    
    @Test(expected=DBFailureException.class)
    public void testInsertCalculation_Error() throws DBFailureException {
        doThrow(Exception.class).when(dao).insert(anyString(),  anyString());
        calcQuery.insertCalculation(anyString(), anyString());
        verify(dao).insert(anyString(),  anyString());
    }
    

    @Test(expected=DBFailureException.class)
    public void testInsertCalculationError_withObject() throws DBFailureException {
        Calculation calculation = new Calculation();
        doThrow(Exception.class).when(dao).insertCalculation(calculation);
        calcQuery.insertCalculation(calculation);
        verify(dao).insertCalculation(calculation);
    }

    @Test
    public void testDeleteCalculationById() throws DBFailureException {
        calcQuery.deleteCalculationById(anyInt());
        verify(dao).deleteById(anyInt());
    }
    
    @Test(expected=DBFailureException.class)
    public void testDeleteCalculationByIdError() throws DBFailureException {
        doThrow(IllegalArgumentException.class).when(dao).deleteById(anyInt());
        calcQuery.deleteCalculationById(anyInt());
        verify(dao).deleteById(anyInt());
    }
    
    @Test
    public void testDeleteAllCalculations() throws DBFailureException {
        calcQuery.deleteAll();
        verify(dao).deleteAll();
    }
    
    @Test(expected=DBFailureException.class)
    public void testDeleteAllCalculations_Error() throws DBFailureException {
        doThrow(IllegalArgumentException.class).when(dao).deleteAll();
        calcQuery.deleteAll();
        verify(dao).deleteAll();
    }
    
    @Test
    public void setResultToExpressionById() throws DBFailureException {
        calcQuery.setResultToExpression(Mockito.anyInt(), Mockito.anyString());
        verify(dao).setResult(Mockito.anyInt(), Mockito.anyString());
    }
    
    @Test(expected=DBFailureException.class)
    public void setResultToExpressionById_Error() throws DBFailureException {
        doThrow(IllegalArgumentException.class).when(dao).setResult(Mockito.anyInt(), Mockito.anyString());
        calcQuery.setResultToExpression(Mockito.anyInt(), Mockito.anyString());
        verify(dao).setResult(Mockito.anyInt(), Mockito.anyString());
    }

}
