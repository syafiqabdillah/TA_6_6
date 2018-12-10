package com.apap.farmasi.controller;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import com.apap.farmasi.model.JadwalJagaModel;
import com.apap.farmasi.model.PermintaanModel;
import com.apap.farmasi.rest.TambahPermintaanDetail;
import com.apap.farmasi.rest.TambahPermintaanResponse;
import com.apap.farmasi.service.JadwalJagaService;
import com.apap.farmasi.service.PermintaanService;
import com.apap.farmasi.service.StatusPermintaanService;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import com.apap.farmasi.model.MedicalSuppliesModel;
import com.apap.farmasi.service.MedicalSuppliesService;
import com.apap.farmasi.rest.BaseResponse;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    MedicalSuppliesService medicalSuppliesService;

    @Autowired
    StatusPermintaanService statusPermintaanService;

    @Autowired
    JadwalJagaService jadwalJagaService;

    @Autowired
    PermintaanService permintaanService;

    @GetMapping(value="/daftar-medical-service")
    public BaseResponse<List<MedicalSuppliesModel>> getAllMedicalSupplies(){
        BaseResponse<List<MedicalSuppliesModel>> response = new BaseResponse<List<MedicalSuppliesModel>>();
        response.setStatus(200);
        response.setMessage("success");
        response.setResult(medicalSuppliesService.getAll());
        return response;
    }

    @PostMapping(value = "/medical-supplies/permintaan/")
    public TambahPermintaanResponse createPermintaan(@Valid @RequestBody TambahPermintaanDetail permintaanDetail,BindingResult result) {
        TambahPermintaanResponse response = new TambahPermintaanResponse();
        if (result.hasErrors()) {
            response.setMessage("informasi permintaan tidak lengkap");
            response.setStatus(400);
            return response;
        }

        try {
            PermintaanModel permintaan = new PermintaanModel();
            permintaan.setIdPasien(permintaanDetail.getIdPasien());
            permintaan.setMedicalSupplies(medicalSuppliesService.getMedicalSuppliesByNama(permintaanDetail.getNamaObat()));
            permintaan.setTanggal(Timestamp.valueOf(LocalDateTime.now()));
            permintaan.setJumlahMedicalSupplies(permintaanDetail.getJumlahMedicalSupplies());
            permintaan.setStatusPermintaan(statusPermintaanService.findById(1));

            //kalau tidak ada staff yg jaga, jadwal jaga default id 1
            List<JadwalJagaModel> allJadwal = jadwalJagaService.getAll();
            boolean isStaffPresent = false;
            for (JadwalJagaModel jadwal : allJadwal) {
                Timestamp mulai = jadwal.getWaktuMulaiTs();
                Timestamp selesai = jadwal.getWaktuSelesaiTs();
                Timestamp waktuPermintaan = Timestamp.valueOf(LocalDateTime.now());

                if (waktuPermintaan.after(mulai) && waktuPermintaan.before(selesai)) {
                    permintaan.setJadwalPermintaan(jadwal);
                    isStaffPresent = true;
                }
            }
            if (!isStaffPresent)
                permintaan.setJadwalPermintaan(jadwalJagaService.getJadwalJagaById(Long.parseLong("1")));

            response.setResult(permintaanService.save(permintaan));

        }
        catch (Exception e) {
            response.setStatus(500);
            response.setMessage("internal server error");
            return response;
        }
        response.setStatus(200);
        response.setMessage("success");
        return response;
    }

}
