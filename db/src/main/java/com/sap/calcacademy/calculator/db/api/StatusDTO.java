package com.sap.calcacademy.calculator.db.api;

public class StatusDTO {

	private String status;

	public StatusDTO() {
		
	}
	
	public StatusDTO(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "StatusDTO [status=" + status + "]";
	}
	
}
