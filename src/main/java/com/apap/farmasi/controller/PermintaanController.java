package com.apap.farmasi.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.apap.farmasi.model.DetailPermintaanModel;
import com.apap.farmasi.model.PasienModel;
import com.apap.farmasi.model.PermintaanModel;
import com.apap.farmasi.model.StaffModel;
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
	
	@GetMapping(value="/medical-supplies/permintaan")
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
}
