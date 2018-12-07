package com.apap.farmasi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.farmasi.model.JenisMedicalSuppliesModel;

@Repository
public interface JenisMedicalSuppliesDB extends JpaRepository<JenisMedicalSuppliesModel, Long> {
	List<JenisMedicalSuppliesModel> findAll();
}
