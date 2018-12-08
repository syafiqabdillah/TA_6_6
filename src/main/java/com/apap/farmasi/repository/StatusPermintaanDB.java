package com.apap.farmasi.repository;

import com.apap.farmasi.model.StatusPermintaanModel;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StatusPermintaanDB extends JpaRepository<StatusPermintaanModel,Long> {
    @Override
    <S extends StatusPermintaanModel> List<S> findAll(Example<S> example);

    @Override
    Optional<StatusPermintaanModel> findById(Long aLong);
}
