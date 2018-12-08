package com.apap.farmasi.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.apap.farmasi.model.MedicalSuppliesModel;
import com.apap.farmasi.model.PerencanaanModel;
import com.apap.farmasi.model.StaffModel;
import com.apap.farmasi.service.MedicalSuppliesService;
import com.apap.farmasi.service.PerencanaanService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class PerencanaanController {
	@Autowired 
	PerencanaanService perencanaanService;
	
	@Autowired
	MedicalSuppliesService medsupService;
	
	@RequestMapping(value = "/medical-supplies/perencanaan", method = RequestMethod.GET)
	private String view(Model model) {
		List<PerencanaanModel> listPerencanaan = perencanaanService.getAll();
		model.addAttribute("listPerencanaan", listPerencanaan);
		return "view-perencanaan-pembelian-medical-supplies";
	}
	
	@RequestMapping(value = "/medical-supplies/updateStatus", method = RequestMethod.GET)
	private String updateStatusPerencanaan(Model model, @RequestParam("id") long id) {
		PerencanaanModel perencanaan = perencanaanService.getPerencanaan(id);
		model.addAttribute("perencanaan", perencanaan);
		return "update-status-perencanaan";
	}
	
	@RequestMapping(value = "/medical-supplies/updateStatus", method = RequestMethod.POST)
	private String postStatusPerencanaan(Model model, String status, long id) {
		PerencanaanModel oldP = perencanaanService.getPerencanaan(id);
		PerencanaanModel newP = perencanaanService.getPerencanaan(id);
		newP.setStatus(status);
		perencanaanService.updateStatusPerencanaan(oldP, newP);
		if(status.equals("Tersedia")) {
			medsupService.updateJumlahMedsup(oldP.getMedicalSupplies().getId(),oldP.getJumlah());
		}
		List<PerencanaanModel> listPerencanaan = perencanaanService.getAll();
		model.addAttribute("listPerencanaan", listPerencanaan);
		return "view-perencanaan-pembelian-medical-supplies";
	}
	
	@RequestMapping(value = "/medical-supplies/perencanaan/tambah", method = RequestMethod.POST)
	private String postPerencanaan(Model model, @ModelAttribute PerencanaanModel newPerencanaan) {
		perencanaanService.addPerencanaan(newPerencanaan);	
		System.out.println(newPerencanaan.getTanggal() + "--" + newPerencanaan.getStatus() + "--" + newPerencanaan.getJumlah());
		List<PerencanaanModel> listPerencanaan = perencanaanService.getAll();
		model.addAttribute("listPerencanaan", listPerencanaan);
		return "view-perencanaan-pembelian-medical-supplies";
	}
	
	private <List>MedicalSuppliesModel getDataFromLab() throws IOException{
		//rest template 
		RestTemplate restTemplate = new RestTemplate();
		//membuat Object mapper 
    	ObjectMapper mapper = new ObjectMapper();
    	//path
    	String path = "https://44d5b7c7-1ad6-43ca-8645-2673bcad019b.mock.pstmn.io/lab/kebutuhan/perencanaan";
    	//json map 
    	String jsonLab = restTemplate.getForObject(path, String.class);
    	JsonNode jsonNodeLab = mapper.readTree(jsonLab);
    	//mengambil result , yakni map
    	String resultMap = jsonNodeLab.get("result").toString();
    	System.out.println("result map : " + resultMap);
    	//merubah string of map of Json menjad map of MedsupLab
    	Map<String, StaffModel> mapStaff = mapper.readValue(resultMap, new TypeReference<HashMap<String, StaffModel>>(){});
    	
		return null;
	}
	
}
