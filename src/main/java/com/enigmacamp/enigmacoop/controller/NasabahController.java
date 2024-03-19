package com.enigmacamp.enigmacoop.controller;

import com.enigmacamp.enigmacoop.model.Nasabah;
import com.enigmacamp.enigmacoop.service.NasabahService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/nasabah")
@AllArgsConstructor
public class NasabahController {

    private final NasabahService nasabahService;

    @PostMapping
    public Nasabah createNasabah(@RequestBody Nasabah nasabah){
        return nasabahService.registerNewNasabah(nasabah);
    }

    @GetMapping
    public List<Nasabah> getAllNasabah(){
        return nasabahService.getAllNasabah();
    }

}
