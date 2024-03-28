package com.enigmacamp.enigmacoop.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NasabahRequest {
    @NotBlank(message = "fullname is mandatory and cannot be blank")
    // tidak boleh blank, hanya boleh karakter alpabet
    @Pattern(regexp = "^[A-Za-z]+$",message = "Fullname must contain only alphabet")
    private String fullName;

    @NotBlank(message = "Email is mandatory and cannot be blank")
    @Email(message = "Email should be valid")
    private String email;
    // format email
    // formatnya harus number, tidak boleh huruf,
    @Pattern(regexp = "^[0-9]+$",message = "Phone number must containt only numbers")
    @NotBlank(message = "Phone Number is mandatory and cannot be blank")
    private String phoneNumber;
    private String address;
    private String username;
    private String password;
}
