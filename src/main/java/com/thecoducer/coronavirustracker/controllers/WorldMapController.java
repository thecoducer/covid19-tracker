package com.thecoducer.coronavirustracker.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thecoducer.coronavirustracker.services.WorldStatsDataService;

@RestController
public class WorldMapController {
	
	@Autowired
	WorldStatsDataService worldStatsDataService;
	
	@GetMapping("/getworldmapdata")
	public ResponseEntity<Map<String, Integer>> getWorldMapData(){
		
		Map<String, Integer> mapData = worldStatsDataService.getWorldDataForMap();

		return new ResponseEntity<>(mapData, HttpStatus.OK);
	}
}
