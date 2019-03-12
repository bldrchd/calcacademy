package com.sap.calcacademy.calculator.db.exceptions;

public enum ErrorMessage {
    CONNECTION_FAILURE("Cannot connect to the DB."),
    INSERT_ERROR("Cannot access the DB to insert the calculation."),
    DELETE_ID_ERROR("Calculation with this id doesn't exist"),
    DELETE_ERROR("Cannot delete all records"),
    SET_ERROR("Cannot connect or the expression doesn't exist");
    
    private String message;

    private ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
