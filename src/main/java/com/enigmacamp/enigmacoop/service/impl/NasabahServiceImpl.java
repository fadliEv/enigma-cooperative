package com.enigmacamp.enigmacoop.service.impl;

import com.enigmacamp.enigmacoop.entity.Nasabah;
import com.enigmacamp.enigmacoop.repository.NasabahRepository;
import com.enigmacamp.enigmacoop.service.NasabahService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NasabahServiceImpl implements NasabahService {

    private final NasabahRepository nasabahRepository;

    @Override
    public Nasabah createNasabah(Nasabah nasabah) {
        return nasabahRepository.saveAndFlush(nasabah);
    }

    @Override
    public List<Nasabah> getAllNasabah() {
        return nasabahRepository.findAll();
    }

    @Override
    public Nasabah getById(String id) {
        Optional<Nasabah> optionalNasabah = nasabahRepository.findById(id);
        if (optionalNasabah.isPresent()) return  optionalNasabah.get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nasabah Not Found");
    }

    @Override
    public Nasabah update(Nasabah nasabah) {
        this.getById(nasabah.getId());
        return nasabahRepository.save(nasabah);
    }

    @Override
    public void deleteById(String id) {
        this.getById(id);
        nasabahRepository.deleteById(id);
    }
}
