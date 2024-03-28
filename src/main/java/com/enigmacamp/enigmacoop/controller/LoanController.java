package com.enigmacamp.enigmacoop.controller;


import com.enigmacamp.enigmacoop.entity.Loan;
import com.enigmacamp.enigmacoop.model.response.PagingResponse;
import com.enigmacamp.enigmacoop.model.response.WebResponse;
import com.enigmacamp.enigmacoop.model.request.LoanRequest;
import com.enigmacamp.enigmacoop.model.request.SearchLoanRequest;
import com.enigmacamp.enigmacoop.model.response.LoanResponse;
import com.enigmacamp.enigmacoop.service.LoanService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/loan")
@RestController
@AllArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @PostMapping
    public ResponseEntity<WebResponse<LoanResponse>> createLoan(@RequestBody LoanRequest loanRequest){
        LoanResponse newLoan = loanService.createLoan(loanRequest);
        WebResponse<LoanResponse> response = WebResponse.<LoanResponse>builder()
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

    @GetMapping
    public ResponseEntity<?> getLoansByNasabahId(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "10") Long minAmount,
            @RequestParam(defaultValue = "10") Long maxAmount
    ){
        SearchLoanRequest searchLoanRequest = SearchLoanRequest.builder()
                .page(page)
                .size(size)
                .minAmount(minAmount)
                .maxAmount(maxAmount)
                .build();
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
