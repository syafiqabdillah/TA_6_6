package com.apap.farmasi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.apap.farmasi.model.PermintaanModel;
//import com.apap.farmasi.model.StaffModel;
import com.apap.farmasi.service.PermintaanService;

@Controller
public class PermintaanController {
	@Autowired
	PermintaanService permintaanService;

	@GetMapping(value="/medical-supplies/permintaan")
    public String viewAllPermintaan(Model model) {
		List<PermintaanModel> listPermintaan = permintaanService.getAll();
		model.addAttribute("listPermintaan", listPermintaan);
		
		//data staff
//    	RestTemplate restTemplate = new RestTemplate();
//    	String path = "http://si-appointment.herokuapp.com/api/6/getAllStaffFarmasi";
//    	StaffModel staff = restTemplate.getForObject(path, StaffModel.class);
//    	System.out.println(staff);
//    	
    	return "view-all-permintaan";
    }
}
