package com.apap.farmasi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.farmasi.model.JenisMedicalSuppliesModel;
import com.apap.farmasi.repository.JenisMedicalSuppliesDB;

@Service
@Transactional
public class JenisMedicalSuppliesImpl implements JenisMedicalSuppliesService{

	@Autowired
	JenisMedicalSuppliesDB jenisMedicalSuppliesDb;
	
	@Override
	public List<JenisMedicalSuppliesModel> getAll() {
		// TODO Auto-generated method stub
		return jenisMedicalSuppliesDb.findAll();
	}

}
