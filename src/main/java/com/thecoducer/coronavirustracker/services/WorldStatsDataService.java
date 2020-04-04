package com.thecoducer.coronavirustracker.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thecoducer.coronavirustracker.models.WorldStats;

@Service
public class WorldStatsDataService {
	
	@Autowired
    AllStatsDataService allStatsDataService;
	
	private List<WorldStats> worldStats;
	
	private long totalNewCasesCount;
	private long totalNewDeathsCount;
	
	@PostConstruct
	public void fetchWorldStats() throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		
		NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        //read json file and convert to worldstats object
        List<WorldStats> newWorldStats = objectMapper.readValue(new URL("https://corona.lmao.ninja/countries?sort=cases"), 
        		new TypeReference<List<WorldStats>>(){});
        
        long totalnewcases = 0;
        long totalnewdeathscount = 0;
        
        DecimalFormat df = new DecimalFormat("#.##");
        
        for(WorldStats worldStat:newWorldStats) {
        	
        	try {
        		double rr = (Double.parseDouble(worldStat.getRecovered()) / Double.parseDouble(worldStat.getCases())) * 100;
        		String recoveredRate = df.format(rr);
        		worldStat.setRecoveredRate(recoveredRate);
        	}catch (Exception e) {
				worldStat.setRecoveredRate("0"); 
			}
        	
        	
        	try {
        		double d = (Double.parseDouble(worldStat.getDeaths()) / Double.parseDouble(worldStat.getCases())) * 100;
        		String deathRate = df.format(d);
        		worldStat.setDeathRate(deathRate);
        	}catch (Exception e) {
				worldStat.setDeathRate("0"); 
			}
        	
        	try {
        		double cp = (Double.parseDouble(worldStat.getCases()) / 
        				Double.parseDouble(allStatsDataService.getAllStats().getCases())) * 100;
        		String casePercentage = df.format(cp);
        		worldStat.setCasePercentage(casePercentage);
        	}catch (Exception e) {
				worldStat.setCasePercentage("0"); 
			}
        	
        	totalnewcases += Long.parseLong(worldStat.getTodayCases());
        	
        	totalnewdeathscount += Long.parseLong(worldStat.getTodayDeaths());
        	
        	
        	worldStat.setCases(numberFormat.format(Long.parseLong(worldStat.getCases())));
        	worldStat.setTodayCases(numberFormat.format(Long.parseLong(worldStat.getTodayCases())));
        	worldStat.setActive(numberFormat.format(Long.parseLong(worldStat.getActive())));
        	worldStat.setDeaths(numberFormat.format(Long.parseLong(worldStat.getDeaths())));
        	worldStat.setTodayDeaths(numberFormat.format(Long.parseLong(worldStat.getTodayDeaths())));
        	worldStat.setRecovered(numberFormat.format(Long.parseLong(worldStat.getRecovered())));
        	
        }
        
        //newWorldStats.remove(0);
        
        this.worldStats = newWorldStats;
        this.totalNewCasesCount = totalnewcases;
        this.totalNewDeathsCount = totalnewdeathscount;
	}
	
	public List<WorldStats> getWorldStats() {
		return worldStats;
	}
	
	public String getTotalNewCasesCount() {
		NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
		return numberFormat.format(totalNewCasesCount);
	}
	
	public String getTotalNewDeathsCount() {
		NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
		return numberFormat.format(totalNewDeathsCount);
	}
}