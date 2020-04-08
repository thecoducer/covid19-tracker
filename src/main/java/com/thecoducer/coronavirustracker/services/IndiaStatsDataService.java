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
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.thecoducer.coronavirustracker.models.IndiaStats;

@Service
public class IndiaStatsDataService {
	
	@Autowired
	IndiaTotalCountersDataService indiaTotalCounterDataService;
	
	private List<IndiaStats> indiaStats;

	@PostConstruct
	@Scheduled(cron = "0 0/5 * * * *")
	public void fetchIndiaStats() throws MalformedURLException, ParseException, IOException {
		
		List<IndiaStats> newIndiaStats = new ArrayList<>();
		
		JSONObject jo = (JSONObject) new JSONParser().parse(IOUtils.toString(
				new URL("https://api.covid19india.org/data.json").openStream()));
		
		
		JSONArray statewise = (JSONArray) jo.get("statewise");
		
		for(Object s : statewise) {
			
			IndiaStats stats = new IndiaStats();
			
			JSONObject s_obj = (JSONObject) s;
			
			String active = (String) s_obj.get("active");
			String confirmed = (String) s_obj.get("confirmed");
			String deaths = (String) s_obj.get("deaths");
			String recovered = (String) s_obj.get("recovered");
			
			stats.setActive(setHyphenIfZero(active));
			stats.setConfirmed(setHyphenIfZero(confirmed));
			stats.setDeaths(setHyphenIfZero(deaths));
			stats.setRecovered(setHyphenIfZero(recovered));
			stats.setState((String) s_obj.get("state"));
			
			stats.setDelta_Confirmed((String) s_obj.get("deltaconfirmed"));
			stats.setDelta_Deaths((String) s_obj.get("deltadeaths"));
			stats.setDelta_Recovered((String) s_obj.get("deltarecovered"));
			
			DecimalFormat df = new DecimalFormat("#.##");
			
			if(stats.getConfirmed().equals("0")) {
				stats.setDeathRate("-");
				stats.setRecoveredRate("-");
				stats.setCasePercentage("-");
			}else {
				try {
	        		double rr = (Double.parseDouble(stats.getRecovered()) / Double.parseDouble(stats.getConfirmed())) * 100;
	        		String recoveredRate = df.format(rr);
	        		stats.setRecoveredRate(recoveredRate+"%");
	        	}catch (Exception e) {
					stats.setRecoveredRate("-"); 
				}
				
				try {
	        		double d = (Double.parseDouble(stats.getDeaths()) / Double.parseDouble(stats.getConfirmed())) * 100;
	        		String deathRate = df.format(d);
	        		stats.setDeathRate(deathRate+"%");
	        	}catch (Exception e) {
					stats.setDeathRate("-"); 
				}
				
				
	        	try {
	        		double cp = (Double.parseDouble(stats.getConfirmed()) / 
	        				Double.parseDouble(indiaTotalCounterDataService.getTotalCases())) * 100;
	        		String casePercentage = df.format(cp);
	        		stats.setCasePercentage(casePercentage+"%");
	        	}catch (Exception e) {
					stats.setCasePercentage("-"); 
				}
			}
			
			
			newIndiaStats.add(stats);
			
		}
		
		//to get rid of the total row
		//it was the first element which got extracted
		newIndiaStats.remove(0);
		
		this.indiaStats = newIndiaStats;
	
	}
	
	public static String setHyphenIfZero(String str) {
		if(str.equals("0") || str.equals("")) {
			return "-";
		}else {
			return str;
		}
	}
	

	public List<IndiaStats> getIndiaStats(){
		return indiaStats;
	}

}