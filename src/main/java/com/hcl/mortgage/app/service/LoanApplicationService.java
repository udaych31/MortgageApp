package com.hcl.mortgage.app.service;

import org.springframework.http.ResponseEntity;

import com.hcl.mortgage.app.dto.CreateResponse;
import com.hcl.mortgage.app.dto.RequestPojo;

public interface LoanApplicationService {

	ResponseEntity<CreateResponse> createapplication(RequestPojo requestPojo);

}
