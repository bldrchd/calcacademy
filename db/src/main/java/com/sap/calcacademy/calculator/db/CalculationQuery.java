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

    public CalculationQuery(CalculationDAO dao) {
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
    public void insertCalculation(String expression, String result) throws DBFailureException {
        try {
            dao.insert(expression, result);
        } catch (Exception e) {
            throw new DBFailureException(ErrorMessage.INSERT_ERROR, e);
        }
    }


    @Override
    public void deleteCalculationById(int id) throws DBFailureException {
        try {
            dao.deleteById(id);
        } catch (Exception e) {
            throw new DBFailureException(ErrorMessage.DELETE_ID_ERROR, e);
        }
        
    }

    @Override
    public void deleteAll() throws DBFailureException {
        try {
            dao.deleteAll();
        } catch (Exception e) {
            throw new DBFailureException(ErrorMessage.DELETE_ERROR, e);
        }
        
    }


    @Override
    public void setResultToExpression(int expressionID, String result) throws DBFailureException {
        try {
            dao.setResult(expressionID, result);
        } catch (Exception e) {
            throw new DBFailureException(ErrorMessage.SET_ERROR, e);
        }
        
    }

    @Override
    public Calculation getCalculationById(int id) {
        return dao.getById(id);
    }
}