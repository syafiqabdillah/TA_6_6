package com.apap.farmasi.controller;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.apap.farmasi.model.*;
import com.apap.farmasi.repository.StatusPermintaanDB;
import com.apap.farmasi.rest.BillingDetail;
import com.apap.farmasi.rest.BillingResponse;
import com.apap.farmasi.rest.Settings;
import com.apap.farmasi.service.MedicalSuppliesService;
import com.apap.farmasi.service.StatusPermintaanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.apap.farmasi.service.PermintaanService;
import com.apap.farmasi.service.StaffService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class PermintaanController {
	@Autowired
	PermintaanService permintaanService;
	
	@Autowired 
	StaffService staffService;

	@Autowired
	StatusPermintaanService statusPermintaanService;

	@Autowired
	MedicalSuppliesService medicalSuppliesService;

	@RequestMapping(value="/medical-supplies/permintaan/", method = RequestMethod.GET)
    public String viewAllPermintaan(Model model) throws IOException {
		List<PermintaanModel> listPermintaan = permintaanService.getAll();
		
		//mengambil nama staf semua yang ada di permintaan
    	RestTemplate restTemplate = new RestTemplate();
    	//list semua id staf dan id pasien 
    	List<String> listIdStaff = new ArrayList<>();
    	List<String> listIdPasien = new ArrayList<>();
    	for (PermintaanModel permintaan : listPermintaan) {
    		listIdStaff.add(Long.toString(permintaan.getJadwalPermintaan().getIdStaff()));
    		listIdPasien.add(Long.toString(permintaan.getIdPasien()));
    	}

    	//membuat Object mapper 
    	ObjectMapper mapper = new ObjectMapper();
    	
    	//API staff pake map 
    	String pathMap = "http://si-appointment.herokuapp.com/api/getStaff?listId=";
    	for(String idStaff : listIdStaff) {
    		pathMap = pathMap + idStaff + ",";
    	}
    	String endingPathMap = "&resultType=Map";
    	String urlMap = pathMap + endingPathMap;  
    	//json map 
    	String jsonMapStaff = restTemplate.getForObject(urlMap, String.class);
    	JsonNode jsonNodeMap = mapper.readTree(jsonMapStaff);
    	//mengambil result , yakni map
    	String resultMap = jsonNodeMap.get("result").toString();
    	System.out.println("result map : " + resultMap);
    	//merubah string of map of Json menjad map of Staff
    	Map<String, StaffModel> mapStaff = mapper.readValue(resultMap, new TypeReference<HashMap<String, StaffModel>>(){});
    	//membuat key list dari permintaan berisi id staff yang mau ditampilin namanya 
    	ArrayList<String> listKeyIdStaff = new ArrayList<>();
    	//memasukkan key berupa id staff 
    	for (PermintaanModel permintaan : listPermintaan) {
    		listKeyIdStaff.add(Integer.toString(permintaan.getJadwalPermintaan().getIdStaff()));
    	}
    	List<String> listNamaStaffPakeMap = new ArrayList<>();
    	//mengisi nama staff berdasarkan keylist is staff
    	for (String id : listKeyIdStaff) {
    		listNamaStaffPakeMap.add(mapStaff.get(id).getNama());
    	}
    	System.out.println("nama staff pake map:");
    	for (String nama : listNamaStaffPakeMap) {
    		System.out.println(nama);
    	}
    	//gabungin permintaan dengan staf dari hasil pake map
    	List<DetailPermintaanModel> detailPermintaanPakeMap = new ArrayList<>(); 
    	
    	for (int i = 0 ; i < listPermintaan.size() ; i++) {
    		DetailPermintaanModel detailNew = new DetailPermintaanModel(listPermintaan.get(i), listNamaStaffPakeMap.get(i), "");
    		detailPermintaanPakeMap.add(detailNew);
    	}
    	
		model.addAttribute("detail", detailPermintaanPakeMap);
		
    	return "view-all-permintaan";
    }

	@RequestMapping(value="/medical-supplies/permintaan/ubah/{idPermintaan}", method = RequestMethod.GET)
	public String updatePermintaan(@PathVariable(value="idPermintaan") long id, Model model) {
		PermintaanModel permintaan = permintaanService.findById(id);

		List<StatusPermintaanModel> statusPermintaanList = statusPermintaanService.findAll();

		if (permintaan.getJumlahMedicalSupplies() > permintaan.getMedicalSupplies().getJumlah())
			statusPermintaanList.remove(statusPermintaanService.findById(2));

		model.addAttribute("permintaan", permintaan);
		model.addAttribute("statusPermintaan", statusPermintaanList);

		return "update-permintaan";
	}

	@RequestMapping(value="/medical-supplies/permintaan/ubah",method = RequestMethod.POST)
	public String permintaanUpdated(@ModelAttribute PermintaanModel permintaan) {
		PermintaanModel permintaanLama = permintaanService.findById(permintaan.getId());
		permintaanLama.setStatusPermintaan(permintaan.getStatusPermintaan());
		permintaanService.save(permintaanLama);

		if (permintaanLama.getStatusPermintaan().getNama().equals("diterima")) {
			MedicalSuppliesModel obat = medicalSuppliesService.getMedicalSuppliesById(permintaanLama.getMedicalSupplies().getId());
			obat.setJumlah(obat.getJumlah()-permintaanLama.getJumlahMedicalSupplies());
			medicalSuppliesService.save(obat);

			String path = Settings.billingUrl;
			RestTemplate restTemplate = new RestTemplate();
			BillingDetail detail = new BillingDetail(permintaanLama.getIdPasien());
			detail.setJumlahTagihan(permintaanLama.getJumlahMedicalSupplies());
			LocalDate date = permintaan.getTanggal().toLocalDateTime().toLocalDate();
			detail.setTanggalTagihan(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

			restTemplate.postForObject(path,detail, BillingResponse.class);
		}

		return "redirect:/medical-supplies/permintaan/";
	}

}
