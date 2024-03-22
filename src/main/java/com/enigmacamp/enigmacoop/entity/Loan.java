package com.enigmacamp.enigmacoop.entity;

import com.enigmacamp.enigmacoop.constant.LoanStatus;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="loan")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Long amount;
    private Double interestRate;
    private Date startDate;
    private Date dueDate;
    @Enumerated(EnumType.STRING)
    private LoanStatus status;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nasabah_id")
    private Nasabah nasabah;
}
