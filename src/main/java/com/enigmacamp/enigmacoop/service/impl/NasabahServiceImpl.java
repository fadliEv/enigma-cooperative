package com.enigmacamp.enigmacoop.service.impl;

import com.enigmacamp.enigmacoop.constant.NasabahStatus;
import com.enigmacamp.enigmacoop.entity.Nasabah;
import com.enigmacamp.enigmacoop.entity.Saving;
import com.enigmacamp.enigmacoop.model.request.NasabahRequest;
import com.enigmacamp.enigmacoop.repository.NasabahRepository;
import com.enigmacamp.enigmacoop.service.NasabahService;
import com.enigmacamp.enigmacoop.service.SavingService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@AllArgsConstructor
public class NasabahServiceImpl implements NasabahService {

    private final NasabahRepository nasabahRepository;
    private final SavingService savingService;

    @Override
    public Nasabah createNasabah(NasabahRequest nasabahRequest) {
        // setiap register nasabah, maka akan dibuatkan data saving secara otomatis dengan
        // data awal saldonya adalah 0
        Nasabah nasabah = Nasabah.builder()
                .fullName(nasabahRequest.getFullName())
                .email(nasabahRequest.getEmail())
                .phoneNumber(nasabahRequest.getPhoneNumber())
                .address(nasabahRequest.getAddress())
                .status(NasabahStatus.ACTIVE)
                .build();
        Nasabah newNasabah = nasabahRepository.saveAndFlush(nasabah);
        Saving newSaving = Saving.builder()
                .balance(0L)
                .nasabah(newNasabah)
                .build();
        savingService.createSaving(newSaving);
        return newNasabah;
    }

    @Override
    public Page<Nasabah> getAllNasabah(Integer page, Integer size) {
        // Page berapa
        // Size berapa per page
        if (page <= 0 ) {
            page = 1;
        }
        Pageable pageable = PageRequest.of(page-1,size);
        return nasabahRepository.findAll(pageable);
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
