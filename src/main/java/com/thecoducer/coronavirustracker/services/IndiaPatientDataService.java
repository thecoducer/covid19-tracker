package com.thecoducer.coronavirustracker.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.PostConstruct;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.thecoducer.coronavirustracker.models.PatientStats;

@Service
public class IndiaPatientDataService {
	
	private SortedMap<Integer, PatientStats> patientData = new TreeMap<>();

	@PostConstruct
	public void fetchPatientData() throws MalformedURLException, ParseException, IOException {
		
		JSONObject jo = (JSONObject) new JSONParser().parse(IOUtils.toString(new URL("https://api.covid19india.org/raw_data.json").openStream()));
		
		JSONArray raw_data = (JSONArray) jo.get("raw_data");
		
		SortedMap<Integer, PatientStats> newPatientData = new TreeMap<>();
		
		int i = 0;
		
		for(Object r : raw_data) {
			
			JSONObject r_obj = (JSONObject) r;
			
			String age = (String) r_obj.get("agebracket");
			String gender = (String) r_obj.get("gender");
			
			if(age.equals("") == false && gender.equals("") == false) {
				PatientStats patient = new PatientStats();
				
				patient.setAge(formatAge(age));
				patient.setGender(gender);
				
				newPatientData.put(i++, patient);
			}
			
		}
		
		this.patientData = newPatientData;
	}
	
	public static String formatAge(String age) {
		
		int calculatedAge = 0;
		
		if(age.contains("-")) {
			String[] numArray = age.split("-", 2);
			
			calculatedAge = (int) (Integer.parseInt(numArray[0]) + Integer.parseInt(numArray[1])) / 2;
			
			return String.valueOf(calculatedAge);
		}else {
			return age;
		}
	
	}
	
	public SortedMap<Integer, PatientStats> getPatientData(){
		return patientData;
	}
}
