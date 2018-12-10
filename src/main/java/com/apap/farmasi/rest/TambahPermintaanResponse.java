package com.apap.farmasi.rest;

import com.apap.farmasi.model.PermintaanModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TambahPermintaanResponse {
    int status;
    String message;
    PermintaanModel result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PermintaanModel getResult() {
        return result;
    }

    public void setResult(PermintaanModel result) {
        this.result = result;
    }
}
