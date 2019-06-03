package com.hcl.mortgage.app.service;

import java.util.Date;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.mortgage.app.dto.ApplicationRequest;
import com.hcl.mortgage.app.dto.ApplicationResponse;
import com.hcl.mortgage.app.entity.LoanDetails;
import com.hcl.mortgage.app.repository.LoanDetailRepository;



@RunWith(MockitoJUnitRunner.class)
public class LoanApplicationServiceTests {
	
	
	@Mock
	LoanDetailRepository loanDetailRepository;

	@InjectMocks
	LoanApplicationServiceImpl loanServiceImpl;
	
	@Test
	public void testUpdateCreditStatusWithNew() {
		
		Optional<LoanDetails> optional=Optional.empty();
		
		ApplicationRequest request=new ApplicationRequest();
		
		request.setAddress("Bangalore");
		request.setApplicantIncome(30000.0);
		request.setFirstName("suma");
		request.setLastName("larna");
		//request.setTimeCreated(new Date());
		request.setLoanAmount(320000.0);
		//request.setCreditStatus("NEW");
		request.setLoanId(1L);
		
		LoanDetails details=new LoanDetails();
		
		details.setAddress(request.getAddress());
		details.setApplicantIncome(request.getApplicantIncome());
		details.setFirstName(request.getFirstName());
		details.setLastName(request.getLastName());
		details.setLoanAmount(request.getLoanAmount());
		//details.setTimeCreated(request.getTimeCreated());
		details.setCreditStatus("NEW");
		details.setLoanId(request.getLoanId());
		
		optional=Optional.of(details);
		
		Mockito.when(loanDetailRepository.findById(details.getLoanId())).thenReturn(optional);
		
		ApplicationResponse response1=new ApplicationResponse();
		response1.setStatusCode(200);
		response1.setMessage("Update application successfully with reference number: "+1L);
		
		ApplicationResponse response=loanServiceImpl.updateApplication(request);
		
		Assert.assertEquals(response.toString(), response1.toString());
	}
	

	
	@Test
	public void testUpdateCreditStatusWithApproved() {
		
		Optional<LoanDetails> optional=Optional.empty();
		
		ApplicationRequest request=new ApplicationRequest();
		
		request.setCreditStatus("Approved");
		request.setLoanId(1L);
		
		LoanDetails details=new LoanDetails();
		
		details.setCreditStatus("NEW");
		details.setLoanId(request.getLoanId());
		
		optional=Optional.of(details);
		
		Mockito.when(loanDetailRepository.findById(details.getLoanId())).thenReturn(optional);
		
		ApplicationResponse response1=new ApplicationResponse();
		response1.setStatusCode(200);
		response1.setMessage("APPROVED successfully with reference number: "+1L);
		
		ApplicationResponse response=loanServiceImpl.updateApplication(request);
		
		Assert.assertEquals(response.toString(), response1.toString());
	}
	

	
	
	
	@Test
	public void testUpdateCreditStatusWithFunded() {
		
		Optional<LoanDetails> optional=Optional.empty();
		
		ApplicationRequest request=new ApplicationRequest();
		
		
		request.setCreditStatus("Funded");
		request.setLoanId(1L);
		
		LoanDetails details=new LoanDetails();
		
		
		details.setCreditStatus("Approved");
		details.setLoanId(request.getLoanId());
		
		optional=Optional.of(details);
		
		Mockito.when(loanDetailRepository.findById(details.getLoanId())).thenReturn(optional);
		
		ApplicationResponse response1=new ApplicationResponse();
		response1.setStatusCode(200);
		response1.setMessage("FUNDED successfully with reference number: "+1L);
		
		ApplicationResponse response=loanServiceImpl.updateApplication(request);
		
		Assert.assertEquals(response.toString(), response1.toString());
	}
	

	
	
	
	
	
}
