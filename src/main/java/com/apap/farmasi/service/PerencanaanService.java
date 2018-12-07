package com.apap.farmasi.service;

import java.util.List;

import com.apap.farmasi.model.PerencanaanModel;

public interface PerencanaanService {
	PerencanaanModel getPerencanaan(long id);
	void updateStatusPerencanaan(PerencanaanModel oldP, PerencanaanModel newP);
	void addPerencanaan(PerencanaanModel perencanaan);
	List<PerencanaanModel> getAll();
}
