package com.apap.farmasi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.apap.farmasi.model.JadwalJagaModel;
import com.apap.farmasi.model.StaffModel;
import com.apap.farmasi.service.JadwalJagaService;
import com.apap.farmasi.service.PermintaanService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class JadwalJagaController {
	@Autowired
	JadwalJagaService jadwalJagaService;
	@Autowired
	PermintaanService permintaanService;
	
	@RequestMapping(value = "medical-supplies/jadwal-staf/tambah", method = RequestMethod.GET)
	public String addJadwalJagaGET(Model model) {
		JadwalJagaModel jadwalJaga = new JadwalJagaModel();
		model.addAttribute("jadwalJaga", jadwalJaga);
		
		//API get all staff
		RestTemplate restTemplate = new RestTemplate();
    	String url = "http://si-appointment.herokuapp.com/api/6/getAllStaffFarmasi";
    	String staff = restTemplate.getForObject(url, String.class);
    	ObjectMapper mapper = new ObjectMapper();

    	List<String> listNamaStaff = new ArrayList<>();
    	List<StaffModel> listStaff = new ArrayList<>();
    	
    	try {
	    	JsonNode jsonNode = mapper.readTree(staff);
	    	
	    	//Iterasi result yang bentuknya list json
	    	for (int i=0; i<jsonNode.get("result").size(); i++) {
	    		//Ambil nama satu persatu
	    		//Masukin ke list
//	    		listIdStaff.add(jsonNode.get("result").get(i).get("id").toString());
//	    		listNamaStaff.add(jsonNode.get("result").get(i).get("nama").toString());
	    		
	    		String id = jsonNode.get("result").get(i).get("id").toString();
	    		String nama = jsonNode.get("result").get(i).get("nama").toString();
	    		
	    		StaffModel staffModel = new StaffModel(Integer.parseInt(id), nama);
	    		listStaff.add(staffModel);
	    	}
	    	
    	} catch(Exception e) {}
    	
    	model.addAttribute("listStaff", listStaff);
    	
		return "addJadwal";
	}
	
	@RequestMapping(value = "medical-supplies/jadwal-staf/tambah", method = RequestMethod.POST)
	public String addJadwalJaga(Model model, @ModelAttribute JadwalJagaModel jadwalJaga) {
		
		jadwalJagaService.addJadwalJaga(jadwalJaga);
		
		return "redirect:";
	}
	
	//UPDATE
	@RequestMapping(value = "medical-supplies/jadwal-staf/{idJadwal}", method = RequestMethod.GET)
	public String updateJadwalJagaGET(@PathVariable("idJadwal") long idJadwal, Model model) {
		JadwalJagaModel jadwalJaga = jadwalJagaService.getJadwalJagaById(idJadwal);
		model.addAttribute("jadwalJaga", jadwalJaga);
		
		//API get all staff
		RestTemplate restTemplate = new RestTemplate();
    	String url = "http://si-appointment.herokuapp.com/api/6/getAllStaffFarmasi";
    	String staff = restTemplate.getForObject(url, String.class);
    	ObjectMapper mapper = new ObjectMapper();

    	List<StaffModel> listStaff = new ArrayList<>();
    	
    	try {
	    	JsonNode jsonNode = mapper.readTree(staff);
	    	
	    	//Iterasi result yang bentuknya list json
	    	for (int i=0; i<jsonNode.get("result").size(); i++) {
	    		//Ambil nama satu persatu
	    		//Masukin ke list
//	    		listIdStaff.add(jsonNode.get("result").get(i).get("id").toString());
//	    		listNamaStaff.add(jsonNode.get("result").get(i).get("nama").toString());
	    		
	    		String id = jsonNode.get("result").get(i).get("id").toString();
	    		String nama = jsonNode.get("result").get(i).get("nama").toString();
	    		
	    		StaffModel staffModel = new StaffModel(Integer.parseInt(id), nama);
	    		listStaff.add(staffModel);
	    	}
	    	
    	} catch(Exception e) {}
    	
    	model.addAttribute("listStaff", listStaff);
    	
		return "updateJadwal";
	}
	
	@RequestMapping(value = "medical-supplies/jadwal-staf/", method = RequestMethod.POST)
	public String updateJadwalJagaPOST(Model model, @ModelAttribute JadwalJagaModel jadwalJaga) {
		
		jadwalJagaService.updateJadwalJaga(jadwalJaga.getId(),jadwalJaga);
		
		return "redirect:";
	}


	//Menampilkan data seluruh jabatan (fitur 9)
	@RequestMapping("medical-supplies/jadwal-staf/")
	public String viewAllJabatan(Model model) {
		List<JadwalJagaModel> jadwalJaga = jadwalJagaService.getAll();
		
		//API get all staff
		RestTemplate restTemplate = new RestTemplate();
    	String url = "http://si-appointment.herokuapp.com/api/6/getAllStaffFarmasi";
    	String staff = restTemplate.getForObject(url, String.class);
    	ObjectMapper mapper = new ObjectMapper();

    	List<String> listNamaStaff = new ArrayList<>();
    	List<StaffModel> listStaff = new ArrayList<>();
    	
    	try {
	    	JsonNode jsonNode = mapper.readTree(staff);
	    	
	    	//Iterasi result yang bentuknya list json
	    	for (int i=0; i<jsonNode.get("result").size(); i++) {
	    		//Ambil nama satu persatu
	    		//Masukin ke list
//			    		listIdStaff.add(jsonNode.get("result").get(i).get("id").toString());
//			    		listNamaStaff.add(jsonNode.get("result").get(i).get("nama").toString());
	    		
	    		String id = jsonNode.get("result").get(i).get("id").toString();
	    		String nama = jsonNode.get("result").get(i).get("nama").toString();
	    		
	    		StaffModel staffModel = new StaffModel(Integer.parseInt(id), nama);
	    		listStaff.add(staffModel);
	    	}
			    	
		    } catch(Exception e) {}
		    
		model.addAttribute("listStaff", listStaff);
		
		model.addAttribute("jadwalJaga", jadwalJaga);
		
		return "view-all-jadwal";
	}

}

//
//ObjectMapper mapper = new ObjectMapper();
//
//
//
//List<String> listIdStaff = new ArrayList<>();
//for (PermintaanModel permintaan : listPermintaan) {
//	listIdStaff.add(Long.toString(permintaan.getJadwalPermintaan().getIdStaff()));
//}
//
//String path = "http://si-appointment.herokuapp.com/api/getStaff?listId=1,2,3";
//String end = "&resultType=List";
//
//for (String idStaff : listIdStaff) {
//	path = path + idStaff + ",";
//}
//String url = path + end;
//try {
//	JsonNode jsonNode = mapper.readTree(staff);
//	String result = jsonNode.get("result").toString();
//	System.out.println(result);
//	List<StaffModel> listStaff = mapper.readValue(result, new TypeReference<List<StaffModel>>(){});
//	List<String> listNamaStaff = new ArrayList<>();
//	for (StaffModel orang : listStaff) {
//		listNamaStaff.add(orang.getNama());
//	}
//	
//	System.out.println(listNamaStaff.get(0));
//}catch(Exception e) {}
//
