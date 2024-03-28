package com.enigmacamp.enigmacoop.service.impl;

import com.enigmacamp.enigmacoop.constant.LoanStatus;
import com.enigmacamp.enigmacoop.entity.Loan;
import com.enigmacamp.enigmacoop.entity.Nasabah;
import com.enigmacamp.enigmacoop.model.request.LoanRequest;
import com.enigmacamp.enigmacoop.model.request.SearchLoanRequest;
import com.enigmacamp.enigmacoop.model.response.LoanResponse;
import com.enigmacamp.enigmacoop.repository.LoanRepository;
import com.enigmacamp.enigmacoop.service.LoanService;
import com.enigmacamp.enigmacoop.service.NasabahService;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class LoanServiceImpl implements LoanService {
    private final LoanRepository loanRepository;
    private final NasabahService nasabahService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public LoanResponse createLoan(LoanRequest loanRequest) {
        Nasabah findNasabah = nasabahService.getById(loanRequest.getNasabahId());
        Calendar calendar = getCalendarLoanCreate();
        Loan newLoan = Loan.builder()
                .amount(loanRequest.getAmount())
                .dueDate(calendar.getTime())
                .interestRate(5.0)
                .status(LoanStatus.PENDING)
                .nasabah(findNasabah)
                .build();
        Loan savedLoad = loanRepository.saveAndFlush(newLoan);
        Double totalRepaymentAmount = loanRequest.getAmount() + (loanRequest.getAmount() * 0.05);
        return LoanResponse.builder()
                .id(savedLoad.getId())
                .amount(savedLoad.getAmount())
                .dueDate(savedLoad.getDueDate())
                .startDate(savedLoad.getStartDate())
                .interestRate(savedLoad.getInterestRate())
                .nasabah(savedLoad.getNasabah())
                .status(savedLoad.getStatus())
                .totalPayment(totalRepaymentAmount.longValue())
                .build();
    }

    private static Calendar getCalendarLoanCreate() {
        // 1 bulan dari request peminjaman
        Calendar calendar = Calendar.getInstance();
        int daysToNextMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH) - calendar.get(Calendar.DAY_OF_MONTH);
        // due date / jatuh tempo akan selalu pada tanggal 5 setelah hitungan 1 bulan tepatnya di jam 23.00
        // Jika jarak ke tanggal 5 hanya 15 hari atau kurang, maka akan tambah menuju bulan depannya
        // untuk bunga akan selalu 5% dari semua pinjaman
        if (daysToNextMonth <= 15) {
            calendar.add(Calendar.MONTH,2);
        }else{
            calendar.add(Calendar.MONTH,1);
        }
        calendar.set(Calendar.DAY_OF_MONTH,5);
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        return calendar;
    }

    @Override
    public List<Loan> getLoansByNasabahId(String id) {
        return loanRepository.findByNasabahId(id);
    }

    @Override
    public Page<Loan> getAll(SearchLoanRequest searchLoanRequest) {
        if (searchLoanRequest.getPage() <= 0){
            searchLoanRequest.setPage(1);
        }
        Specification<Loan> loanSpecification = amountBetweenSepecification(
                searchLoanRequest.getMinAmount(),
                searchLoanRequest.getMaxAmount());
        Pageable pageable = PageRequest.of(searchLoanRequest.getPage()-1,searchLoanRequest.getSize());
        return loanRepository.findAll(loanSpecification,pageable);

    }

    private Specification<Loan> amountBetweenSepecification(Long minAmount, Long maxAmount){
        return (root, query, criteriaBuilder) -> {
            Predicate minAmountPredicate = criteriaBuilder.greaterThanOrEqualTo(root.get("amount"),minAmount);
            Predicate maxAmountPredicate = criteriaBuilder.lessThanOrEqualTo(root.get("amount"),maxAmount);
            return criteriaBuilder.and(minAmountPredicate,maxAmountPredicate);
        };
    }
}
