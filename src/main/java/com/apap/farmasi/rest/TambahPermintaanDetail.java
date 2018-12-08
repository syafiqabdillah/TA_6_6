package com.apap.farmasi.rest;

import java.io.Serializable;
import java.util.List;

public class TambahPermintaanDetail implements Serializable {

    public static class DetailRequest implements Serializable {
        Long id;
        Integer jumlah;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Integer getJumlah() {
            return jumlah;
        }

        public void setJumlah(Integer jumlah) {
            this.jumlah = jumlah;
        }
    }

    int idPasien;

    List<DetailRequest> details;

    public int getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(int idPasien) {
        this.idPasien = idPasien;
    }

    public List<DetailRequest> getDetails() {
        return details;
    }

    public void setDetails(List<DetailRequest> details) {
        this.details = details;
    }
}