package com.apap.farmasi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DetailMedicalSuppliesLabModel {
	private int id;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	private String nama;
	private int jumlah;

	public DetailMedicalSuppliesLabModel(String nama, int jumlah) {
		this.nama = nama;
		this.jumlah = jumlah;
	}
	
	public DetailMedicalSuppliesLabModel() {}

	public String getNama() {
		return nama;
	}



	public void setNama(String nama) {
		this.nama = nama;
	}



	public int getJumlah() {
		return jumlah;
	}



	public void setJumlah(int jumlah) {
		this.jumlah = jumlah;
	}

	
	
}
