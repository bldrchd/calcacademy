package com.sap.calcacademy.calculator.db.exceptions;

import com.sap.calcacademy.calculator.db.exceptions.ErrorMessage;

/**
 * 
 * The DBFailureException wraps standard Java exceptions which are thrown 
 * when there is a problem with the database and enriches them with a custom error message.
 *
 */
public class DBFailureException extends Exception {
    private static final long serialVersionUID = 1L;

    public DBFailureException(ErrorMessage message, Exception e) {
        super(message.getMessage() + "Root cause: " + e.getMessage(), e);
    }
}
