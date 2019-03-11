package com.sap.calcacademy.calculator.db;

import java.util.List;

import com.sap.calcacademy.calculator.db.api.IQuery;
import com.sap.calcacademy.calculator.db.exceptions.DBFailureException;
import com.sap.calcacademy.calculator.db.exceptions.ErrorMessage;
import com.sap.calcacademy.calculator.db.model.Calculation;

public class CalculationQuery implements IQuery {

    private CalculationDAO dao;

    public CalculationQuery() {
        DBConnection connection = new DBConnection();
        this.dao = new CalculationDAO(connection.getEntityManager());
    }

    CalculationQuery(CalculationDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<Calculation> getAllCalculations() throws DBFailureException {
        try {
            return dao.getAll();
        } catch (Exception e) {
            throw new DBFailureException(ErrorMessage.CONNECTION_FAILURE, e);
        }
    }

    @Override
    public void insertCalculation(Calculation c) throws DBFailureException {
        try {
            dao.insertCalculation(c);
        } catch (Exception e) {
            throw new DBFailureException(ErrorMessage.CONNECTION_FAILURE, e);
        }
    }


    @Override
    public void deleteCalculationById(int id) throws DBFailureException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAll() throws DBFailureException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<Calculation> getNotCalculatedExpressions() throws DBFailureException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setResultToExpression(int id, String string) throws DBFailureException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Calculation getCalculationById(int id) {
        // TODO Auto-generated method stub
        return null;
    }
}