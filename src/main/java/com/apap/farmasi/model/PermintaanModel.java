package com.apap.farmasi.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="permintaan")
public class PermintaanModel implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Column(name = "tanggal", nullable = false)
	private Timestamp tanggal;
	
	@NotNull
	@Column(name = "jumlah_medical_supplies", nullable = false)
	private int jumlahMedicalSupplies;
	
	@NotNull
	@Column(name = "id_pasien", nullable = false)
	private int idPasien;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_medical_supplies", referencedColumnName = "id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private MedicalSuppliesModel medicalSupplies;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_status_permintaan", referencedColumnName = "id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private StatusPermintaanModel statusPermintaan;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_jadwal", referencedColumnName = "id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private JadwalJagaModel jadwalPermintaan;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Timestamp getTanggal() {
		return tanggal;
	}

	public void setTanggal(Timestamp tanggal) {
		this.tanggal = tanggal;
	}

	public int getJumlahMedicalSupplies() {
		return jumlahMedicalSupplies;
	}

	public void setJumlahMedicalSupplies(int jumlahMedicalSupplies) {
		this.jumlahMedicalSupplies = jumlahMedicalSupplies;
	}

	public int getIdPasien() {
		return idPasien;
	}

	public void setIdPasien(int idPasien) {
		this.idPasien = idPasien;
	}

	public MedicalSuppliesModel getMedicalSupplies() {
		return medicalSupplies;
	}

	public void setMedicalSupplies(MedicalSuppliesModel medicalSupplies) {
		this.medicalSupplies = medicalSupplies;
	}

	public StatusPermintaanModel getStatusPermintaan() {
		return statusPermintaan;
	}

	public void setStatusPermintaan(StatusPermintaanModel statusPermintaan) {
		this.statusPermintaan = statusPermintaan;
	}

	public JadwalJagaModel getJadwalPermintaan() {
		return jadwalPermintaan;
	}

	public void setJadwalPermintaan(JadwalJagaModel jadwalPermintaan) {
		this.jadwalPermintaan = jadwalPermintaan;
	}
	
	
}
