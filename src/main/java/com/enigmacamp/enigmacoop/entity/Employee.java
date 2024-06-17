package com.enigmacamp.enigmacoop.entity;

import com.enigmacamp.enigmacoop.constant.PositionEmployee;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String fullName;
    private String email;
    private String phoneNumber;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss a", timezone = "Asia/Jakarta")
    private Date hireDate;

    @Enumerated(EnumType.STRING)
    private PositionEmployee position;
    private Long salary;
    @Column(columnDefinition = "boolean default true")
    private Boolean status = true;

    @OneToOne
    @JoinColumn(name = "image_id", unique = true)
    private Image image;

}
