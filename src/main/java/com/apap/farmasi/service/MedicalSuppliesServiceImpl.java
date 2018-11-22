package com.apap.farmasi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.farmasi.model.MedicalSuppliesModel;
import com.apap.farmasi.repository.MedicalSuppliesDB;

@Service
@Transactional
public class MedicalSuppliesServiceImpl implements MedicalSuppliesService {
	@Autowired
	MedicalSuppliesDB medicalSuppliesDb;

	@Override
	public List<MedicalSuppliesModel> getAll() {
		return medicalSuppliesDb.findAll();
	}

	@Override
	public MedicalSuppliesModel getMedicalSuppliesByNama(String nama) {
		// TODO Auto-generated method stub
		return medicalSuppliesDb.findByNama(nama);
	}

	

	@Override
	public MedicalSuppliesModel getMedicalSuppliesById(Long id) {
		// TODO Auto-generated method stub
		return medicalSuppliesDb.findMSById(id);
	}
}
