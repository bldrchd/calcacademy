package com.sap.calcacademy.calculatordb.exceptions;

public class DBFailureException extends Exception {
    private static final long serialVersionUID = 1L;

    public DBFailureException(ErrorMessage message, Exception e) {
        super(message.getMessage() + "Root cause: " + e.getMessage(), e);
    }
}
