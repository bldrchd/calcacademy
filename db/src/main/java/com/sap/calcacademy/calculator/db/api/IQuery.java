package com.sap.calcacademy.calculator.db.api;

import java.util.List;

import com.sap.calcacademy.calculator.db.exceptions.DBFailureException;
import com.sap.calcacademy.calculator.db.model.Calculation;

public interface IQuery {

    /**
     * 
     * @return List of type Calculation, filled with all rows from the table or
     *         null if error occurs
     * @throws DBFailureException
     *             if error with the DB or EntityManager occurs and the query
     *             cannot be processed
     */
    List<Calculation> getAllCalculations() throws DBFailureException;

    /**
     *  
     * @param expression
     *            calculation to be inserted in the database
     * @param result
     *            the result from the expression to be inserted
     * @throws DBFailureException
     *             if error with the EntityManager occurs or the entity already exist 
     */
    void insertCalculation(Calculation calculation) throws DBFailureException;

    /**
     * @param id
     *            id of calculation to be deleted
     * @throws DBFailureException
     *             if calculation with this id doesn't exist or db is empty
     */
    void deleteCalculationById(int id) throws DBFailureException;

    /**
     * 
     * @throws DBFailureException
     *             if error with the DB or EntityManager occurs and the query
     *             cannot be processed
     */
    void deleteAll() throws DBFailureException;
    
    /**
     * 
     * @param id id of expression which result will be set
     * @param string to be set
     * @throws DBFailureException if can't connect to the db
     */
    void setResultToExpression(int id, String string) throws DBFailureException;
    
    Calculation getCalculationById(int id);
}