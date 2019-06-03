package com.hcl.mortgage.app.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.mortgage.app.entity.LoanDetails;
import com.hcl.mortgage.app.repository.LoanDetailRepository;

@RunWith(MockitoJUnitRunner.class)
public class LoanApplicationServiceTest {
	
	@Mock
	private LoanDetailRepository loanRepository;
	
	@InjectMocks
	private LoanApplicationService service;
	
	@Test
	public void getAllApplications() {
		
		LoanDetails loanDetails=new LoanDetails();
		loanDetails.setAddress("tpt");
		loanDetails.setApplicantIncome(1000.0);
		loanDetails.setCreditStatus("NEW");
		loanDetails.setFirstName("uday");
		loanDetails.setLastName("ch");
		loanDetails.setLoanAmount(3000.0);
		loanDetails.setLoanId(1l);
		
		//List<LoanDetails> 
		
		
	}
	

}
