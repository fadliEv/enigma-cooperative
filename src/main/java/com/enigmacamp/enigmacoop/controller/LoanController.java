package com.enigmacamp.enigmacoop.controller;


import com.enigmacamp.enigmacoop.constant.LoanStatus;
import com.enigmacamp.enigmacoop.entity.Loan;
import com.enigmacamp.enigmacoop.entity.Nasabah;
import com.enigmacamp.enigmacoop.model.PagingResponse;
import com.enigmacamp.enigmacoop.model.WebResponse;
import com.enigmacamp.enigmacoop.model.request.LoanRequest;
import com.enigmacamp.enigmacoop.model.request.SearchLoanRequest;
import com.enigmacamp.enigmacoop.service.LoanService;
import jdk.jshell.Snippet;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/v1/loan")
@RestController
@AllArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @PostMapping
    public ResponseEntity<WebResponse<Loan>> createLoan(@RequestBody LoanRequest loanRequest){
        Loan newLoan = loanService.createLoan(loanRequest);
        WebResponse<Loan> response = WebResponse.<Loan>builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message("Success Create Loan")
                .data(newLoan)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getLoansByNasabahId(@PathVariable String id){
        List<Loan> loanList = loanService.getLoansByNasabahId(id);
        WebResponse<List<Loan>> response = WebResponse.<List<Loan>>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("Success Get Loans By Nasabah Id")
                .data(loanList)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/range-amount")
    public ResponseEntity<?> findLoansByRangeAmount(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "1000") Long minAmount,
            @RequestParam(defaultValue = "300000") Long maxAmount
    ){
        Page<Loan> loanList = loanService.findLoansByAmountRange(page,size,minAmount,maxAmount);
        PagingResponse pagingResponse = PagingResponse.builder()
                .page(page)
                .size(size)
                .totalPages(loanList.getTotalPages())
                .totalElements(loanList.getTotalElements())
                .build();
        WebResponse<List<Loan>> response = WebResponse.<List<Loan>>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("Success Get Loans By range Amount")
                .paging(pagingResponse)
                .data(loanList.getContent())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<?> getAllLoan(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam Optional<Long> minAmount,
            @RequestParam Optional<Long> maxAmount,
            @RequestParam Optional<String> status, // Terima sebagai String
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<Date> dueDate // Gunakan DateTimeFormat untuk Date

    ){
        SearchLoanRequest.SearchLoanRequestBuilder builder = SearchLoanRequest.builder()
                .page(page)
                .size(size);
        minAmount.ifPresent(builder::minAmount);
        maxAmount.ifPresent(builder::maxAmount);
        status.ifPresent(s -> builder.status(LoanStatus.valueOf(s)));
        dueDate.ifPresent(builder::dueDate);

        SearchLoanRequest searchLoanRequest = builder.build();
        Page<Loan> loanList = loanService.getAll(searchLoanRequest);
        PagingResponse pagingResponse = PagingResponse.builder()
                .page(page)
                .size(size)
                .totalPages(loanList.getTotalPages())
                .totalElements(loanList.getTotalElements())
                .build();
        WebResponse<List<Loan>> response = WebResponse.<List<Loan>>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("Success Get Loans")
                .paging(pagingResponse)
                .data(loanList.getContent())
                .build();
        return ResponseEntity.ok(response);
    }

}
