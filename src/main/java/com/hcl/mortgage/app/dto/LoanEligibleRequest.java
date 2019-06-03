package com.hcl.mortgage.app.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.NumberFormat;

public class LoanEligibleRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "Application number is required")
	@NumberFormat(pattern = "${0-9}")
	private Long applicationNo;
	
	@NotNull(message = "Loan amount is required")
	@NumberFormat(pattern = "${0-9}")
	private Double loanAmount;
	
	@NotNull(message = "Applicant income is required")
	@NumberFormat(pattern = "${0-9}")
	private Double applicantIncome;
	
	public LoanEligibleRequest() {
		super();
	}

	public Long getApplicationNo() {
		return applicationNo;
	}

	public void setApplicationNo(Long applicationNo) {
		this.applicationNo = applicationNo;
	}

	public Double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(Double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public Double getApplicantIncome() {
		return applicantIncome;
	}

	public void setApplicantIncome(Double applicantIncome) {
		this.applicantIncome = applicantIncome;
	}

	
}
