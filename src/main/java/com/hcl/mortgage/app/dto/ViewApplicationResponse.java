package com.hcl.mortgage.app.dto;

import java.io.Serializable;
import java.util.List;

public class ViewApplicationResponse implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	private List<LoanDTO> loanList;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<LoanDTO> getLoanList() {
		return loanList;
	}

	public void setLoanList(List<LoanDTO> loanList) {
		this.loanList = loanList;
	}
}
