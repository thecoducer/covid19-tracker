package com.thecoducer.coronavirustracker.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.PostConstruct;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.thecoducer.coronavirustracker.models.IndiaCasesTimeSeries;

@Service
public class IndiaCasesTimeSeriesDataService {

	private SortedMap<Integer, IndiaCasesTimeSeries> graphData = new TreeMap<>();
	
	@PostConstruct
	@Scheduled(cron = "0 0/5 * * * *")
	public void fetchTimeSeriesData() throws MalformedURLException, ParseException, IOException {
		
		JSONObject jo = (JSONObject) new JSONParser().parse(IOUtils.toString(new URL("https://api.covid19india.org/data.json").openStream()));
		
		JSONArray timeSeries = (JSONArray) jo.get("cases_time_series");
		
		SortedMap<Integer, IndiaCasesTimeSeries> newGraphData = new TreeMap<>();
		
		int i = 0;
		
		for(Object t : timeSeries) {
			
			IndiaCasesTimeSeries day = new IndiaCasesTimeSeries();
			
			JSONObject t_obj = (JSONObject) t;
			
			day.setDailyConfirmed((String) t_obj.get("dailyconfirmed"));
			day.setDailyDeceased((String) t_obj.get("dailydeceased"));
			day.setDailyRecovered((String) t_obj.get("dailyrecovered"));
			day.setDate((String) t_obj.get("date"));
			day.setTotalConfirmed((String) t_obj.get("totalconfirmed"));
			day.setTotalDeceased((String) t_obj.get("totaldeceased"));
			day.setTotalRecovered((String) t_obj.get("totalrecovered"));
			
			newGraphData.put(i++, day);
		}
		
		this.graphData = newGraphData;
		
//		System.out.println("hhhhhhhhhhhhhhhhhh");
	}
	
	public SortedMap<Integer, IndiaCasesTimeSeries> getIndiaTimeSeriesData(){
		return graphData;
	}
}
