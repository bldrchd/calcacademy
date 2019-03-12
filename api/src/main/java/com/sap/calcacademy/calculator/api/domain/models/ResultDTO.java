package com.sap.calcacademy.calculator.api.domain.models;

public class ResultDTO {
	
	private String result;
	
	public ResultDTO() {
	}
	
	public ResultDTO(String result) {
		this.result = result;
	}
	
	public String getResult() {
		return result;
	}
	
	public Double getResultAsDouble() {
		return Double.parseDouble(result);
	}
	
	public void setResult(String result) {
		this.result = result;
	}
	
	@Override
	public String toString() {
		return "ResultDTO [result=" + result + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.result == null) ? 0 : this.result.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResultDTO other = (ResultDTO) obj;
		if (result == null) {
			if (other.result != null)
				return false;
		} else if (!result.equals(other.result))
			return false;
		return true;
	}
	
}
