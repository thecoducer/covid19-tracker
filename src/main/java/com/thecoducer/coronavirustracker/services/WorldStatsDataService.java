package com.thecoducer.coronavirustracker.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.TreeMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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
	@Scheduled(cron = "0 0/15 * * * *")
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
        		worldStat.setRecoveredRate(setHyphenIfZero(recoveredRate));
        	}catch (Exception e) {
				worldStat.setRecoveredRate("-"); 
			}
        	
        	
        	try {
        		double d = (Double.parseDouble(worldStat.getDeaths()) / Double.parseDouble(worldStat.getCases())) * 100;
        		String deathRate = df.format(d);
        		worldStat.setDeathRate(setHyphenIfZero(deathRate));
        	}catch (Exception e) {
				worldStat.setDeathRate("-"); 
			}
        	
        	try {
        		double cp = (Double.parseDouble(worldStat.getCases()) / 
        				Double.parseDouble(allStatsDataService.getAllStats().getCases())) * 100;
        		String casePercentage = df.format(cp);
        		worldStat.setCasePercentage(setHyphenIfZero(casePercentage));
        	}catch (Exception e) {
				worldStat.setCasePercentage("-"); 
			}
        	
        	totalnewcases += Long.parseLong(worldStat.getTodayCases());
        	
        	totalnewdeathscount += Long.parseLong(worldStat.getTodayDeaths());
        	
        	
        	worldStat.setCases(setHyphenOrNumberFormat(worldStat.getCases()));
        	worldStat.setTodayCases(numberFormat.format(Long.parseLong(worldStat.getTodayCases())));
        	worldStat.setActive(setHyphenOrNumberFormat(worldStat.getActive()));
        	worldStat.setDeaths(setHyphenOrNumberFormat(worldStat.getDeaths()));
        	worldStat.setTodayDeaths(numberFormat.format(Long.parseLong(worldStat.getTodayDeaths())));
        	worldStat.setRecovered(setHyphenOrNumberFormat(worldStat.getRecovered()));
        	
        }
        
        //newWorldStats.remove(0);
        
        this.worldStats = newWorldStats;
        this.totalNewCasesCount = totalnewcases;
        this.totalNewDeathsCount = totalnewdeathscount;
	}
	
	public static String setHyphenOrNumberFormat(String str) {
		if(str.equals("0") || str.equals("")) {
			return "-";
		}else {
			NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
			
			return numberFormat.format(Long.parseLong(str));
		}
	}
	
	public static String setHyphenIfZero(String str) {
		if(str.equals("0") || str.equals("")) {
			return "-";
		}else {
			return str+"%";
		}
	}
	
	public List<WorldStats> getWorldStats() {
		return worldStats;
	}
	
	public Map<String, Integer> getWorldDataForMap(){
		
		Map<String, Integer> mapData = new TreeMap<>();
		
		for(WorldStats itr : this.worldStats) {
			try {
				mapData.put(itr.getCountryInfo().getIso3(), Integer.parseInt(itr.getCases().replaceAll("\\p{Punct}","")));
			}catch (NullPointerException e) {
				System.out.println("");
			}
		}
		
		return mapData;
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