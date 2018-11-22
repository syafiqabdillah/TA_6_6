package com.apap.farmasi.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="jadwal_jaga")
public class JadwalJagaModel implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Column(name = "tanggal", nullable = false)
	private Date tanggal;
	
	@NotNull
	@Column(name = "waktu_mulai", nullable = false)
	private String waktuMulai;
	
	@NotNull
	@Column(name = "waktu_selesai", nullable = false)
	private String waktuSelesai;
	
	@OneToMany(mappedBy = "jadwalPermintaan", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<PermintaanModel> listPermintaan;
	
	@NotNull
	@Column(name = "id_staff", nullable = false)
	private int idStaff;

	public int getIdStaff() {
		return idStaff;
	}

	public void setIdStaff(int idStaff) {
		this.idStaff = idStaff;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getTanggal() {
		return tanggal;
	}

	public void setTanggal(Date tanggal) {
		this.tanggal = tanggal;
	}

	public String getWaktuMulai() {
		return waktuMulai;
	}

	public void setWaktuMulai(String waktuMulai) {
		this.waktuMulai = waktuMulai;
	}

	public String getWaktuSelesai() {
		return waktuSelesai;
	}

	public void setWaktuSelesai(String waktuSelesai) {
		this.waktuSelesai = waktuSelesai;
	}

	public List<PermintaanModel> getListPermintaan() {
		return listPermintaan;
	}

	public void setListPermintaan(List<PermintaanModel> listPermintaan) {
		this.listPermintaan = listPermintaan;
	}


		
	
}
