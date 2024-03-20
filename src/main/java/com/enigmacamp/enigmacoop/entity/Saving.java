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
@Table(name="saving")
public class Saving {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Long balance;
    private Date lastTransctionDate;

    @ManyToOne
    @JoinColumn(name = "nasabah_id",nullable = false)
    private Nasabah nasabah;
}
