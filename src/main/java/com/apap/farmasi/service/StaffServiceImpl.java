package com.apap.farmasi.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.apap.farmasi.model.StaffModel;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service 
@Transactional
public class StaffServiceImpl implements StaffService{

	@Override
	public List<StaffModel> getAll() throws IOException {
		//mengambil semua staff dari API asdos
		
		//data staff
    	RestTemplate restTemplate = new RestTemplate();
    	String path = "http://si-appointment.herokuapp.com/api/6/getAllStaffFarmasi";
    	//JSON data list staff dari API asdos
    	String JSONstaff = restTemplate.getForObject(path, String.class);
    	//membuat Object mapper 
    	ObjectMapper mapper = new ObjectMapper();
    	//melakukan mapping dari data 
    	JsonNode jsonNode = mapper.readTree(JSONstaff);
   
    	//mengambil result , yakni string of list of JSON 
    	String result = jsonNode.get("result").toString();
    	System.out.println();
    	//merubah string of list of JSON menjadi list of JSON 
    	List<String> listStaffString = Arrays.asList(result.split(","));
    	
    	//membuat list of StaffModel dari data yang didapat 
    	System.out.println("membuat list of staff dari data yang didapat");
    	List<StaffModel> listStaff = new ArrayList<>();
    	
    	//membuat StaffModel dari data yang didapat, kemudian memasukkan ke list
    	for (String isi : listStaffString) {
    		System.out.println(isi);
//    		
//    		JsonNode staffJson = mapper.readTree(isi);
//    		String id = staffJson.get("id").toString();
//    		System.out.println("aaaaaa" + id);
//    		String nama = staffJson.get("nama").toString();
//    		System.out.println("aaaaaa" + nama);
//    		
//    		//membuat StaffModel
//    		StaffModel staff = new StaffModel(); 
//    		//memasukkan atribut 
//    		staff.setId(Long.parseLong(id));
//    		staff.setNama(nama);
//    		
//    		//memasukkan StaffModel baru ke list 
//    		listStaff.add(staff);
    		
    		System.out.println();
    	}
    	
    	for (StaffModel staff : listStaff) {
    		System.out.println(staff.getNama() +  " " + staff.getId());
    	}
    	
    	// sumur: 
    	// https://stackoverflow.com/questions/26738386/liststring-to-string-back-to-list-in-java
    	// https://www.baeldung.com/jackson-object-mapper-tutorial
    	// https://www.mkyong.com/java/jackson-2-convert-java-object-to-from-json/	
    	
		return null;
	}

}
