package com.hcl.mortgage.app.service;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.http.ResponseEntity;
import com.hcl.mortgage.app.dto.CreateResponse;
import com.hcl.mortgage.app.dto.RequestPojo;
import com.hcl.mortgage.app.entity.LoanDetails;
import com.hcl.mortgage.app.repository.LoanDetailRepository;

@RunWith(MockitoJUnitRunner.class)
public class LoanApplicationServiceImplTest {
	
	@InjectMocks
	LoanApplicationServiceImpl LoanApplicationServiceImpl;
	@Mock
	LoanDetailRepository loanDetailRepository;
	
	
	@Test
	public void LoanApplicationServiceImpl() {
		
		
		RequestPojo requestPojo=new RequestPojo();
		requestPojo.setAddress("khan");
		requestPojo.setApplicantIncome(10000.0);
		requestPojo.setFirstName("khan");
		requestPojo.setLastName("khan");
		requestPojo.setLoanAmount(1000.0);
		
		
		LoanDetails details=new LoanDetails();
		details.setAddress(requestPojo.getAddress());
		details.setApplicantIncome(requestPojo.getApplicantIncome());
		details.setFirstName(requestPojo.getFirstName());
		details.setLastName(requestPojo.getLastName());
		details.setLoanAmount(requestPojo.getLoanAmount());
		details.setLoanId(1L);
		
		ResponseEntity<CreateResponse> result=LoanApplicationServiceImpl.createapplication(requestPojo);
	   
		assertEquals(201,result.getStatusCodeValue());
	
		
	
		
	}
	

}
