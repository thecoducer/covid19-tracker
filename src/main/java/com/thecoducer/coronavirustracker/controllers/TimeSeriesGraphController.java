package com.thecoducer.coronavirustracker.controllers;

import java.util.Map;
import java.util.TreeMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimeSeriesGraphController {
	
	@GetMapping("/getseries")
	public ResponseEntity<Map<String, Integer>> getTimeSeriesGraph(){
		Map<String, Integer> graphData = new TreeMap<>();
		graphData.put("30 Jan", 100);
		graphData.put("04 Feb", 120);
		graphData.put("12 Feb", 193);
		graphData.put("26 Feb", 225);
		graphData.put("10 Mar", 300);
		
		return new ResponseEntity<>(graphData, HttpStatus.OK);
		
	}
}
