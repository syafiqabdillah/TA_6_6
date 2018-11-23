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
	public PerencanaanModel getPerencanaan() {
		List<PerencanaanModel> listPerencanaan = perencanaanDb.findAll();
		return listPerencanaan.get(0);
	}

}
