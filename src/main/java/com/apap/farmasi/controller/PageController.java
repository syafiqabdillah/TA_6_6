package com.apap.farmasi.controller;

import java.util.ArrayList;
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
		List<MedicalSuppliesModel> listMedicalSupplies = new ArrayList<>();
		PerencanaanModel perencanaan = new PerencanaanModel();
		java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		perencanaan.setTanggal(date);
		if (minggu1Atau3()) {
			System.out.println("ini minggu 1 atau 3");
		}
		if (minggu1Atau3()) {
			//kalo minggu 1 atau 3, bebas 
			listMedicalSupplies = medsupService.getAll();
		} else {
			//kalo gak minggu 1 atau 3, cuma yang urgent 
			List<MedicalSuppliesModel> allMedsup = medsupService.getAll();
			for (MedicalSuppliesModel medsup : allMedsup) {
				//cuma masukin yang urgent 
				if (medsup.getJenisMedicalSupplies().getUrgent().getId() == 1) {
					listMedicalSupplies.add(medsup);
				} else {
					System.out.println(medsup.getNama() + " tidak dimasukkan ke pilihan karena tidak urgent ");
				}
			}
		}
		
		model.addAttribute("perencanaan", perencanaan);
		model.addAttribute("listMedicalSupplies", listMedicalSupplies);
		return "home";
	}
	
	boolean minggu1Atau3() {
		Calendar calendar = Calendar.getInstance();
		int minggu = calendar.get(Calendar.WEEK_OF_MONTH);
		System.out.println("ini minggu ke-" + minggu);
		return (minggu==1) || (minggu==3);
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
}
