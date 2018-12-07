package com.apap.farmasi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.farmasi.model.PerencanaanModel;
import com.apap.farmasi.repository.PerencanaanDB;

@Service 
@Transactional
public class PerencanaanServiceImpl implements PerencanaanService{
	@Autowired
	PerencanaanDB perencanaanDb;
	
	@Override
	public PerencanaanModel getPerencanaan(long id) {
		return perencanaanDb.findById(id).get();
	}

	@Override
	public void updateStatusPerencanaan(PerencanaanModel oldP, PerencanaanModel newP) {
		oldP.setStatus(newP.getStatus());
		perencanaanDb.save(oldP);
	}

	@Override
	public void addPerencanaan(PerencanaanModel perencanaan) {
		perencanaanDb.save(perencanaan);
	}

	@Override
	public List<PerencanaanModel> getAll() {
		return perencanaanDb.findAll();
	}

}
