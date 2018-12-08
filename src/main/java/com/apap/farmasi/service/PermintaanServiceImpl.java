package com.apap.farmasi.service;

import java.util.List;
import java.util.Optional;

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

	@Override
	public PermintaanModel findById(long id) {
		return permintaanDb.findById(id).get();
	}

	@Override
	public PermintaanModel save(PermintaanModel permintaan) {
		return permintaanDb.save(permintaan);
	}

	@Override
	public void updateStatusPermintaan(PermintaanModel newPermintaanModel) {
		PermintaanModel resPermintaan = permintaanDb.findById(newPermintaanModel.getId()).get();
		resPermintaan.setStatusPermintaan(newPermintaanModel.getStatusPermintaan());
		permintaanDb.save(resPermintaan);
	}


}
