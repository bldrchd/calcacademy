package com.sap.calcacademy.calculator.api.domain.models;


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
