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
		return medicalSuppliesDb.findByNama(nama);
	}

	@Override
	public MedicalSuppliesModel getMedicalSuppliesById(Long id) {
		return medicalSuppliesDb.findMSById(id);
	}

	@Override
	public MedicalSuppliesModel save(MedicalSuppliesModel medicalSupplies) {
		return medicalSuppliesDb.save(medicalSupplies);
	}

	@Override
	public void updateJumlahMedsup(long id, int jumlah) {
		MedicalSuppliesModel medsup = getMedicalSuppliesById(id);
		medsup.setJumlah(medsup.getJumlah() + jumlah);
		medicalSuppliesDb.save(medsup);
	}
	
	@Override
	public void addMedicalSupplies(MedicalSuppliesModel medicalSupplies) {
		// TODO Auto-generated method stub
		medicalSuppliesDb.save(medicalSupplies);
		
	}

	@Override
	public void updateMedicalSupplies(MedicalSuppliesModel newMedicalSupplies, Long id) {
		// TODO Auto-generated method stub
		MedicalSuppliesModel oldMedicalSupplies = medicalSuppliesDb.findMSById(id);
		oldMedicalSupplies.setDeskripsi(newMedicalSupplies.getDeskripsi());
		oldMedicalSupplies.setJenisMedicalSupplies(newMedicalSupplies.getJenisMedicalSupplies());
		oldMedicalSupplies.setNama(newMedicalSupplies.getNama());
		oldMedicalSupplies.setPrice(newMedicalSupplies.getPrice());
		medicalSuppliesDb.save(oldMedicalSupplies);
	}

}
