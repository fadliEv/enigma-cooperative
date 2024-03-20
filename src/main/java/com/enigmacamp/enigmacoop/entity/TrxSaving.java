package com.enigmacamp.enigmacoop.entity;

import com.enigmacamp.enigmacoop.constant.SavingType;
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
@Table(name="trx_saving")
public class TrxSaving {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private Long amount;

    @ManyToOne
    @JoinColumn(name = "saving_id")
    private Saving saving;

    @Enumerated(EnumType.STRING)
    private SavingType savingType;

    private Date date;
}
