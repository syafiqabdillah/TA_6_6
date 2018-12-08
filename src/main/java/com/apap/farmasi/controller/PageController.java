package com.apap.farmasi.controller;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apap.farmasi.model.MedicalSuppliesModel;
import com.apap.farmasi.model.PerencanaanModel;
import com.apap.farmasi.service.MedicalSuppliesService;

@Controller
public class PageController {
	@Autowired
	MedicalSuppliesService medsupService;
	
	@RequestMapping(value ="/", method = RequestMethod.GET)
	public String home(Model model) {
		List<MedicalSuppliesModel> listMedicalSupplies = medsupService.getAll();
		PerencanaanModel perencanaan = new PerencanaanModel();
		java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		perencanaan.setTanggal(date);
		model.addAttribute("perencanaan", perencanaan);
		model.addAttribute("listMedicalSupplies", listMedicalSupplies);
		return "home";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
}
