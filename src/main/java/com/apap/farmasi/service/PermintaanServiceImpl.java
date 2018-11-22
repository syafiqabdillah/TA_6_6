package com.apap.farmasi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.farmasi.model.PermintaanModel;
import com.apap.farmasi.repository.PermintaanDB;

@Service
@Transactional
public class PermintaanServiceImpl implements PermintaanService{
	@Autowired
	PermintaanDB permintaanDb;

	@Override
	public List<PermintaanModel> getAll() {
		return permintaanDb.findAll();
	}
	
	
}
