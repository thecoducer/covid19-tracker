package com.thecoducer.coronavirustracker.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.annotation.PostConstruct;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.thecoducer.coronavirustracker.models.TestedStats;

@Service
public class IndiaTestedDataService {
	
	private TestedStats testStats;
	
	@PostConstruct
	public void fetchTestedData() throws MalformedURLException, ParseException, IOException {
		
		JSONObject jo = (JSONObject) new JSONParser().parse(IOUtils.toString(
				new URL("https://api.covid19india.org/data.json").openStream()));
		
		JSONArray tested = (JSONArray) jo.get("tested");
		
		TestedStats newTestStats = new TestedStats();
		
		int flag1 = 0, flag2 = 0;
		
		for(int i = tested.size()-1; i>=0; i--) {
			
			JSONObject tested_obj = (JSONObject) tested.get(i);
			
			String totIndvTestd = (String) tested_obj.get("totalindividualstested");
					
			if(totIndvTestd.equals("") == false && flag1 == 0) {
				
				String so = (String) tested_obj.get("source");
				String ts = (String) tested_obj.get("updatetimestamp");
				
				newTestStats.setTotalIndividualTested(totIndvTestd.replaceAll("\\p{Punct}",""));
				newTestStats.setSourceIndividualTested(so);
				newTestStats.setTimestampIndividual(ts);
				
				flag1 = 1;
			}
			
			String totSampTestd = (String) tested_obj.get("totalsamplestested");
						
			if(totSampTestd.equals("") == false && flag2 == 0) {
				
				String sa = (String) tested_obj.get("source");
				String tsa = (String) tested_obj.get("updatetimestamp");
				
				newTestStats.setTotalSamplesTested(totSampTestd.replaceAll("\\p{Punct}",""));
				newTestStats.setSourceSamplesTested(sa);
				newTestStats.setTimestampSamples(tsa);
				
				flag2 = 1;
			}
			
			if(flag1 == 1 && flag2 == 1) {
				break;
			}
			
		}
		
		this.testStats = newTestStats;
	}
	
	public TestedStats getTestStats() {
		return testStats;
	}
}
