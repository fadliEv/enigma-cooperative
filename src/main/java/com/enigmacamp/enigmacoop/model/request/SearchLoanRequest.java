package com.enigmacamp.enigmacoop.model.request;

import com.enigmacamp.enigmacoop.constant.LoanStatus;
import lombok.*;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchLoanRequest {
    private Integer page;
    private Integer size;
    private Long minAmount;
    private Long maxAmount;
    private LoanStatus status;
    private Date dueDate;
}
