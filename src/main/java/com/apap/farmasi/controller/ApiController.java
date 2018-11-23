package com.apap.farmasi.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
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
