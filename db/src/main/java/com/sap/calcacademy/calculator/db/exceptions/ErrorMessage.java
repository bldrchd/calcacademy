package com.sap.calcacademy.calculator.db.exceptions;

public enum ErrorMessage {
    CONNECTION_FAILURE("Cannot connect to the DB.");
    
    private String message;

    private ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
