package com.thecoducer.coronavirustracker.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thecoducer.coronavirustracker.models.AllStats;

@Service 
public class AllStatsDataService {
	
	private AllStats allStats;
	
	@PostConstruct
	public void fetchAllStats() throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		
		ObjectMapper objectMapper = new ObjectMapper();

        //read json file and convert to customer object
        AllStats newAllStats = objectMapper.readValue(new URL("https://corona.lmao.ninja/all"), AllStats.class);

        this.allStats = newAllStats;
        
	}
	
	public AllStats getAllStats() {
		return allStats;
	}
}