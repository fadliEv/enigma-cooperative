package com.enigmacamp.enigmacoop.service;

import com.enigmacamp.enigmacoop.entity.Loan;
import com.enigmacamp.enigmacoop.model.request.LoanRequest;
import com.enigmacamp.enigmacoop.model.request.SearchLoanRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface LoanService {
    Loan createLoan(LoanRequest loanRequest);
    List<Loan> getLoansByNasabahId(String id);
    Page<Loan> findLoansByAmountRange(Integer page, Integer size,Long minAmount, Long maxAmount);
    Page<Loan> getAll(SearchLoanRequest searchLoanRequest);
}
