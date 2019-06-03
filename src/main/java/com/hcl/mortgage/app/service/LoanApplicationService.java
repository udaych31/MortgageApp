package com.hcl.mortgage.app.service;

import org.springframework.http.ResponseEntity;

import com.hcl.mortgage.app.dto.ApplicationQueueListResponse;
import com.hcl.mortgage.app.dto.ApplicationRequest;
import com.hcl.mortgage.app.dto.ApplicationResponse;
import com.hcl.mortgage.app.dto.CreateResponse;
import com.hcl.mortgage.app.dto.LoanEligibleRequest;
import com.hcl.mortgage.app.dto.LoanEligibleResponse;
import com.hcl.mortgage.app.dto.RequestPojo;
import com.hcl.mortgage.app.dto.ViewApplicationResponse;
import com.hcl.mortgage.app.util.LoanServiceException;


public interface LoanApplicationService {
	
	 public ApplicationResponse updateApplication(ApplicationRequest request);
	
	public ApplicationQueueListResponse getAllLoanApplications();

	public ResponseEntity<CreateResponse> createapplication(RequestPojo requestPojo);

	public ViewApplicationResponse viewApplicationByApplicationId(Long id);
	
	public LoanEligibleResponse checkEligibilityForLoan(LoanEligibleRequest request) throws LoanServiceException;
}
