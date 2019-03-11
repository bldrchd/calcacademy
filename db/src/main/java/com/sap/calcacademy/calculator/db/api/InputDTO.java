package com.sap.calcacademy.calculator.db.api;


public class InputDTO {
	
	private String input;
	
	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	@Override
	public String toString() {
		return "InputDTO [input = " + input + "]";
	}
	
}
