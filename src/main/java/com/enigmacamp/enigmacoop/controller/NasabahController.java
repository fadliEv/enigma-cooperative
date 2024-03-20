package com.enigmacamp.enigmacoop.controller;

import com.enigmacamp.enigmacoop.entity.Nasabah;
import com.enigmacamp.enigmacoop.model.PagingResponse;
import com.enigmacamp.enigmacoop.model.WebResponse;
import com.enigmacamp.enigmacoop.service.NasabahService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/nasabah")
@AllArgsConstructor
public class NasabahController {

    private final NasabahService nasabahService;

    @PostMapping
    public ResponseEntity<WebResponse<Nasabah>> createNasabah(@RequestBody Nasabah nasabah){
        Nasabah newNasabah = nasabahService.createNasabah(nasabah);
        WebResponse<Nasabah> response = WebResponse.<Nasabah>builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message("Success register new nasabah")
                .data(newNasabah)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<?> getAllNasabah(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ){
        Page<Nasabah> nasabahList = nasabahService.getAllNasabah(page,size);
        PagingResponse pagingResponse = PagingResponse.builder()
                .page(page)
                .size(size)
                .totalPages(nasabahList.getTotalPages())
                .totalElements(nasabahList.getTotalElements())
                .build();
        WebResponse<List<Nasabah>> response = WebResponse.<List<Nasabah>>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("Success Get List Nasabah")
                .paging(pagingResponse)
                .data(nasabahList.getContent())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getNasabahById(@PathVariable String id){
        Nasabah findNasabah = nasabahService.getById(id);
        WebResponse<Nasabah> response = WebResponse.<Nasabah>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("Success Get Nasabah")
                .data(findNasabah)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteNasabahById(@PathVariable String id){
        nasabahService.deleteById(id);
        WebResponse<String> response = WebResponse.<String>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("Success Delete Nasabah")
                .data("OK")
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<?> updateNasabahById(@RequestBody Nasabah nasabah){
        Nasabah updatedNasabah = nasabahService.update(nasabah) ;
        WebResponse<Nasabah> response = WebResponse.<Nasabah>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("Success update Nasabah")
                .data(updatedNasabah)
                .build();
        return ResponseEntity.ok(response);
    }
}

// JPA :  if Req -> id (Primary key | UUID) as Update data
//        if !id as Create data

/**
 * 100jt data user -> 100jt return ke client  ?? NOOOOO!!!
 * semakin banyak data yang diload maka akan berpengaruh terhadap performance dari BE/service
 * filter limitation data list , ini harus adaa!!
 */