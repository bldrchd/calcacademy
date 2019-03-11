package com.sap.calcacademy.calculator.db;

import java.util.List;

import com.sap.calcacademy.calculator.db.exceptions.DBFailureException;
import com.sap.calcacademy.calculator.db.exceptions.ErrorMessage;
import com.sap.calcacademy.calculator.db.model.Calculation;

public class MyQuery {
    private CalculationOps cops;
    
    public MyQuery() {
        DBConnection connection = new DBConnection();
        this.cops = new CalculationOps(connection.getEntityManager());
    }
    
    MyQuery(CalculationOps cops){
        this.cops = cops;
    }
    
    public List<Calculation> getAllCalculations() throws DBFailureException {
        try {
            return cops.getAll();
        } catch (Exception e) {
            throw new DBFailureException(ErrorMessage.CONNECTION_FAILURE, e);
        }
    }
}
