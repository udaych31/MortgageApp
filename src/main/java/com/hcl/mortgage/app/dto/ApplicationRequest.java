package com.hcl.mortgage.app.dto;

import java.io.Serializable;
import java.util.Date;

public class ApplicationRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long loanId;
	private String firstName;
	private String lastName;
	private String address;
	private String creditStatus;
	private Double applicantIncome;
	private Double loanAmount;

	public Long getLoanId() {
		return loanId;
	}

	public void setLoanId(Long loanId) {
		this.loanId = loanId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCreditStatus() {
		return creditStatus;
	}

	public void setCreditStatus(String creditStatus) {
		this.creditStatus = creditStatus;
	}

	public Double getApplicantIncome() {
		return applicantIncome;
	}

	public void setApplicantIncome(Double applicantIncome) {
		this.applicantIncome = applicantIncome;
	}

	public Double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(Double loanAmount) {
		this.loanAmount = loanAmount;
	}


}
