package com.apap.farmasi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.farmasi.model.MedicalSuppliesModel;

@Repository
public interface MedicalSuppliesDB extends JpaRepository<MedicalSuppliesModel, Long> {
	MedicalSuppliesModel findByNama(String nama);
	MedicalSuppliesModel findMSById(Long id);

	@Override
	MedicalSuppliesModel save(MedicalSuppliesModel medicalSupplies);
}
