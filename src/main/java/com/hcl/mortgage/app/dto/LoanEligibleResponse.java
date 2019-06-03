package com.hcl.mortgage.app.dto;

import java.io.Serializable;

import com.hcl.mortgage.app.util.ExceptionResponse;

public class LoanEligibleResponse extends ExceptionResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String status;
	
	public LoanEligibleResponse() {
		super();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
}
