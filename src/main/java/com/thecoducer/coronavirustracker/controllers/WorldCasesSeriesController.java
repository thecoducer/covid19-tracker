package com.thecoducer.coronavirustracker.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thecoducer.coronavirustracker.models.WorldCasesSeries;
import com.thecoducer.coronavirustracker.services.WorldCasesTimeSeriesDataService;

@RestController
public class WorldCasesSeriesController {
	
//	@Autowired
//	WorldCasesTimeSeriesDataService worldCasesTimeSeriesDataService;
//	
//	@GetMapping("/getworldcaseseries")
//	public ResponseEntity<Map<String, List<WorldCasesSeries>>> getWorldCaseSeries(){
//		
//		Map<String, List<WorldCasesSeries>> worldCaseSeries = worldCasesTimeSeriesDataService.get;
//
//		return new ResponseEntity<>(worldCaseSeries, HttpStatus.OK);
//	}

}
