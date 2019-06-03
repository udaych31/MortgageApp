package com.hcl.mortgage.app.service;

import com.hcl.mortgage.app.dto.ApplicationRequest;
import com.hcl.mortgage.app.dto.ApplicationResponse;

public interface LoanApplicationService {
	
	 public ApplicationResponse updateStatus(ApplicationRequest request);

}
