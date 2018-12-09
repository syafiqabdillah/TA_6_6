package com.apap.farmasi.controller;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.apap.farmasi.model.JadwalJagaModel;
import com.apap.farmasi.model.PasienModel;
import com.apap.farmasi.model.PermintaanModel;
import com.apap.farmasi.rest.BillingDetail;
import com.apap.farmasi.rest.TambahPermintaanDetail;
import com.apap.farmasi.service.JadwalJagaService;
import com.apap.farmasi.service.PermintaanService;
import com.apap.farmasi.service.StatusPermintaanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.apap.farmasi.model.MedicalSuppliesModel;
import com.apap.farmasi.service.MedicalSuppliesService;

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
	public @ResponseBody List<MedicalSuppliesModel> getAllMedicalSupplies(){
		List<MedicalSuppliesModel> listOfMedSupplies = medicalSuppliesService.getAll();
		return listOfMedSupplies;
	}

	@PostMapping(value="/medical-supplies/permintaan/")
	public PermintaanModel createPermintaan(@RequestBody TambahPermintaanDetail permintaanDetail) {
			PermintaanModel permintaan = new PermintaanModel();
			permintaan.setIdPasien(permintaanDetail.getIdPasien());
			permintaan.setMedicalSupplies(medicalSuppliesService.getMedicalSuppliesByNama(permintaanDetail.getNamaObat()));
			permintaan.setTanggal(Timestamp.valueOf(LocalDateTime.now()));
			permintaan.setJumlahMedicalSupplies(permintaanDetail.getJumlah());
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
			if(!isStaffPresent)
				permintaan.setJadwalPermintaan(jadwalJagaService.getJadwalJagaById(Long.parseLong("1")));

			return permintaanService.save(permintaan);
	}

}
