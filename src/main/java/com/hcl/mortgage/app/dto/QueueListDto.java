package com.hcl.mortgage.app.dto;

import java.io.Serializable;
import java.util.Date;

public class QueueListDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long loanId;
	
	private String fullName;
	
	private Double loanAmount;
	
	public QueueListDto() {
		super();
	}

	public Long getLoanId() {
		return loanId;
	}

	public void setLoanId(Long loanId) {
		this.loanId = loanId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(Double loanAmount) {
		this.loanAmount = loanAmount;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LoanDto [loanId=");
		builder.append(loanId);
		builder.append(", fullName=");
		builder.append(fullName);
		builder.append(", loanAmount=");
		builder.append(loanAmount);
		builder.append("]");
		return builder.toString();
	}

	

}
