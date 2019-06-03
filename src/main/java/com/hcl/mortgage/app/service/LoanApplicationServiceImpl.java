package com.hcl.mortgage.app.service;

import java.util.Date;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.mortgage.app.dto.ApplicationRequest;
import com.hcl.mortgage.app.dto.ApplicationResponse;
import com.hcl.mortgage.app.entity.LoanDetails;
import com.hcl.mortgage.app.repository.LoanDetailRepository;
import com.hcl.mortgage.app.util.LoanServiceException;

@Service
public class LoanApplicationServiceImpl implements LoanApplicationService {

	private static final Logger logger = LogManager.getLogger(LoanApplicationServiceImpl.class);

	@Autowired
	LoanDetailRepository loanDetailRepository;

	@Override
	public ApplicationResponse updateStatus(ApplicationRequest request) {

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
					
						loanDetail.setAddress(request.getAddress());
						loanDetail.setApplicantIncome(request.getApplicantIncome());
						loanDetail.setCreditStatus("NEW");
						loanDetail.setFirstName(request.getFirstName());
						loanDetail.setLastName(request.getLastName());
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
					loanDetail.setCreditStatus("FUNDED");
					loanDetailRepository.save(loanDetail);
					response.setMessage("FUNDED successfully with reference number: " + request.getLoanId());
					response.setStatusCode(200);

				}

			}
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setStatusCode(404);
			logger.error(logger.getClass().getName() + " Update Not Success" + e.getMessage());
		}
		return response;

	}
}
