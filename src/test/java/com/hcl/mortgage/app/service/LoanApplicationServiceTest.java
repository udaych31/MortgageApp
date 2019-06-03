package com.hcl.mortgage.app.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.mortgage.app.dto.ApplicationQueueListResponse;
import com.hcl.mortgage.app.dto.QueueListDto;
import com.hcl.mortgage.app.entity.LoanDetails;
import com.hcl.mortgage.app.repository.LoanDetailRepository;

@RunWith(MockitoJUnitRunner.class)
public class LoanApplicationServiceTest {
	
	private static final Logger logger = LogManager.getLogger(LoanApplicationServiceTest.class);
	
	@Mock
	private LoanDetailRepository loanRepository;
	
	@InjectMocks
	private LoanApplicationServiceImpl loanService;
	
	@Test
	public void getAllApplications() {
		
		LoanDetails loanDetails=new LoanDetails();
		loanDetails.setAddress("tpt");
		loanDetails.setApplicantIncome(1000.0);
		loanDetails.setCreditStatus("new");
		loanDetails.setFirstName("uday");
		loanDetails.setLastName("ch");
		loanDetails.setLoanAmount(3000.0);
		loanDetails.setLoanId(1l);
		
		List<LoanDetails> loanList=new ArrayList<>();
		loanList.add(loanDetails);
		when(loanRepository.findAll()).thenReturn(loanList);
		ApplicationQueueListResponse response = loanService.getAllLoanApplications();
		Map<String, List<QueueListDto>> queueList = response.getQueueList();
		assertTrue(queueList.containsKey("NEW"));
		List<QueueListDto> list = queueList.get("NEW");
		QueueListDto queueListDto = list.get(0);
		logger.info(queueListDto.toString());
		Long size=Long.valueOf(""+list.size());
		Long val=1L;
		assertEquals(val, size);
		
		
	}
	

}
