package com.hcl.mortgage.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.mortgage.app.dto.ApplicationRequest;
import com.hcl.mortgage.app.dto.ApplicationResponse;
import com.hcl.mortgage.app.service.LoanApplicationService;

@RestController
@CrossOrigin
public class LoanApplicationController {
	
	@Autowired
	LoanApplicationService loanApplicationService;
	
	@PutMapping("/updateCreditStatus")
	public ApplicationResponse updateCreditStatus(@RequestBody ApplicationRequest request) {
		
		  ApplicationResponse response=loanApplicationService.updateStatus(request);
		  return response;
		
		
		
	}

}
