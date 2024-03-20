package com.enigmacamp.enigmacoop.service;

import com.enigmacamp.enigmacoop.model.Nasabah;

import java.util.List;

public interface NasabahService {
    Nasabah createNasabah(Nasabah nasabah);
    List<Nasabah> getAllNasabah();
    Nasabah getById(String id);
    Nasabah update(Nasabah nasabah);
    void deleteById(String id);
}