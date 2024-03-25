package com.enigmacamp.enigmacoop.service.impl;

import com.enigmacamp.enigmacoop.entity.Loan;
import com.enigmacamp.enigmacoop.entity.Nasabah;
import com.enigmacamp.enigmacoop.model.request.LoanRequest;
import com.enigmacamp.enigmacoop.model.request.SearchLoanRequest;
import com.enigmacamp.enigmacoop.repository.LoanRepository;
import com.enigmacamp.enigmacoop.service.LoanService;
import com.enigmacamp.enigmacoop.service.NasabahService;
import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public Page<Loan> findLoansByAmountRange(Integer page, Integer size, Long minAmount, Long maxAmount) {
        if (page <= 0 ) {
            page = 1;
        }
        Specification<Loan> loanSpecification = amountBetweenSpecification(minAmount,maxAmount);
        Pageable pageable = PageRequest.of(page-1,size);
        return loanRepository.findAll(loanSpecification,pageable);
    }

    @Override
    public Page<Loan> getAll(SearchLoanRequest searchLoanRequest) {
        if (searchLoanRequest.getPage() <= 0 ) {
            searchLoanRequest.setPage(1);
        }
        Specification<Loan> loanSpecification = getLoanSpecification(searchLoanRequest);
        Pageable pageable = PageRequest.of(searchLoanRequest.getPage() -1,searchLoanRequest.getSize());
        return loanRepository.findAll(loanSpecification,pageable);
    }


    private Specification<Loan> getLoanSpecification(SearchLoanRequest searchLoanRequest){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            // filter minimal besaran pinjaman
            if (searchLoanRequest.getMinAmount() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("amount"),searchLoanRequest.getMinAmount()));
            }
            // filter max besaran pinjaman
            if (searchLoanRequest.getMaxAmount() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("amount"),searchLoanRequest.getMaxAmount()));
            }
            // status peminjaman
            if (searchLoanRequest.getStatus() != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"),searchLoanRequest.getStatus()));
            }
            // by due date
            if (searchLoanRequest.getDueDate() != null) {
                predicates.add(criteriaBuilder.equal(root.get("dueDate"),searchLoanRequest.getDueDate()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private Specification<Loan> amountBetweenSpecification(Long minAmount, Long maxAmount){
        return (root, query, criteriaBuilder) -> {
            Predicate greaterThanOrEqualToMin = criteriaBuilder.greaterThanOrEqualTo(root.get("amount"),minAmount);
            Predicate lessThanOrEqualToMax = criteriaBuilder.lessThanOrEqualTo(root.get("amount"), maxAmount);
            return criteriaBuilder.and(greaterThanOrEqualToMin, lessThanOrEqualToMax);
        };
    }
}
