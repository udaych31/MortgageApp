package com.hcl.mortgage.app.service.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.mortgage.app.dto.LoanDTO;
import com.hcl.mortgage.app.entity.LoanDetails;
import com.hcl.mortgage.app.repository.LoanDetailRepository;
import com.hcl.mortgage.app.service.LoanApplicationServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class LoanApplicationServiceTest {

	@InjectMocks
	private LoanApplicationServiceImpl loanApplicationService;
	
	@Mock
	private LoanDetailRepository loanDetailRepository;
	
	@Test
	public void viewApplicationByApplicationId() {
		LoanDTO loanDTO=new LoanDTO();	
		loanDTO.setAddress("Bangalore");
		loanDTO.setApplicantIncome(20000.0);
		loanDTO.setCreditStatus("Approved");
		loanDTO.setFirstName("Sushil");
		loanDTO.setId(1L);
		loanDTO.setLastName("tiwari");
		loanDTO.setLoanAmount(100000.0);
		loanDTO.setTimeCreated(new Date());
		long id=loanDTO.getId();
		
		List<LoanDetails> loanDetailsList=new ArrayList<LoanDetails>();
		
        LoanDetails loanDetails=new LoanDetails();	
		loanDetails.setAddress("Bangalore");
		loanDetails.setApplicantIncome(20000.0);
		loanDetails.setCreditStatus("Approved");
		loanDetails.setFirstName("Sushil");
		loanDetails.setLoanId(1L);
		loanDetails.setLastName("tiwari");
		loanDetails.setLoanAmount(100000.0);
		loanDetails.setTimeCreated(new Date());
		loanDetails.setLoanId(1L);
		loanDetailsList.add(loanDetails);
		
		when(loanDetailRepository.findByLoanId(id)).thenReturn(loanDetailsList);
		assertThat(loanApplicationService.viewApplicationByApplicationId(id)).isNotEqualTo(loanDTO);
	}
}
