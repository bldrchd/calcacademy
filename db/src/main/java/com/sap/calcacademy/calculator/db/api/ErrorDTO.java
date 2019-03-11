package com.sap.calcacademy.calculator.db.api;

public class ErrorDTO {

	private String errorMessage;
	
	public ErrorDTO() {
	}

	public ErrorDTO(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "ErrorDTO [error=" + errorMessage + "]";
	}
	
}
