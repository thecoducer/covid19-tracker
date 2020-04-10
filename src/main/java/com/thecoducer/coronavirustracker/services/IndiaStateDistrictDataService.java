package com.thecoducer.coronavirustracker.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.thecoducer.coronavirustracker.models.IndiaStateDistricts;

@Service
public class IndiaStateDistrictDataService {
	
	private Map<String, List<IndiaStateDistricts>> stateMap = new TreeMap<>();

	@PostConstruct
	@Scheduled(cron = "0 0/5 * * * *")
	public void fetchDistrictsData() throws MalformedURLException, ParseException, IOException {

		JSONArray jarr = (JSONArray) new JSONParser().parse(IOUtils.toString(
				new URL("https://api.covid19india.org/v2/state_district_wise.json").openStream()));
		
		Map<String, List<IndiaStateDistricts>> newStateMap = new TreeMap<>();
		
		for(Object s : jarr) {
			
			JSONObject s_obj = (JSONObject) s;
			
			String stateName = String.valueOf(s_obj.get("state")).replaceAll("\\s+", "");
			
			JSONArray districtData = (JSONArray) s_obj.get("districtData");
			
			List<IndiaStateDistricts> districtList = new ArrayList<>();
			
			for(Object d : districtData) {
				
				JSONObject d_obj = (JSONObject) d;
				
				IndiaStateDistricts sd = new IndiaStateDistricts();
				
				sd.setDistrictName((String) d_obj.get("district"));
				sd.setConfirmed(String.valueOf(d_obj.get("confirmed")));
				
				JSONObject delta_obj = (JSONObject) d_obj.get("delta");
				
				sd.setDeltaConfirmed(String.valueOf(delta_obj.get("confirmed")));
				
				districtList.add(sd);
			}
			
			Collections.sort(districtList, IndiaStateDistricts.iSDComparator);
			
			newStateMap.put(stateName, districtList);
		}
		
		this.stateMap = newStateMap;
		
	}
	
	
	public Map<String, List<IndiaStateDistricts>> getStateDistrictData(){
		return stateMap;
	}

}
