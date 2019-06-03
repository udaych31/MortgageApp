package com.hcl.mortgage.app.service;

import com.hcl.mortgage.app.dto.ViewApplicationResponse;

public interface LoanApplicationService {

	ViewApplicationResponse viewApplicationByApplicationId(Long id);
}
