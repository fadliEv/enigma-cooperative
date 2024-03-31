package com.enigmacamp.enigmacoop.controller;

import com.enigmacamp.enigmacoop.entity.Nasabah;
import com.enigmacamp.enigmacoop.model.response.WebResponse;
import com.enigmacamp.enigmacoop.service.NasabahService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('CUSTOMER')")
@Slf4j
public class NasabahProfileController {
    private final NasabahService nasabahService;
    @GetMapping
    public ResponseEntity<?> getMyProfile(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        log.info("Username : " + username);
        Nasabah nasabah = nasabahService.findByUsername(username);
        WebResponse<Nasabah> response = WebResponse.<Nasabah>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("Success Get Nasabah")
                .data(nasabah)
                .build();
        return ResponseEntity.ok(response);
    }
}
