package com.thecoducer.coronavirustracker.controllers;

import java.util.SortedMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thecoducer.coronavirustracker.models.IndiaCasesTimeSeries;
import com.thecoducer.coronavirustracker.services.IndiaCasesTimeSeriesDataService;

@RestController
public class TimeSeriesGraphController {
	
	@Autowired
	IndiaCasesTimeSeriesDataService indiaCasesTimeSeriesDataService;
	
	@GetMapping("/getseries")
	public ResponseEntity<SortedMap<Integer, IndiaCasesTimeSeries>> getTimeSeriesGraph(){
		
		SortedMap<Integer, IndiaCasesTimeSeries> graphData = indiaCasesTimeSeriesDataService.getIndiaTimeSeriesData();

		return new ResponseEntity<>(graphData, HttpStatus.OK);
		
	}
}
