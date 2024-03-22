package com.enigmacamp.enigmacoop.service.impl;

import com.enigmacamp.enigmacoop.entity.Loan;
import com.enigmacamp.enigmacoop.entity.Nasabah;
import com.enigmacamp.enigmacoop.model.request.LoanRequest;
import com.enigmacamp.enigmacoop.repository.LoanRepository;
import com.enigmacamp.enigmacoop.service.LoanService;
import com.enigmacamp.enigmacoop.service.NasabahService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements LoanService {
    private final LoanRepository loanRepository;
    private final NasabahService nasabahService;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Loan createLoan(LoanRequest loanRequest) {
        Nasabah findNasabah = nasabahService.getById(loanRequest.getNasabahId());
        Loan newLoan = Loan.builder()
                .amount(loanRequest.getAmount())
                .dueDate(loanRequest.getDueDate())
                .interestRate(loanRequest.getInterestRate())
                .startDate(loanRequest.getStartDate())
                .status(loanRequest.getStatus())
                .nasabah(findNasabah)
                .build();
        return loanRepository.saveAndFlush(newLoan);
    }

    @Override
    public List<Loan> getLoansByNasabahId(String id) {
        return loanRepository.findByNasabahId(id);
    }
}
