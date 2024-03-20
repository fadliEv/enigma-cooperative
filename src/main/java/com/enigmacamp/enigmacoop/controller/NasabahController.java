package com.enigmacamp.enigmacoop.controller;

import com.enigmacamp.enigmacoop.model.Nasabah;
import com.enigmacamp.enigmacoop.service.NasabahService;
import com.enigmacamp.enigmacoop.service.impl.NasabahServiceImpl;
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
        return nasabahService.createNasabah(nasabah);
    }

    @GetMapping
    public List<Nasabah> getAllNasabah(){
        return nasabahService.getAllNasabah();
    }

    @GetMapping(path = "/{id}")
    public Nasabah getNasabahById(@PathVariable String id){
        return nasabahService.getById(id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteNasabahById(@PathVariable String id){
        nasabahService.deleteById(id);
        return "Success Detele Nasabah";
    }

    @PutMapping
    public Nasabah updateNasabahById(@RequestBody Nasabah nasabah){
        return nasabahService.update(nasabah);
    }

}

// JPA :  if Req -> id (Primary key | UUID) as Update data
//        if !id as Create data