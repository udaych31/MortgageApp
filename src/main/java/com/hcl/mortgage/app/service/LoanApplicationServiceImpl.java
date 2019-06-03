package com.hcl.mortgage.app.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hcl.mortgage.app.dto.ApplicationQueueListResponse;
import com.hcl.mortgage.app.dto.CreateResponse;
import com.hcl.mortgage.app.dto.QueueListDto;
import com.hcl.mortgage.app.dto.RequestPojo;
import com.hcl.mortgage.app.entity.LoanDetails;
import com.hcl.mortgage.app.repository.LoanDetailRepository;
import com.hcl.mortgage.app.util.LoanStatus;

@Service
public class LoanApplicationServiceImpl implements LoanApplicationService {

	private static final Logger logger = LogManager.getLogger(LoanApplicationServiceImpl.class);

	@Autowired
	private LoanDetailRepository loanRepository;

	@Override
	public ApplicationQueueListResponse getAllLoanApplications() {
		ApplicationQueueListResponse response = new ApplicationQueueListResponse();
		List<QueueListDto> newStatusList = new ArrayList<>();
		List<QueueListDto> approveStatusList = new ArrayList<>();
		List<QueueListDto> fundStatusList = new ArrayList<>();
		Map<String, List<QueueListDto>> queueList = new HashMap<>();
		try {
			List<LoanDetails> loanList = loanRepository.findAll();
			if (!loanList.isEmpty()) {
				loanList.stream().forEach(loan -> {
					if (loan != null && loan.getCreditStatus() != null
							&& loan.getCreditStatus().equalsIgnoreCase(LoanStatus.NEW.toString())) {
						QueueListDto dto = new QueueListDto();
						dto.setLoanAmount(loan.getLoanAmount());
						dto.setLoanId(loan.getLoanId());
						dto.setFullName(loan.getFirstName()+" "+loan.getLastName());
						newStatusList.add(dto);
					} else if (loan != null && loan.getCreditStatus() != null
							&& loan.getCreditStatus().equalsIgnoreCase(LoanStatus.APPROVED.toString())) {
						QueueListDto dto = new QueueListDto();
						dto.setLoanAmount(loan.getLoanAmount());
						dto.setLoanId(loan.getLoanId());
						dto.setFullName(loan.getFirstName()+" "+loan.getLastName());
						approveStatusList.add(dto);
					} else if (loan != null && loan.getCreditStatus() != null
							&& loan.getCreditStatus().equalsIgnoreCase(LoanStatus.FUNDCOMPLETED.toString())) {
						QueueListDto dto = new QueueListDto();
						dto.setLoanAmount(loan.getLoanAmount());
						dto.setLoanId(loan.getLoanId());
						dto.setFullName(loan.getFirstName()+" "+loan.getLastName());
						fundStatusList.add(dto);
					}

				});

				
				queueList.put(LoanStatus.NEW.toString(), newStatusList);
				queueList.put(LoanStatus.APPROVED.toString(), approveStatusList);
				queueList.put(LoanStatus.FUNDCOMPLETED.toString(), fundStatusList);
			}
			response.setQueueList(queueList);

		} catch (Exception e) {
			logger.error(this.getClass().getName() + " - getAllLoanApplications : " + e.getMessage());
		}

		return response;
	}
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
		LoanDetails loanDetailResult=loanRepository.save(loanDetails);
		if(loanDetailResult!=null)
		{
			CreateResponse response=new CreateResponse();
			response.setMessage("loanDetail added succesfully");
			return ResponseEntity.status(201).body(response);

		}
		else
		{
			CreateResponse response=new CreateResponse();
			response.setMessage("data not saved. sorry try again ");
			
			return ResponseEntity.status(401).body(response);

		}

	}

}
