package com.enigmacamp.enigmacoop.controller;


import com.enigmacamp.enigmacoop.entity.Loan;
import com.enigmacamp.enigmacoop.entity.Nasabah;
import com.enigmacamp.enigmacoop.model.WebResponse;
import com.enigmacamp.enigmacoop.model.request.LoanRequest;
import com.enigmacamp.enigmacoop.service.LoanService;
import lombok.AllArgsConstructor;
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
}
