package com.thecoducer.coronavirustracker.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.thecoducer.coronavirustracker.models.IndiaDistrictsStats;

@Service
public class IndiaStateDistrictDataService {

	private List<IndiaDistrictsStats> Maharashtra;
	private List<IndiaDistrictsStats> TamilNadu;
	private List<IndiaDistrictsStats> Delhi;
	private List<IndiaDistrictsStats> Telangana;
	private List<IndiaDistrictsStats> Rajasthan;
	private List<IndiaDistrictsStats> UttarPradesh;
	private List<IndiaDistrictsStats> AndhraPradesh;
	private List<IndiaDistrictsStats> Kerala;
	private List<IndiaDistrictsStats> MadhyaPradesh;
	private List<IndiaDistrictsStats> Gujarat;
	private List<IndiaDistrictsStats> Karnataka;
	private List<IndiaDistrictsStats> Haryana;
	private List<IndiaDistrictsStats> JammuandKashmir;
	private List<IndiaDistrictsStats> Punjab;
	private List<IndiaDistrictsStats> WestBengal;
	private List<IndiaDistrictsStats> Odisha;
	private List<IndiaDistrictsStats> Bihar;
	private List<IndiaDistrictsStats> Uttarakhand;
	private List<IndiaDistrictsStats> Assam;
	private List<IndiaDistrictsStats> HimachalPradesh;
	private List<IndiaDistrictsStats> Chandigarh;
	private List<IndiaDistrictsStats> Ladakh;
	private List<IndiaDistrictsStats> AndamanandNicobarIslands;
	private List<IndiaDistrictsStats> Chhattisgarh;
	private List<IndiaDistrictsStats> Goa;
	private List<IndiaDistrictsStats> Puducherry;
	private List<IndiaDistrictsStats> Jharkhand;
	private List<IndiaDistrictsStats> Manipur;
	private List<IndiaDistrictsStats> Mizoram;
	private List<IndiaDistrictsStats> ArunachalPradesh;
	private List<IndiaDistrictsStats> DadraandNagarHaveli;
	private List<IndiaDistrictsStats> Tripura;
	private List<IndiaDistrictsStats> DamanandDiu;
	private List<IndiaDistrictsStats> Lakshadweep;
	private List<IndiaDistrictsStats> Meghalaya;
	private List<IndiaDistrictsStats> Nagaland;
	private List<IndiaDistrictsStats> Sikkim;

	@PostConstruct
	public void fetchDistrictsData() throws MalformedURLException, ParseException, IOException {

		List<IndiaDistrictsStats> Maharashtra;
		List<IndiaDistrictsStats> TamilNadu;
		List<IndiaDistrictsStats> Delhi;
		List<IndiaDistrictsStats> Telangana;
		List<IndiaDistrictsStats> Rajasthan;
		List<IndiaDistrictsStats> UttarPradesh;
		List<IndiaDistrictsStats> AndhraPradesh;
		List<IndiaDistrictsStats> Kerala;
		List<IndiaDistrictsStats> MadhyaPradesh;
		List<IndiaDistrictsStats> Gujarat;
		List<IndiaDistrictsStats> Karnataka;
		List<IndiaDistrictsStats> Haryana;
		List<IndiaDistrictsStats> JammuandKashmir;
		List<IndiaDistrictsStats> Punjab;
		List<IndiaDistrictsStats> WestBengal;
		List<IndiaDistrictsStats> Odisha;
		List<IndiaDistrictsStats> Bihar;
		List<IndiaDistrictsStats> Uttarakhand;
		List<IndiaDistrictsStats> Assam;
		List<IndiaDistrictsStats> HimachalPradesh;
		List<IndiaDistrictsStats> Chandigarh;
		List<IndiaDistrictsStats> Ladakh;
		List<IndiaDistrictsStats> AndamanandNicobarIslands;
		List<IndiaDistrictsStats> Chhattisgarh;
		List<IndiaDistrictsStats> Goa;
		List<IndiaDistrictsStats> Puducherry;
		List<IndiaDistrictsStats> Jharkhand;
		List<IndiaDistrictsStats> Manipur;
		List<IndiaDistrictsStats> Mizoram;
		List<IndiaDistrictsStats> ArunachalPradesh;
		List<IndiaDistrictsStats> DadraandNagarHaveli;
		List<IndiaDistrictsStats> Tripura;
		List<IndiaDistrictsStats> DamanandDiu;
		List<IndiaDistrictsStats> Lakshadweep;
		List<IndiaDistrictsStats> Meghalaya;
		List<IndiaDistrictsStats> Nagaland;
		List<IndiaDistrictsStats> Sikkim;
		
		
		JSONArray jarr = (JSONArray) new JSONParser().parse(IOUtils.toString(
				new URL("https://api.covid19india.org/v2/state_district_wise.json").openStream()));
		
		//System.out.println(jo.keySet());
		
		for(Object s : jarr) {
			
		}
	}

}
