package com.hcl.mortgage.app.service;

import com.hcl.mortgage.app.dto.ApplicationQueueListResponse;
import com.hcl.mortgage.app.dto.CreateResponse;

import org.springframework.http.ResponseEntity;

import com.hcl.mortgage.app.dto.RequestPojo;

public interface LoanApplicationService {
	
	public ApplicationQueueListResponse getAllLoanApplications();

	ResponseEntity<CreateResponse> createapplication(RequestPojo requestPojo);

}
