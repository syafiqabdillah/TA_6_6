package com.apap.farmasi.model;

public class DetailPermintaanModel {
	private PermintaanModel permintaan;
	private String namaStaff;
	private String namaPasien;
	
	public DetailPermintaanModel(PermintaanModel permintaan, String namaStaf, String namaPasien) {
		this.namaPasien = namaPasien;
		this.namaStaff = namaStaf;
		this.permintaan = permintaan;
	}

	public PermintaanModel getPermintaan() {
		return permintaan;
	}

	public void setPermintaan(PermintaanModel permintaan) {
		this.permintaan = permintaan;
	}


	public String getNamaStaff() {
		return namaStaff;
	}

	public void setNamaStaff(String namaStaff) {
		this.namaStaff = namaStaff;
	}

	public String getNamaPasien() {
		return namaPasien;
	}

	public void setNamaPasien(String namaPasien) {
		this.namaPasien = namaPasien;
	}
	
	
}
