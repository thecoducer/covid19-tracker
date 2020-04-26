package com.thecoducer.coronavirustracker.models;

public class WorldStats {
	
	private String country;
	private CountryInfo countryInfo;
	private String cases;
	private String todayCases;
	private String deaths;
	private String todayDeaths;
	private String recovered;
	private String active;
	private String tests;
	private String testsPerOneMillion;
	
	private String recoveredRate;
	private String deathRate;
	private String casePercentage;	
	
	
	public String getRecoveredRate() {
		return recoveredRate;
	}
	public void setRecoveredRate(String recoveredRate) {
		this.recoveredRate = recoveredRate;
	}
	public String getDeathRate() {
		return deathRate;
	}
	public void setDeathRate(String deathRate) {
		this.deathRate = deathRate;
	}
	public String getCasePercentage() {
		return casePercentage;
	}
	public void setCasePercentage(String casePercentage) {
		this.casePercentage = casePercentage;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public CountryInfo getCountryInfo() {
		return countryInfo;
	}
	public void setCountryInfo(CountryInfo countryInfo) {
		this.countryInfo = countryInfo;
	}
	public String getCases() {
		return cases;
	}
	public void setCases(String cases) {
		this.cases = cases;
	}
	public String getTodayCases() {
		return todayCases;
	}
	public void setTodayCases(String todayCases) {
		this.todayCases = todayCases;
	}
	public String getDeaths() {
		return deaths;
	}
	public void setDeaths(String deaths) {
		this.deaths = deaths;
	}
	public String getTodayDeaths() {
		return todayDeaths;
	}
	public void setTodayDeaths(String todayDeaths) {
		this.todayDeaths = todayDeaths;
	}
	public String getRecovered() {
		return recovered;
	}
	public void setRecovered(String recovered) {
		this.recovered = recovered;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getTests() {
		return tests;
	}
	public void setTests(String tests) {
		this.tests = tests;
	}
	public String getTestsPerOneMillion() {
		return testsPerOneMillion;
	}
	public void setTestsPerOneMillion(String testsPerOneMillion) {
		this.testsPerOneMillion = testsPerOneMillion;
	}
	@Override
	public String toString() {
		return "WorldStats [country=" + country + ", countryInfo=" + countryInfo + ", cases=" + cases + ", todayCases="
				+ todayCases + ", deaths=" + deaths + ", todayDeaths=" + todayDeaths + ", recovered=" + recovered
				+ ", active=" + active + "]";
	}
	
}