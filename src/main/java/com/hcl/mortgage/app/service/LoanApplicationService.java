package com.hcl.mortgage.app.service;

import org.springframework.http.ResponseEntity;

import com.hcl.mortgage.app.dto.RequestPojo;
import com.hcl.mortgage.app.dto.createResponse;

public interface LoanApplicationService {

	ResponseEntity<createResponse> createapplication(RequestPojo requestPojo);

}
