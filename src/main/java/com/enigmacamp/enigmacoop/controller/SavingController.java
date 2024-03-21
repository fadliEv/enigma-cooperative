package com.enigmacamp.enigmacoop.controller;

import com.enigmacamp.enigmacoop.entity.Nasabah;
import com.enigmacamp.enigmacoop.entity.Saving;
import com.enigmacamp.enigmacoop.model.WebResponse;
import com.enigmacamp.enigmacoop.service.SavingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/saving")
@AllArgsConstructor
public class SavingController {

    private final SavingService savingService;

    @PostMapping
    public ResponseEntity<WebResponse<Saving>> createSaving(@RequestBody Saving saving){
        Saving newSaving = savingService.createSaving(saving);
        WebResponse<Saving> response = WebResponse.<Saving>builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message("Success register new Saving")
                .data(newSaving)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<WebResponse<List<Saving>>> getListSaving(){
        List<Saving> savingList = savingService.getListSaving();
        WebResponse<List<Saving>> response = WebResponse.<List<Saving>>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("Success Get List Saving")
                .data(savingList)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WebResponse<Saving>> getSavingById(@PathVariable String id){
        Saving findSaving = savingService.getSavingById(id);
        WebResponse<Saving> response = WebResponse.<Saving>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("Success Get Saving By Id")
                .data(findSaving)
                .build();
        return ResponseEntity.ok(response);
    }

}
