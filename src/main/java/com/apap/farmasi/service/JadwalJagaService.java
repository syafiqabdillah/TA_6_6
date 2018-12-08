package com.apap.farmasi.service;

import java.util.List;
import java.util.Optional;

import com.apap.farmasi.model.JadwalJagaModel;

public interface JadwalJagaService {
	//View
	JadwalJagaModel getJadwalJagaById(Long id);
	List<JadwalJagaModel> getAll();
	
	//Add
	void addJadwalJaga(JadwalJagaModel jadwalJagaBaru);
	
	//Update
	void updateJadwalJaga(Long id, JadwalJagaModel jadwalJagaBaru);
	
	//Delete
	void deleteById(Long id);
}
