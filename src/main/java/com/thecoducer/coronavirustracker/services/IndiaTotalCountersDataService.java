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
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class IndiaTotalCountersDataService {

	private String totalNewCases;
	private String totalCases;
	private String totalActiveCases;
	private String totalRecoveredCases;
	private String totalNewRecoveredCases;
	private String totalNewDeaths;
	private String totalDeaths;

	@PostConstruct
	@Scheduled(cron = "0 0/5 * * * *")
	public void setTotalCounters() throws MalformedURLException, ParseException, IOException {

		JSONObject jo = (JSONObject) new JSONParser()
				.parse(IOUtils.toString(new URL("https://api.covid19india.org/data.json").openStream()));

		//JSONArray key_values = (JSONArray) jo.get("key_values");

		JSONArray statewise = (JSONArray) jo.get("statewise");

		//JSONObject kv_obj = (JSONObject) key_values.get(0);

		JSONObject first_s_obj = (JSONObject) statewise.get(0);

		this.totalCases = (String) first_s_obj.get("confirmed");
		this.totalNewCases = (String) first_s_obj.get("deltaconfirmed");
		this.totalActiveCases = (String) first_s_obj.get("active");
		this.totalRecoveredCases = (String) first_s_obj.get("recovered");
		this.totalNewRecoveredCases = (String) first_s_obj.get("deltarecovered");
		this.totalNewDeaths = (String) first_s_obj.get("deltadeaths");
		this.totalDeaths = (String) first_s_obj.get("deaths");
	}

	public String getTotalNewCases() {
		return totalNewCases;
	}

	public String getTotalActiveCases() {
		return totalActiveCases;
	}

	public String getTotalRecoveredCases() {
		return totalRecoveredCases;
	}

	public String getTotalNewDeaths() {
		return totalNewDeaths;
	}

	public String getTotalDeaths() {
		return totalDeaths;
	}

	public String getTotalNewRecoveredCases() {
		return totalNewRecoveredCases;
	}

	public String getTotalCases() {
		return totalCases;
	}
}