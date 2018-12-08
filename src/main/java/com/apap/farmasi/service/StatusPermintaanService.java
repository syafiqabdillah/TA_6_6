package com.apap.farmasi.service;

import com.apap.farmasi.model.StatusPermintaanModel;

import java.util.List;

public interface StatusPermintaanService {
    List<StatusPermintaanModel> findAll();

    StatusPermintaanModel findById(long id);
}
