package com.hcl.mortgage.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.mortgage.app.dto.CreateResponse;
import com.hcl.mortgage.app.dto.RequestPojo;
import com.hcl.mortgage.app.service.LoanApplicationService;

@RestController
@CrossOrigin
public class LoanApplicationController {

	@Autowired
	LoanApplicationService loanApplicationService;
	@PostMapping("/createapplication")
	public ResponseEntity<CreateResponse> createapplication(@RequestBody RequestPojo requestPojo)
	{
		 return loanApplicationService.createapplication(requestPojo);
		
	}
}
