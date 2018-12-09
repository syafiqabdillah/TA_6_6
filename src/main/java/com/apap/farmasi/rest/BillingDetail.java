package com.apap.farmasi.rest;

import java.sql.Date;
import java.sql.Timestamp;

public class BillingDetail {
    int jumlahTagihan;
    String tanggalTagihan;
    Pasien pasien;

    public BillingDetail(int id) {
        this.pasien = new Pasien();
        pasien.setId(id);
    }

    static class Pasien {
        int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public int getJumlahTagihan() {
        return jumlahTagihan;
    }

    public void setJumlahTagihan(int jumlahTagihan) {
        this.jumlahTagihan = jumlahTagihan;
    }

    public String getTanggalTagihan() {
        return tanggalTagihan;
    }

    public void setTanggalTagihan(String tanggalTagihan) {
        this.tanggalTagihan = tanggalTagihan;
    }

    public Pasien getPasien() {
        return pasien;
    }

    public void setPasien(Pasien pasien) {
        this.pasien = pasien;
    }
}
