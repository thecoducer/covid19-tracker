package com.thecoducer.coronavirustracker.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.thecoducer.coronavirustracker.models.IndiaZones;

@Service
public class IndiaZonesDataService {
	
	List<IndiaZones> IndiaZonesList = new ArrayList<>();
	
	@PostConstruct
	@Scheduled(cron = "0 0/5 * * * *")
	public void fetchZoneInfo() throws MalformedURLException, ParseException, IOException {
		JSONObject zd = (JSONObject) new JSONParser().parse(IOUtils.toString(new URL("https://api.covid19india.org/zones.json").openStream()));
		
		JSONArray zones = (JSONArray) zd.get("zones");
		
		List<IndiaZones> newIndiaZonesList = new ArrayList<>();
		
		for(Object z : zones) {
			JSONObject z_obj = (JSONObject) z;
			
			IndiaZones obj = new IndiaZones();
			
			String zone = (String) z_obj.get("zone");
			
			obj.setDistrict((String) z_obj.get("district"));
			obj.setLastupdated((String) z_obj.get("lastupdated"));
			obj.setState((String) z_obj.get("state"));
			obj.setZone(capitalize(zone));
			
			newIndiaZonesList.add(obj);
		}
		
		this.IndiaZonesList = newIndiaZonesList;
	}
	
	public static String capitalize(String str) {
	    if(str == null || str.isEmpty()) {
	        return str;
	    }

	    return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
	
	public List<IndiaZones> getIndiaZonesList(){
		return IndiaZonesList;
	}

}
