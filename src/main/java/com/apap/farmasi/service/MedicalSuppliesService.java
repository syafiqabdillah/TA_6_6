package com.apap.farmasi.service;

import java.util.List;

import com.apap.farmasi.model.MedicalSuppliesModel;

public interface MedicalSuppliesService {
	List<MedicalSuppliesModel> getAll();
	MedicalSuppliesModel getMedicalSuppliesByNama(String nama);
	MedicalSuppliesModel getMedicalSuppliesById(Long id);
	MedicalSuppliesModel save(MedicalSuppliesModel medicalSupplies);
	void updateJumlahMedsup(long id, int jumlah);
	void addMedicalSupplies(MedicalSuppliesModel medicalSupplies);
	void updateMedicalSupplies(MedicalSuppliesModel newMedicalSupplies, Long id);
}
