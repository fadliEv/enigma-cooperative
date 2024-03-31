package com.enigmacamp.enigmacoop.service;

import com.enigmacamp.enigmacoop.entity.Nasabah;
import com.enigmacamp.enigmacoop.entity.UserCredential;
import com.enigmacamp.enigmacoop.model.request.NasabahRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface NasabahService {
    Nasabah createNasabah(NasabahRequest nasabahRequest, UserCredential userCredential);
    Page<Nasabah> getAllNasabah(Integer page, Integer size);
    Nasabah getById(String id);
    Nasabah update(Nasabah nasabah);
    void deleteById(String id);

    Nasabah findByUsername(String name);
}
