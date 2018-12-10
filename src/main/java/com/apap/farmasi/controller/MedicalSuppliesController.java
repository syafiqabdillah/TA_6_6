package com.apap.farmasi.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apap.farmasi.model.JenisMedicalSuppliesModel;
import com.apap.farmasi.model.MedicalSuppliesModel;
import com.apap.farmasi.service.JenisMedicalSuppliesService;
import com.apap.farmasi.service.MedicalSuppliesService;
import com.apap.farmasi.rest.ObatDetail;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import javax.persistence.criteria.CriteriaBuilder;

@Controller
public class MedicalSuppliesController {
	@Autowired
	private MedicalSuppliesService medicalSuppliesService;

	@Autowired
	private JenisMedicalSuppliesService jenisMedicalSuppliesService;
	
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
	private String viewMedicalSupplies(@PathVariable("id") long id, Model model ) {
		MedicalSuppliesModel medicalSupplies = medicalSuppliesService.getMedicalSuppliesById(id);
		model.addAttribute("medicalSupplies", medicalSupplies);
		return "view-medical-supplies";
	}
	
	@RequestMapping(value="/medical-supplies/tambah", method = RequestMethod.GET)
	private String add(Model model) {
		List<JenisMedicalSuppliesModel> listOfJenisMedicalSupplies = jenisMedicalSuppliesService.getAll();
		model.addAttribute("medicalSupplies", new MedicalSuppliesModel());
		model.addAttribute("jenisMedicalSupplies", listOfJenisMedicalSupplies);
		return "add-medical-supplies";
	}
	
	@RequestMapping(value="/medical-supplies/tambah", method = RequestMethod.POST)
	private String addMedicalSuppliesSubmit(@ModelAttribute MedicalSuppliesModel medicalSupplies) {
		medicalSuppliesService.addMedicalSupplies(medicalSupplies);
		return "redirect:";
	}
	
	@RequestMapping(value="/medical-supplies/ubah/{id}", method=RequestMethod.GET)
	private String updateMedicalSupplies(@PathVariable("id") long id, Model model) {
		MedicalSuppliesModel medicalSupplies = medicalSuppliesService.getMedicalSuppliesById(id);
		model.addAttribute("medicalSupplies", medicalSupplies);
		List<JenisMedicalSuppliesModel> listOfJenisMedicalSupplies = jenisMedicalSuppliesService.getAll();
		model.addAttribute("jenisMedicalSupplies", listOfJenisMedicalSupplies);
		return "update-medical-supplies";
	}
	@RequestMapping (value = "/medical-supplies/ubah", method = RequestMethod.POST)
	private String updateMedicalSuppliesSubmit(@ModelAttribute MedicalSuppliesModel medicalSupplies){
		medicalSuppliesService.updateMedicalSupplies(medicalSupplies, medicalSupplies.getId());
		long idMS = medicalSupplies.getId();
		return "redirect:/medical-supplies/" + idMS;
	}

	@RequestMapping(value = "/medical-supplies/checkout/{id}", method = RequestMethod.GET)
	private String giveSupplies(@PathVariable(value="id") long id,
								Model model) {
		MedicalSuppliesModel obat = medicalSuppliesService.getMedicalSuppliesById(id);
		model.addAttribute("obat", obat);
		model.addAttribute("obatCheckOut", obat);

		return "give-medical-supplies";
	}

	@RequestMapping(value = "/medical-supplies/checkout/{id}", method = RequestMethod.POST)
	private String checkOutSupplies(@PathVariable(value="id") long id,
									@ModelAttribute MedicalSuppliesModel obatCheckOut, Model model) {
		//String path = "";
		MedicalSuppliesModel obat = medicalSuppliesService.getMedicalSuppliesById(id);
		ObatDetail obatDetail = new ObatDetail();
		obatDetail.setNama(obat.getNama());
		obatDetail.setId(obat.getId());
		obatDetail.setJumlah(obatCheckOut.getJumlah());

		//ObatDetail result = restTemplate.postForObject(path,obatDetail,ObatDetail.class);

		obat.setJumlah(obat.getJumlah()-obatCheckOut.getJumlah());
		medicalSuppliesService.save(obat);
		
		//update halaman view all med sup
		List<MedicalSuppliesModel> listOfMedicalSupplies = medicalSuppliesService.getAll();
		model.addAttribute("listOfMedicalSupplies",listOfMedicalSupplies);	
		return "redirect:/medical-supplies/";
	}
}
