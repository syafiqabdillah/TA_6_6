package com.apap.farmasi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.apap.farmasi.model.MedicalSuppliesModel;
import com.apap.farmasi.service.MedicalSuppliesService;

@RestController
@RequestMapping("/api")
public class ApiController {
	@Autowired
	MedicalSuppliesService medicalSuppliesService;
	
	@GetMapping(value="/daftar-medical-service")
	public @ResponseBody List<MedicalSuppliesModel> getAllMedicalSupplies(){
		List<MedicalSuppliesModel> listOfMedSupplies = medicalSuppliesService.getAll();
		return listOfMedSupplies;
	}





}
