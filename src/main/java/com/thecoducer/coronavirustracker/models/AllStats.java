package com.thecoducer.coronavirustracker.models;

public class AllStats {
	private String cases;
	private String deaths;
	private String recovered;
	private String updated;
	private String active;
	private String affectedCountries;
	
	//later added on occurence of error
//	private String todayCases;
//	private String todayDeaths;
//	private String casesPerOneMillion;
//	private String deathsPerOneMillion;
//	private
	
	
	
//	public String getTodayCases() {
//		return todayCases;
//	}
//	public void setTodayCases(String todayCases) {
//		this.todayCases = todayCases;
//	}
	public String getCases() {
		return cases;
	}
	public void setCases(String cases) {
		this.cases = cases;
	}
	public String getDeaths() {
		return deaths;
	}
	public void setDeaths(String deaths) {
		this.deaths = deaths;
	}
	public String getRecovered() {
		return recovered;
	}
	public void setRecovered(String recovered) {
		this.recovered = recovered;
	}
	public String getUpdated() {
		return updated;
	}
	public void setUpdated(String updated) {
		this.updated = updated;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getAffectedCountries() {
		return affectedCountries;
	}
	public void setAffectedCountries(String affectedCountries) {
		this.affectedCountries = affectedCountries;
	}
	@Override
	public String toString() {
		return "AllStats [cases=" + cases + ", deaths=" + deaths + ", recovered=" + recovered + ", updated=" + updated
				+ ", active=" + active + ", affectedCountries=" + affectedCountries + "]";
	}
}