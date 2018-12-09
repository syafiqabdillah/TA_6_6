package com.apap.farmasi.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import com.apap.farmasi.model.DetailMedicalSuppliesLabModel;
import com.apap.farmasi.model.MedicalSuppliesModel;
import com.apap.farmasi.model.PerencanaanModel;
import com.apap.farmasi.service.MedicalSuppliesService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class PageController {
	@Autowired
	MedicalSuppliesService medsupService;

	@RequestMapping(value ="/", method = RequestMethod.GET)
	public String home(Model model) throws IOException {
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

		List<DetailMedicalSuppliesLabModel> medsupLab = getDataFromLab();
		model.addAttribute("medsupLab", medsupLab);

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String authority = authentication.getAuthorities().iterator().next().getAuthority();
		model.addAttribute("mingguKe", mingguKe());
		model.addAttribute("authority", authority);
		model.addAttribute("perencanaan", perencanaan);
		model.addAttribute("listMedicalSupplies", listMedicalSupplies);
		return "home";
	}
	
	int mingguKe() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.WEEK_OF_MONTH);
	}

	boolean minggu1Atau3() {
		Calendar calendar = Calendar.getInstance();
		int minggu = calendar.get(Calendar.WEEK_OF_MONTH);
		System.out.println("ini minggu ke-" + minggu);
		return (minggu==1) || (minggu==3);
	}

	
	List<DetailMedicalSuppliesLabModel> getDataFromLab() throws IOException{
		//rest template 
		RestTemplate restTemplate = new RestTemplate();
		//membuat Object mapper 
    	ObjectMapper mapper = new ObjectMapper();
    	//path
    	String path = "https://44d5b7c7-1ad6-43ca-8645-2673bcad019b.mock.pstmn.io/lab/kebutuhan/perencanaan";
    	//json  
    	String jsonLab = restTemplate.getForObject(path, String.class);
    	JsonNode jsonNodeLab = mapper.readTree(jsonLab);
    	//mengambil list result
    	String result = jsonNodeLab.get("result").toString();
    	System.out.println("result = " + result);
    	//merubah string of map of Json menjad map of MedsupLab
    	List<DetailMedicalSuppliesLabModel> medsup = mapper.readValue(result, new TypeReference<ArrayList<DetailMedicalSuppliesLabModel>>(){});
    	//list

    	//test 
    	for (DetailMedicalSuppliesLabModel med : medsup) {
    		System.out.println(med.getNama() + "-" + med.getJumlah());
    	}
		return medsup;
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
}