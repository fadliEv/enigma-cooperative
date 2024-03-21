package com.enigmacamp.enigmacoop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="m_nasabah")
public class Nasabah {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String fullName;
    private String phoneNumber;
    private String address;
    private Date joinDate;
    private String status;

    @OneToOne(mappedBy = "nasabah",cascade = CascadeType.ALL)
    private Saving saving;

    @OneToMany(mappedBy = "nasabah",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Loan> loanList;
}
