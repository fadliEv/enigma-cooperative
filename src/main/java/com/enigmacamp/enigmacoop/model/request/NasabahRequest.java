package com.enigmacamp.enigmacoop.model.request;

import jakarta.persistence.Column;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NasabahRequest {
    private String fullName;
    private String email;
    private String phoneNumber;

    private String address;
    private Date birthDate;
}
