package com.apap.farmasi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.apap.farmasi.model.PerencanaanModel;
import com.apap.farmasi.service.PerencanaanService;


@Controller
public class PerencanaanController {
	@Autowired 
	PerencanaanService perencanaanService;
	
	@RequestMapping(value = "/medical-supplies/perencanaan", method = RequestMethod.GET)
	private String view(Model model) {
		PerencanaanModel perencanaan = perencanaanService.getPerencanaan();
		model.addAttribute("perencanaan", perencanaan);
		return "view-perencanaan-pembelian-medical-supplies";
	}
	
}
