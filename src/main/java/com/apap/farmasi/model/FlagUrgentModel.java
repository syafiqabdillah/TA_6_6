package com.apap.farmasi.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="flag_urgent", schema="public")
public class FlagUrgentModel implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Column(name = "flag", nullable = false)
	private int flag;
	
	@NotNull
	@Size(max = 255)
	@Column(name = "deskripsi_flag_urgent", nullable = false)
	private String deskripsiFlagUrgent;
	
	@OneToMany(mappedBy = "urgent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<JenisMedicalSuppliesModel> jenisMedicalSupplies;
	
	

	public List<JenisMedicalSuppliesModel> getJenisMedicalSupplies() {
		return jenisMedicalSupplies;
	}

	public void setJenisMedicalSupplies(List<JenisMedicalSuppliesModel> jenisMedicalSupplies) {
		this.jenisMedicalSupplies = jenisMedicalSupplies;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getDeskripsiFlagUrgent() {
		return deskripsiFlagUrgent;
	}

	public void setDeskripsiFlagUrgent(String deskripsiFlagUrgent) {
		this.deskripsiFlagUrgent = deskripsiFlagUrgent;
	}
	
	
}
