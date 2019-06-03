package com.hcl.mortgage.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.mortgage.app.dto.ViewApplicationResponse;
import com.hcl.mortgage.app.service.LoanApplicationService;

@RestController
@CrossOrigin
public class LoanApplicationController {
	
	@Autowired
	private LoanApplicationService loanApplicationService;
	
	@GetMapping("/viewApplication/{id}")
	public ViewApplicationResponse viewApplicationByApplicationId(@PathVariable long id) {
		return loanApplicationService.viewApplicationByApplicationId(id);	
	}

}
