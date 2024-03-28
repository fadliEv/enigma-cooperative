package com.enigmacamp.enigmacoop.entity;

import com.enigmacamp.enigmacoop.constant.NasabahStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="m_nasabah")
@Builder
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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false,updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss a", timezone = "Asia/Jakarta")
    private Date joinDate;

    @Enumerated(EnumType.STRING)
    private NasabahStatus status;

    @OneToOne
    private UserCredential userCredential;

    @PrePersist
    protected void onCreate(){
        joinDate = new Date();
    }
}
