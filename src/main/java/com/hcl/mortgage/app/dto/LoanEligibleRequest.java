package com.hcl.mortgage.app.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class LoanEligibleRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "Application number is required")
	private Long applicationNo;

	@NotNull(message = "Loan amount is required")
	private Double loanAmount;

	@NotNull(message = "Applicant income is required")
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
