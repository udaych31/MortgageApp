package com.hcl.mortgage.app.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hcl.mortgage.app.dto.CreateResponse;
import com.hcl.mortgage.app.dto.RequestPojo;
import com.hcl.mortgage.app.entity.LoanDetails;
import com.hcl.mortgage.app.repository.LoanDetailRepository;

@Service
public class LoanApplicationServiceImpl implements LoanApplicationService {

	@Autowired
	LoanDetailRepository loanDetailRepository;
	@Override
	public ResponseEntity<CreateResponse> createapplication(RequestPojo requestPojo) {
		LoanDetails loanDetails=new LoanDetails();
		loanDetails.setApplicantIncome(requestPojo.getApplicantIncome());
		loanDetails.setAddress(requestPojo.getAddress());
		loanDetails.setFirstName(requestPojo.getFirstName());
		loanDetails.setLastName(requestPojo.getLastName());
		Date timeCreated=new Date();
		loanDetails.setTimeCreated(timeCreated);
		loanDetails.setCreditStatus("new");
		loanDetails.setLoanAmount(requestPojo.getLoanAmount());
		loanDetailRepository.save(loanDetails);
		CreateResponse response=new CreateResponse();
		response.setMessage("loanDetail added succesfully");
		return ResponseEntity.status(201).body(response);

	}

}
