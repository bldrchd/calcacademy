package com.sap.calcacademy.calculator.api.domain.models;

public class ExpressionDTO {
	private String input;
	private String result;
	
	public double getResultAsDouble() {
		return Double.parseDouble(result);
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "ExpressionDTO [input=" + input + ", result=" + result + "]";
	}
	
}
