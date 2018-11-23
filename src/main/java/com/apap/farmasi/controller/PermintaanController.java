package com.apap.farmasi.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.apap.farmasi.model.DetailPermintaanModel;
import com.apap.farmasi.model.PasienModel;
import com.apap.farmasi.model.PermintaanModel;
//import com.apap.farmasi.model.StaffModel;
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
    	
    	//API buat nama staf 
    	String path = "http://si-appointment.herokuapp.com/api/getStaff?listId=";
    	for(String idStaff : listIdStaff) {
    		path = path + idStaff + ",";
    	}
    	String ending = "&resultType=List";
    	String url = path + ending;			
    	//JSON data list nama staff dari API asdos
    	String JSONstaff = restTemplate.getForObject(url, String.class);
    	//membuat Object mapper 
    	ObjectMapper mapper = new ObjectMapper();
    	//melakukan mapping dari data 
    	JsonNode jsonNode = mapper.readTree(JSONstaff);
    	//mengambil result , yakni string of list nama staf of JSON 
    	String result = jsonNode.get("result").toString();
		//merubah string of list of JSON menjadi list of Staff 
    	List<StaffModel> listStaff = mapper.readValue(result, new TypeReference<List<StaffModel>>(){});
    	List<String> listNamaStaff = new ArrayList<>();
    	for (StaffModel staff : listStaff) {
    		listNamaStaff.add(staff.getNama());
    	}
    	
    	//API staff pake map 
    	
    	
    	//API buat nama pasien 
    	String pathPasien = "http://si-appointment.herokuapp.com/api/getPasien?listId=";
    	for(String idPasien : listIdPasien) {
    		pathPasien = pathPasien + idPasien + ",";
    	}
    	String endingPathPasien = "&resultType=List";
    	String urlPasien = pathPasien + endingPathPasien;
    	//JSON data list nama pasien dari API asdos
    	String JSONpasien = restTemplate.getForObject(urlPasien, String.class);
    	//mapping data 
    	JsonNode jsonPasien = mapper.readTree(JSONpasien);
    	//mengambil result 
    	String resultPasien = jsonPasien.get("result").toString();
    	//merubah string of list of JSON menjadi list of Pasien
    	List<PasienModel> listPasien = mapper.readValue(resultPasien, new TypeReference<List<PasienModel>>(){});
    	List<String> listNamaPasien = new ArrayList<>();
    	for (PasienModel pasien : listPasien) {
    		listNamaPasien.add(pasien.getNama());
    	}
    	
    	
    	//menggabungkan permintaan, nama staf, dan nama pasien pada suatu object DetailPermintaan
    	List<DetailPermintaanModel> detailPermintaan = new ArrayList<>();
    	
    	for (int i = 0 ; i < listNamaPasien.size() ; i++) {
    		DetailPermintaanModel detail = new DetailPermintaanModel(listPermintaan.get(i), listNamaStaff.get(i), listNamaPasien.get(i));
    		detailPermintaan.add(detail);
    	}
    	
		model.addAttribute("detail", detailPermintaan);
		
    	return "view-all-permintaan";
    }
	
	private String getNamaStaff(long id) {
		String result = "";
		return result;
	}
}
