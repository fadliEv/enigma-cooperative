package com.enigmacamp.enigmacoop.controller;


import com.enigmacamp.enigmacoop.model.request.AuthRequest;
import com.enigmacamp.enigmacoop.model.request.NasabahRequest;
import com.enigmacamp.enigmacoop.model.response.NasabahResponse;
import com.enigmacamp.enigmacoop.model.response.WebResponse;
import com.enigmacamp.enigmacoop.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/register-nasabah")
    public ResponseEntity<WebResponse<NasabahResponse>> createNasabah(
            @Valid @RequestBody NasabahRequest nasabahRequest){
        NasabahResponse nasabahResponse = authService.register(nasabahRequest);
        WebResponse<NasabahResponse> response = WebResponse.<NasabahResponse>builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message("Success register new nasabah")
                .data(nasabahResponse)
                .build();
        return ResponseEntity.ok(response);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest){
        String token = authService.login(authRequest);
        WebResponse<String> response = WebResponse.<String>builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message("Success Login")
                .data(token)
                .build();
        return ResponseEntity.ok(response);
    }
}
