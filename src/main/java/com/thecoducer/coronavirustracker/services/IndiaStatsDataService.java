package com.thecoducer.coronavirustracker.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thecoducer.coronavirustracker.models.IndiaStats;

@Service
public class IndiaStatsDataService {
	
	@Autowired
	IndiaTotalCountersDataService indiaTotalCounterDataService;
	
	private List<IndiaStats> indiaStats;

	@PostConstruct
	public void fetchIndiaStats() throws MalformedURLException, ParseException, IOException {
		
		List<IndiaStats> newIndiaStats = new ArrayList<>();
		
		JSONObject jo = (JSONObject) new JSONParser().parse(IOUtils.toString(
				new URL("https://api.covid19india.org/data.json").openStream()));
		
		
		JSONArray statewise = (JSONArray) jo.get("statewise");
		
		for(Object s : statewise) {
			
			IndiaStats stats = new IndiaStats();
			
			JSONObject s_obj = (JSONObject) s;
			
			stats.setActive((String) s_obj.get("active"));
			stats.setConfirmed((String) s_obj.get("confirmed"));
			stats.setDeaths((String) s_obj.get("deaths"));
			stats.setRecovered((String) s_obj.get("recovered"));
			stats.setState((String) s_obj.get("state"));
			
			//JSONObject delta = (JSONObject) s_obj.get("delta");
			
			//stats.setDelta_Active(String.valueOf((long) delta.get("active")));
			
//			stats.setDelta_Confirmed(String.valueOf((long) delta.get("confirmed")));
//			stats.setDelta_Deaths(String.valueOf((long) delta.get("deaths")));
//			stats.setDelta_Recovered(String.valueOf((long) delta.get("recovered")));
			
			stats.setDelta_Confirmed((String) s_obj.get("deltaconfirmed"));
			stats.setDelta_Deaths((String) s_obj.get("deltadeaths"));
			stats.setDelta_Recovered((String) s_obj.get("deltarecovered"));
			
			DecimalFormat df = new DecimalFormat("#.##");
			
			if(stats.getConfirmed().equals("0")) {
				stats.setDeathRate("0");
				stats.setRecoveredRate("0");
				stats.setCasePercentage("0");
			}else {
				try {
	        		double rr = (Double.parseDouble(stats.getRecovered()) / Double.parseDouble(stats.getConfirmed())) * 100;
	        		String recoveredRate = df.format(rr);
	        		stats.setRecoveredRate(recoveredRate);
	        	}catch (Exception e) {
					stats.setRecoveredRate("0"); 
				}
				
				try {
	        		double d = (Double.parseDouble(stats.getDeaths()) / Double.parseDouble(stats.getConfirmed())) * 100;
	        		String deathRate = df.format(d);
	        		stats.setDeathRate(deathRate);
	        	}catch (Exception e) {
					stats.setDeathRate("0"); 
				}
				
				
	        	try {
	        		double cp = (Double.parseDouble(stats.getConfirmed()) / 
	        				Double.parseDouble(indiaTotalCounterDataService.getTotalCases())) * 100;
	        		String casePercentage = df.format(cp);
	        		stats.setCasePercentage(casePercentage);
	        	}catch (Exception e) {
					stats.setCasePercentage("0"); 
				}
			}
			
			
			newIndiaStats.add(stats);
			
		}
		
		//to get rid of the total row
		//it was the first element which got extracted
		newIndiaStats.remove(0);
		
		this.indiaStats = newIndiaStats;
	
	}
	

	public List<IndiaStats> getIndiaStats(){
		return indiaStats;
	}

}