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

@Controller
public class MedicalSuppliesController {
	@Autowired
	private MedicalSuppliesService medicalSuppliesService;
	
	@RequestMapping("/medical-supplies")
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
	
}
