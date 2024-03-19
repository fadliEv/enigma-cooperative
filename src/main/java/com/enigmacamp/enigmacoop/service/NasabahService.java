package com.enigmacamp.enigmacoop.service;

import com.enigmacamp.enigmacoop.model.Nasabah;
import com.enigmacamp.enigmacoop.repository.NasabahRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NasabahService {
    private final NasabahRepository nasabahRepository;
    public Nasabah registerNewNasabah(Nasabah nasabah){
        return nasabahRepository.saveAndFlush(nasabah);
    }
    public List<Nasabah> getAllNasabah(){
        return nasabahRepository.findAll();
    }
}
