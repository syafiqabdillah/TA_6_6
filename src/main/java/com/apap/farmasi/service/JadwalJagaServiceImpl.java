package com.apap.farmasi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.farmasi.model.JadwalJagaModel;
import com.apap.farmasi.repository.JadwalJagaDB;

@Service
@Transactional
public class JadwalJagaServiceImpl implements JadwalJagaService{
	@Autowired
	JadwalJagaDB jadwalJagaDb;
	
	@Override
	public JadwalJagaModel getJadwalJagaById(Long id) {
		// TODO Auto-generated method stub
		return jadwalJagaDb.findById(id).get();
	}

	@Override
	public List<JadwalJagaModel> getAll() {
		// TODO Auto-generated method stub
		return jadwalJagaDb.findAll();
	}

	@Override
	public void addJadwalJaga(JadwalJagaModel jadwalJaga) {
		// TODO Auto-generated method stub
		jadwalJagaDb.save(jadwalJaga);
		
	}

	@Override
	public void updateJadwalJaga(Long id, JadwalJagaModel jadwalJagaBaru) {
		System.out.println(id);
		JadwalJagaModel jadwalJaga = jadwalJagaDb.findById(id).get();

		System.out.println(jadwalJagaBaru.getIdStaff());
		System.out.println(jadwalJagaBaru.getTanggal());
		System.out.println(jadwalJagaBaru.getWaktuSelesai());
		
		jadwalJaga.setTanggal(jadwalJagaBaru.getTanggal());
		jadwalJaga.setWaktuMulai(jadwalJagaBaru.getWaktuMulai());
		jadwalJaga.setWaktuSelesai(jadwalJagaBaru.getWaktuSelesai());
		jadwalJaga.setIdStaff(jadwalJagaBaru.getIdStaff());
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

}
