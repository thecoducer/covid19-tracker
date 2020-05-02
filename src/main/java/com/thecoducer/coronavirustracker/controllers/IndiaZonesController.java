package com.thecoducer.coronavirustracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.thecoducer.coronavirustracker.models.IndiaZones;
import com.thecoducer.coronavirustracker.services.IndiaZonesDataService;

@Controller
public class IndiaZonesController {
	
	@Autowired
	IndiaZonesDataService indiaZonesDataService;
	
	@GetMapping("/zones")
	public String ZonesInfo(Model model) {
		
		List<IndiaZones> indiaZonesList = indiaZonesDataService.getIndiaZonesList();
		
		model.addAttribute("IndiaZonesList", indiaZonesList);
		
		return "zones";
	}
}
