package com.thecoducer.coronavirustracker.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.PostConstruct;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.thecoducer.coronavirustracker.models.PatientStats;

@Service
public class IndiaPatientDataService {

	private SortedMap<Integer, PatientStats> patientData = new TreeMap<>();

	private String avgMaleRecovered;
	private String avgFemaleRecovered;
	private String avgMaleDied;
	private String avgFemaleDied;
	
	private String malesPercentage;
	private String femalesPercentage;
	
	private int eldestAffected;
	private int youngestAffected;
	
	private int patientsDataCount;

	@PostConstruct
	@Scheduled(cron = "0 0/5 * * * *")
	public void fetchPatientData() throws MalformedURLException, ParseException, IOException {

		JSONObject jo = (JSONObject) new JSONParser()
				.parse(IOUtils.toString(new URL("https://api.covid19india.org/raw_data.json").openStream()));

		JSONArray raw_data = (JSONArray) jo.get("raw_data");

		SortedMap<Integer, PatientStats> newPatientData = new TreeMap<>();
		
		DecimalFormat df = new DecimalFormat("#.##");

		int i = 0;
		double avgAgeMaleRec = 0.0;
		double avgAgeMaleDied = 0.0;
		double mcount = 0;
		double fcount = 0;
		double count = 0;
		double genderknowncount = 0;
		double ageknowncount = 0;
		double genderAndRecvKnown = 0;
		double genderAndDiedKnown = 0;
		
		int agemin = Integer.MAX_VALUE;
		int agemax = Integer.MIN_VALUE;

		for (Object r : raw_data) {

			JSONObject r_obj = (JSONObject) r;

			String age = (String) r_obj.get("agebracket");
			String gender = (String) r_obj.get("gender");
			String currentstatus = (String) r_obj.get("currentstatus");
			
			//if age, gender and currentstatus are all null values, just skip
			if (age.equals("") && gender.equals("") && currentstatus.equals("")) {
				continue;
			}
			
			PatientStats patient = new PatientStats();

			patient.setAge(formatAge(age));
			patient.setGender(gender.toLowerCase());
			patient.setCurrentStatus(currentstatus.toLowerCase());

			newPatientData.put(i++, patient);
			
			if(patient.getGender().equals("m")) {
				mcount++;
			}
			
			if(patient.getGender().equals("f")) {
				fcount++;
			}
			
			if(patient.getGender().equals("") == false) {
				genderknowncount++;
			}
			
			if(patient.getAge().equals("") == false) {
				int currAge = Integer.parseInt(patient.getAge());
				
				if(currAge > agemax) {
					agemax = currAge;
				}else if(currAge < agemin){
					agemin = currAge;
				}
			}
			
			if(patient.getGender().equals("") == false && (patient.getCurrentStatus().equals("recovered"))) {
				genderAndRecvKnown++;
			}
			
			if(patient.getGender().equals("") == false && (patient.getCurrentStatus().equals("deceased"))) {
				genderAndDiedKnown++;
			}
			
			if(patient.getAge().equals("") == false && patient.getGender().equals("m") && patient.getCurrentStatus().equals("recovered")) {
				avgAgeMaleRec += Double.parseDouble(patient.getAge());
			}

			if(patient.getAge().equals("") == false && patient.getGender().equals("m") && patient.getCurrentStatus().equals("deceased")) {
				avgAgeMaleDied += Double.parseDouble(patient.getAge());
			}
			
			count++;
		}
		
		IndiaPatientDataService ipds = new IndiaPatientDataService();
		
		ipds.setMalesPercentage(df.format((mcount/genderknowncount)*100));
		ipds.setFemalesPercentage(df.format((fcount/genderknowncount)*100));
		
		ipds.setEldestAffected(agemax);
		ipds.setYoungestAffected(agemin);
		
		ipds.setAvgMaleRecovered(df.format(avgAgeMaleRec/genderAndRecvKnown));
		ipds.setAvgMaleDied(df.format(avgAgeMaleDied/genderAndDiedKnown));

		this.patientData = newPatientData;
		
		
		System.out.println(ipds.getYoungestAffected());
		System.out.println(ipds.getEldestAffected());
		System.out.println(ipds.getAvgMaleRecovered());
		System.out.println(ipds.getAvgMaleDied());
		
		
	}


	public static String formatAge(String age) {

		int calculatedAge = 0;

		if (age.contains("-")) {
			String[] numArray = age.split("-", 2);

			calculatedAge = (int) (Integer.parseInt(numArray[0]) + Integer.parseInt(numArray[1])) / 2;

			return String.valueOf(calculatedAge);
		} else {
			return age;
		}

	}

	public void setAvgMaleRecovered(String avgMaleRecovered) {
		this.avgMaleRecovered = avgMaleRecovered;
	}

	public void setAvgFemaleRecovered(String avgFemaleRecovered) {
		this.avgFemaleRecovered = avgFemaleRecovered;
	}

	public void setAvgMaleDied(String avgMaleDied) {
		this.avgMaleDied = avgMaleDied;
	}

	public void setAvgFemaleDied(String avgFemaleDied) {
		this.avgFemaleDied = avgFemaleDied;
	}

	public void setMalesPercentage(String malesPercentage) {
		this.malesPercentage = malesPercentage;
	}

	public void setFemalesPercentage(String femalesPercentage) {
		this.femalesPercentage = femalesPercentage;
	}

	public void setEldestAffected(int eldestAffected) {
		this.eldestAffected = eldestAffected;
	}

	public void setYoungestAffected(int youngestAffected) {
		this.youngestAffected = youngestAffected;
	}

	public void setPatientsDataCount(int patientsDataCount) {
		this.patientsDataCount = patientsDataCount;
	}

	public SortedMap<Integer, PatientStats> getPatientData() {
		return patientData;
	}

	public String getAvgMaleRecovered() {
		return avgMaleRecovered;
	}

	public String getAvgFemaleRecovered() {
		return avgFemaleRecovered;
	}

	public String getAvgMaleDied() {
		return avgMaleDied;
	}

	public String getAvgFemaleDied() {
		return avgFemaleDied;
	}

	public String getMalesPercentage() {
		return malesPercentage;
	}

	public String getFemalesPercentage() {
		return femalesPercentage;
	}

	public int getEldestAffected() {
		return eldestAffected;
	}

	public int getYoungestAffected() {
		return youngestAffected;
	}

	public int getPatientsDataCount() {
		return patientsDataCount;
	}
}
