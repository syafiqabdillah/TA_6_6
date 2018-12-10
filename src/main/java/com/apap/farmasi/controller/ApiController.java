package com.apap.farmasi.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.apap.farmasi.model.PasienModel;
import com.apap.farmasi.model.PermintaanModel;
import com.apap.farmasi.rest.TambahPermintaanDetail;
import com.apap.farmasi.service.JadwalJagaService;
import com.apap.farmasi.service.PermintaanService;
import com.apap.farmasi.service.StatusPermintaanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.apap.farmasi.model.MedicalSuppliesModel;
import com.apap.farmasi.service.MedicalSuppliesService;
import com.apap.farmasi.rest.BaseResponse;

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

	@PostMapping(value="/medical-supplies/permintaan/")
	public List<PermintaanModel> createPermintaan(@RequestBody TambahPermintaanDetail permintaanDetail) {
		List<PermintaanModel> result = new ArrayList<PermintaanModel>();
		for (TambahPermintaanDetail.DetailRequest obat : permintaanDetail.getDetails()) {
			PermintaanModel permintaan = new PermintaanModel();
			permintaan.setIdPasien(permintaanDetail.getIdPasien());
			permintaan.setMedicalSupplies(medicalSuppliesService.getMedicalSuppliesById(obat.getId()));
			permintaan.setTanggal(Timestamp.valueOf(LocalDateTime.now()));
			permintaan.setJumlahMedicalSupplies(obat.getJumlah());
			permintaan.setStatusPermintaan(statusPermintaanService.findById('1'));
			//set default
			permintaan.setJadwalPermintaan(jadwalJagaService.getJadwalJagaById(Long.parseLong("1")));

			permintaanService.save(permintaan);
			result.add(permintaan);
		}
		return result;
	}


}
