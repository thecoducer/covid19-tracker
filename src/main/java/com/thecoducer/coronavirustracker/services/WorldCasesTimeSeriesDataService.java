package com.thecoducer.coronavirustracker.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.thecoducer.coronavirustracker.models.WorldCasesSeries;

@Service
public class WorldCasesTimeSeriesDataService {
	
	private Map<String, List<WorldCasesSeries>> worldSeriesData = new TreeMap<>();

	@PostConstruct
	public void fetchWorldHistoricalData() throws MalformedURLException, ParseException, IOException {
		
		JSONObject USA = getJSONObject("https://corona.lmao.ninja/v2/historical/USA?lastdays=120");
		JSONObject Spain = getJSONObject("https://corona.lmao.ninja/v2/historical/Spain?lastdays=120");
		JSONObject Italy = getJSONObject("https://corona.lmao.ninja/v2/historical/Italy?lastdays=120");
		JSONObject Germany = getJSONObject("https://corona.lmao.ninja/v2/historical/Germany?lastdays=120");
		JSONObject France = getJSONObject("https://corona.lmao.ninja/v2/historical/France?lastdays=120");
		JSONObject China = getJSONObject("https://corona.lmao.ninja/v2/historical/China?lastdays=120");
		JSONObject Iran = getJSONObject("https://corona.lmao.ninja/v2/historical/Iran?lastdays=120");
		JSONObject UK = getJSONObject("https://corona.lmao.ninja/v2/historical/UK?lastdays=120");
		JSONObject Turkey = getJSONObject("https://corona.lmao.ninja/v2/historical/Turkey?lastdays=120");
		JSONObject Belgium = getJSONObject("https://corona.lmao.ninja/v2/historical/Belgium?lastdays=120");
		
		Map<String, List<WorldCasesSeries>> newWorldSeriesData = new TreeMap<>();
		
		JSONObject USA_tl = (JSONObject) USA.get("timeline");
		
		JSONObject USA_cases = (JSONObject) USA_tl.get("cases");
		
//		for(Object )
		
		
	}
	
	public static JSONObject getJSONObject(String countryURL) throws MalformedURLException, ParseException, IOException {
		
		JSONObject jo = (JSONObject) new JSONParser().parse(IOUtils.toString(
				new URL(countryURL).openStream()));
		
		return jo;
	}
}
