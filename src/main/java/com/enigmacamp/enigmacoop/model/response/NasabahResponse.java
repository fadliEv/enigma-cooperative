package com.enigmacamp.enigmacoop.model.response;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NasabahResponse {
    private String fullName;
    private String email;
    private String phoneNumber;
    private String address;
    private String username;
    private List<String> roles;
}
