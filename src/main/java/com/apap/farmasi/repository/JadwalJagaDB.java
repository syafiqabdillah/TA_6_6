package com.apap.farmasi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.farmasi.model.JadwalJagaModel;

@Repository
public interface JadwalJagaDB extends JpaRepository<JadwalJagaModel, Long> {
    void deleteById(long id);

    Optional<JadwalJagaModel> findById(long id);
}    