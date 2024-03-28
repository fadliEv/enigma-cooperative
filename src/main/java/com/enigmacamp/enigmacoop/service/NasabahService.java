package com.enigmacamp.enigmacoop.service;

import com.enigmacamp.enigmacoop.entity.Nasabah;
import com.enigmacamp.enigmacoop.model.request.NasabahRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface NasabahService {
    Nasabah createNasabah(NasabahRequest nasabahRequest);
    Page<Nasabah> getAllNasabah(Integer page, Integer size);
    Nasabah getById(String id);
    Nasabah update(Nasabah nasabah);
    void deleteById(String id);
}