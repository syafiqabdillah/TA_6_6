package com.apap.farmasi.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.apap.farmasi.model.MedicalSuppliesModel;
import com.apap.farmasi.service.MedicalSuppliesService;
import com.apap.farmasi.rest.ObatDetail;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@Controller
public class MedicalSuppliesController {
	@Autowired
	private MedicalSuppliesService medicalSuppliesService;

	@Autowired
	RestTemplate restTemplate;

	@Bean
	public RestTemplate rest() {
		return new RestTemplate();
	}
	
	@RequestMapping(value = "/medical-supplies/")
	private String viewAll(Model model) {
		List<MedicalSuppliesModel> listOfMedicalSupplies = medicalSuppliesService.getAll();
		model.addAttribute("listOfMedicalSupplies",listOfMedicalSupplies);
		return "view-all-medical-supplies";
	}
	
	@RequestMapping(value="/medical-supplies/{id}", method = RequestMethod.GET)
	private String viewPilot(@PathVariable("id") long id, Model model ) {
		MedicalSuppliesModel medicalSupplies = medicalSuppliesService.getMedicalSuppliesById(id);
		model.addAttribute("medicalSupplies", medicalSupplies);
		return "view-medical-supplies";
	}
	
	@RequestMapping(value = "/medical-supplies/{id}/{jumlah}")
	private String checkOutSupplies(@PathVariable(value="id") long id,
									@PathVariable(value="jumlah") int jumlah, Model model) {
		//String path = "";
		MedicalSuppliesModel obat = medicalSuppliesService.getMedicalSuppliesById(id);
		ObatDetail obatDetail = new ObatDetail();
		obatDetail.setNama(obat.getNama());
		obatDetail.setId(obat.getId());
		obatDetail.setJumlah(jumlah);

		//ObatDetail result = restTemplate.postForObject(path,obatDetail,ObatDetail.class);

		obat.setJumlah(obat.getJumlah()-jumlah);
		medicalSuppliesService.save(obat);
		
		//update halaman view all med sup
		List<MedicalSuppliesModel> listOfMedicalSupplies = medicalSuppliesService.getAll();
		model.addAttribute("listOfMedicalSupplies",listOfMedicalSupplies);
		return "view-all-medical-supplies";
	}
}
