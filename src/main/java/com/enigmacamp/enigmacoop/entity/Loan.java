package com.enigmacamp.enigmacoop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="loan")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Long amount;
    private Double interestRate;
    private Date startDate;
    private Date dueDate;
    private String status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nasabah_id")
    private Nasabah nasabah;
}
