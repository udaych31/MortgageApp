package com.hcl.mortgage.app.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hcl.mortgage.app.dto.ApplicationQueueListResponse;
import com.hcl.mortgage.app.dto.ApplicationRequest;
import com.hcl.mortgage.app.dto.ApplicationResponse;
import com.hcl.mortgage.app.dto.CreateResponse;
import com.hcl.mortgage.app.dto.LoanDTO;
import com.hcl.mortgage.app.dto.LoanEligibleRequest;
import com.hcl.mortgage.app.dto.LoanEligibleResponse;
import com.hcl.mortgage.app.dto.QueueListDto;
import com.hcl.mortgage.app.dto.RequestPojo;
import com.hcl.mortgage.app.dto.ViewApplicationResponse;
import com.hcl.mortgage.app.entity.LoanDetails;
import com.hcl.mortgage.app.repository.LoanDetailRepository;
import com.hcl.mortgage.app.util.LoanServiceException;
import com.hcl.mortgage.app.util.LoanStatus;

@Service
public class LoanApplicationServiceImpl implements LoanApplicationService {
	private static final Logger logger = LogManager.getLogger(LoanApplicationServiceImpl.class);
	
	@Autowired
	private LoanDetailRepository loanDetailRepository;
	
	private static final String HOST_NAME="http://localhost:8085/bankapp/bank/accessapplication";


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
	


	@Override
	public ApplicationResponse updateApplication(ApplicationRequest request) {

		logger.info("enter into updateCredit status method");
		ApplicationResponse response = null;

		try {
			response = new ApplicationResponse();
			Optional<LoanDetails> loanDetails = loanDetailRepository.findById(request.getLoanId());

			if (loanDetails.isPresent()) {

				LoanDetails loanDetail = loanDetails.get();

				if (request.getLoanId() != null && request.getCreditStatus() == null) {

					if (!loanDetail.getCreditStatus().equalsIgnoreCase("NEW")) {

						logger.error("Loan service exception : loan already approved/funded");
						throw new LoanServiceException("Loan credit status was alredy " + loanDetail.getCreditStatus()
								+ " can not update as NEW application");

					}
                    
					if(request.getAddress()!=null)
					loanDetail.setAddress(request.getAddress());
					if(request.getApplicantIncome()!=null)
					loanDetail.setApplicantIncome(request.getApplicantIncome());
					loanDetail.setCreditStatus("NEW");
					if(request.getFirstName()!=null)
					loanDetail.setFirstName(request.getFirstName());
					if(request.getLastName()!=null)
					loanDetail.setLastName(request.getLastName());
					if(request.getLoanAmount()!=null)
					loanDetail.setLoanAmount(request.getLoanAmount());
					loanDetail.setTimeCreated(new Date());
					loanDetailRepository.save(loanDetail);
					logger.info("updated sucessfully");
					response.setMessage(
							"Update application successfully with reference number: " + request.getLoanId());
					response.setStatusCode(200);
				}

				else if (request.getCreditStatus().equalsIgnoreCase("approved") && request.getCreditStatus() != null) {
					if (loanDetail.getCreditStatus().equalsIgnoreCase("approved")) {
						logger.error("Loan service exception : loan already approved");
						throw new LoanServiceException("Loan  was alredy " + loanDetail.getCreditStatus());
					}
					loanDetail.setCreditStatus("APPROVED");
					loanDetailRepository.save(loanDetail);
					response.setMessage("APPROVED successfully with reference number: " + request.getLoanId());
					response.setStatusCode(200);

				}

				else if (request.getCreditStatus().equalsIgnoreCase("funded") && request.getCreditStatus() != null) {
					if (loanDetail.getCreditStatus().equalsIgnoreCase("funded")) {
						logger.error("Loan service exception : loan already funded");

						throw new LoanServiceException("Loan was alredy " + loanDetail.getCreditStatus());
					}
					loanDetail.setCreditStatus("FUNDCOMPLETED");
					loanDetailRepository.save(loanDetail);
					response.setMessage("FUNDED successfully with reference number: " + request.getLoanId());
					response.setStatusCode(200);

				}

			}
		} catch (Exception e) {
			response=new ApplicationResponse();
			response.setMessage(e.getMessage());
			response.setStatusCode(404);
			logger.error(logger.getClass().getName() + " Update Not Success" + e.getMessage());
		}
		return response;

	}

	@Override
	public ApplicationQueueListResponse getAllLoanApplications() {
		ApplicationQueueListResponse response = new ApplicationQueueListResponse();
		List<QueueListDto> newStatusList = new ArrayList<>();
		List<QueueListDto> approveStatusList = new ArrayList<>();
		List<QueueListDto> fundStatusList = new ArrayList<>();
		Map<String, List<QueueListDto>> queueList = new HashMap<>();
		try {
			List<LoanDetails> loanList = loanDetailRepository.findAll();
			if (!loanList.isEmpty()) {
				loanList.stream().forEach(loan -> {
					if (loan != null && loan.getCreditStatus() != null
							&& loan.getCreditStatus().equalsIgnoreCase(LoanStatus.NEW.toString())) {
						QueueListDto dto = new QueueListDto();
						dto.setLoanAmount(loan.getLoanAmount());
						dto.setLoanId(loan.getLoanId());
						dto.setFullName(loan.getFirstName() + " " + loan.getLastName());
						newStatusList.add(dto);
					} else if (loan != null && loan.getCreditStatus() != null
							&& loan.getCreditStatus().equalsIgnoreCase(LoanStatus.APPROVED.toString())) {
						QueueListDto dto = new QueueListDto();
						dto.setLoanAmount(loan.getLoanAmount());
						dto.setLoanId(loan.getLoanId());
						dto.setFullName(loan.getFirstName() + " " + loan.getLastName());
						approveStatusList.add(dto);
					} else if (loan != null && loan.getCreditStatus() != null
							&& loan.getCreditStatus().equalsIgnoreCase(LoanStatus.FUNDCOMPLETED.toString())) {
						QueueListDto dto = new QueueListDto();
						dto.setLoanAmount(loan.getLoanAmount());
						dto.setLoanId(loan.getLoanId());
						dto.setFullName(loan.getFirstName() + " " + loan.getLastName());
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
		LoanDetails loanDetails = new LoanDetails();
		loanDetails.setApplicantIncome(requestPojo.getApplicantIncome());
		loanDetails.setAddress(requestPojo.getAddress());
		loanDetails.setFirstName(requestPojo.getFirstName());
		loanDetails.setLastName(requestPojo.getLastName());
		Date timeCreated = new Date();
		loanDetails.setTimeCreated(timeCreated);
		loanDetails.setCreditStatus("new");
		loanDetails.setLoanAmount(requestPojo.getLoanAmount());
		 LoanDetails loandetail=loanDetailRepository.save(loanDetails);
		CreateResponse response = new CreateResponse();
		response.setMessage("loanDetail added succesfully");
		if(loandetail!=null)
		response.setApplicationId("Loan Application Id " +loandetail.getLoanId());
		return ResponseEntity.status(201).body(response);

	}
	
	@Override
	public LoanEligibleResponse checkEligibilityForLoan(LoanEligibleRequest request) throws LoanServiceException {
		LoanEligibleResponse response=null;
		try {
			RestTemplate rest=new RestTemplate();
			ResponseEntity<LoanEligibleResponse> responseEntity = rest.postForEntity(HOST_NAME, request, LoanEligibleResponse.class);
			response = responseEntity.getBody();
			
		} catch (Exception e) {
			response=new LoanEligibleResponse();
			response.setErrorMsg(e.getMessage());
			response.setRequestUri(HOST_NAME);
			response.setStatusCode(500);
			response.setStatus("INTERNALSERVERERROR");
			logger.error(this.getClass().getName()+" checkEligibilityForLoan :"+e.getMessage());
		}
		return response;
		
	}

}
