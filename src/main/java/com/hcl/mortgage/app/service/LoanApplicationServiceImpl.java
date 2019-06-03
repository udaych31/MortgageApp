package com.hcl.mortgage.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.mortgage.app.dto.LoanDTO;
import com.hcl.mortgage.app.dto.ViewApplicationResponse;
import com.hcl.mortgage.app.entity.LoanDetails;
import com.hcl.mortgage.app.repository.LoanDetailRepository;

@Service
public class LoanApplicationServiceImpl implements LoanApplicationService {
	
	@Autowired
	private LoanDetailRepository loanDetailRepository;


	@Override
	public ViewApplicationResponse viewApplicationByApplicationId(Long id) {
		ViewApplicationResponse response = new ViewApplicationResponse();
		List<LoanDTO> dtoList=new ArrayList<>();
		    List<LoanDetails> loanDetails =loanDetailRepository.findByLoanId(id);
		    loanDetails.stream().forEach(loan->{
		    	    LoanDTO loanDTO=new LoanDTO();
				    loanDTO.setAddress(loan.getAddress());
				    loanDTO.setApplicantIncome(loan.getApplicantIncome());
				    loanDTO.setCreditStatus(loan.getCreditStatus());
				    loanDTO.setFirstName(loan.getFirstName());
				    loanDTO.setId(loan.getLoanId());
				    loanDTO.setLastName(loan.getLastName());
				    loanDTO.setLoanAmount(loan.getLoanAmount());
				    loanDTO.setTimeCreated(loan.getTimeCreated());
				    dtoList.add(loanDTO);
		    });
		    response.setMessage("Success!!!");
		    response.setLoanList(dtoList);
		    if(dtoList.isEmpty()) {
		    	response.setMessage("Id not found on server");
		    }
		return response;
	}
}
