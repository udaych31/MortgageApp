package com.hcl.mortgage.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.mortgage.app.entity.LoanDetails;

@Repository
public interface LoanDetailRepository extends JpaRepository<LoanDetails, Long> {

	public List<LoanDetails> findByLoanId(Long loanId);

}
