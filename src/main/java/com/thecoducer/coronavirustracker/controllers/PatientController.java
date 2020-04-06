package com.thecoducer.coronavirustracker.controllers;

import java.util.Map;
import java.util.SortedMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thecoducer.coronavirustracker.models.PatientStats;
import com.thecoducer.coronavirustracker.services.IndiaPatientDataService;

@RestController
public class PatientController {
	
	@Autowired
	IndiaPatientDataService indiaPatientDataService;
	
	@GetMapping("/getpatientsdata")
	public ResponseEntity<SortedMap<Integer, PatientStats>> getPatientDataGraph(){
		
		SortedMap<Integer, PatientStats> graphData = indiaPatientDataService.getPatientData();

		return new ResponseEntity<>(graphData, HttpStatus.OK);
	}

}
