package com.apap.farmasi.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TambahPermintaanDetail implements Serializable {
    @NotNull
    String namaObat;
    @NotNull
    int idPasien;
    @NotNull
    @Range(min = 1)
    int jumlahMedicalSupplies;


    public int getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(int idPasien) {
        this.idPasien = idPasien;
    }

    public String getNamaObat() {
        return namaObat;
    }

    public void setNamaObat(String namaObat) {
        this.namaObat = namaObat;
    }

    public int getJumlahMedicalSupplies() {
        return jumlahMedicalSupplies;
    }

    public void setJumlahMedicalSupplies(int jumlahMedicalSupplies) {
        this.jumlahMedicalSupplies = jumlahMedicalSupplies;
    }
}