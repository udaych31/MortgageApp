package com.hcl.mortgage.app.service;

import org.springframework.http.ResponseEntity;

import com.hcl.mortgage.app.dto.ApplicationQueueListResponse;
import com.hcl.mortgage.app.dto.ApplicationRequest;
import com.hcl.mortgage.app.dto.ApplicationResponse;
import com.hcl.mortgage.app.dto.CreateResponse;
import com.hcl.mortgage.app.dto.RequestPojo;
import com.hcl.mortgage.app.dto.ViewApplicationResponse;


public interface LoanApplicationService {
	
	 public ApplicationResponse updateStatus(ApplicationRequest request);
	
	public ApplicationQueueListResponse getAllLoanApplications();

	public ResponseEntity<CreateResponse> createapplication(RequestPojo requestPojo);

	public ViewApplicationResponse viewApplicationByApplicationId(Long id);
}
