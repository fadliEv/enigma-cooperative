package com.enigmacamp.enigmacoop.repository;

import com.enigmacamp.enigmacoop.model.Nasabah;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NasabahRepository extends JpaRepository<Nasabah, String> {
}