package com.enigmacamp.enigmacoop.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.List;

/**
 * Menyimpan saldo untuk nasabah
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="saving")
public class Saving {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Long balance;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nasabah_id")
    private Nasabah nasabah;

    @OneToMany(mappedBy = "saving", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<TrxSaving> trxSavingList;
}


/**
 * Nasabah one to one saving -> satu nasabah hanya memiliki satu data saving (saldo)
 * saving one to many TrxSaving( penarikan atau penambahan saldo)
 * TrxSaving (id,amount,trx_saving_type(debit,kredit),date,saving_id)
 * @JsonManagedReference
 * @JsonBackReference -> infinit loop load data json (one to many)
 */
