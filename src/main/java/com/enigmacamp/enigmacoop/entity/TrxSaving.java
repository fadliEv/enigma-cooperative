package com.enigmacamp.enigmacoop.entity;

import com.enigmacamp.enigmacoop.constant.SavingType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="trx_saving")
public class TrxSaving {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private Long amount;

    @ManyToOne
    @JoinColumn(name = "saving_id")
    @JsonBackReference // sisi yang tidak akan diserialisasikan
    private Saving saving;

    @Enumerated(EnumType.STRING)
    private SavingType savingType;

    private Date date;
}
