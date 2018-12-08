package com.apap.farmasi.service;

import java.util.List;
import java.util.Optional;

import com.apap.farmasi.model.PermintaanModel;

public interface PermintaanService {
	List<PermintaanModel> getAll();

	PermintaanModel findById(long id);

	PermintaanModel save(PermintaanModel permintaan);

	void updateStatusPermintaan(PermintaanModel newPermintaanModel);
}
