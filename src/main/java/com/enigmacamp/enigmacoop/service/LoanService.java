package com.enigmacamp.enigmacoop.service;

import com.enigmacamp.enigmacoop.entity.Loan;
import com.enigmacamp.enigmacoop.model.request.LoanRequest;

import java.util.List;

public interface LoanService {
    Loan createLoan(LoanRequest loanRequest);
    List<Loan> getLoansByNasabahId(String id);
}
