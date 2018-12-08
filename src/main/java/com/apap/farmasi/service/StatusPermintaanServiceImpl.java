package com.apap.farmasi.service;

import com.apap.farmasi.model.StatusPermintaanModel;
import com.apap.farmasi.repository.StatusPermintaanDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusPermintaanServiceImpl implements StatusPermintaanService {
    @Autowired
    StatusPermintaanDB statusPermintaanDb;

    @Override
    public List<StatusPermintaanModel> findAll() {
        return statusPermintaanDb.findAll();
    }

    @Override
    public StatusPermintaanModel findById(long id) {
        return statusPermintaanDb.findById(id).get();
    }
}
