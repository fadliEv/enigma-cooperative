package com.enigmacamp.enigmacoop.entity;

import com.enigmacamp.enigmacoop.constant.NasabahStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


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

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phoneNumber;

    private String address;

    private Date birthDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date joinDate;

    @Enumerated(EnumType.STRING)
    private NasabahStatus status;

    @PrePersist
    protected void onCreate() {
        joinDate = new Date();
    }
}
