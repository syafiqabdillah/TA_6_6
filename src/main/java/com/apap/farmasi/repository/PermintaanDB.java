package com.apap.farmasi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.farmasi.model.PermintaanModel;

@Repository
public interface PermintaanDB extends JpaRepository<PermintaanModel, Long>{
	List<PermintaanModel> findAll();
}
