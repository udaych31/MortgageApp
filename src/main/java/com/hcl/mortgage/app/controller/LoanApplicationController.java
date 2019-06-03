package com.hcl.mortgage.app.controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.mortgage.app.dto.ApplicationQueueListResponse;
import com.hcl.mortgage.app.dto.ApplicationRequest;
import com.hcl.mortgage.app.dto.ApplicationResponse;
import com.hcl.mortgage.app.dto.CreateResponse;
import com.hcl.mortgage.app.dto.RequestPojo;
import com.hcl.mortgage.app.service.LoanApplicationService;

@RestController
@CrossOrigin
@RequestMapping("/loan")
public class LoanApplicationController {
	
	private static final Logger logger=LogManager.getLogger(LoanApplicationController.class);
	

	@Autowired
	LoanApplicationService loanApplicationService;
	
	@PutMapping("/updateCreditStatus")
	public ApplicationResponse updateCreditStatus(@RequestBody ApplicationRequest request) {
		
		  ApplicationResponse response=loanApplicationService.updateStatus(request);
		  return response;
		
		
		
	}

	
	@GetMapping("/getQueueList")
	public ApplicationQueueListResponse getAllApplications() {
		logger.info(this.getClass().getName()+" - getAllApplications method is calling ...! ");
		return loanApplicationService.getAllLoanApplications();
	}
	
	@PostMapping("/createapplication")
	public ResponseEntity<CreateResponse> createapplication(@RequestBody RequestPojo requestPojo)
	{
		 return loanApplicationService.createapplication(requestPojo);
		
	}

}
