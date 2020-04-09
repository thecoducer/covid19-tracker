package com.thecoducer.coronavirustracker.models;

public class WorldCasesSeries {
	private String date;
	private String totalcases;
	private String totaldeaths;
	private String totalrecovered;
	
	
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTotalcases() {
		return totalcases;
	}
	public void setTotalcases(String totalcases) {
		this.totalcases = totalcases;
	}
	public String getTotaldeaths() {
		return totaldeaths;
	}
	public void setTotaldeaths(String totaldeaths) {
		this.totaldeaths = totaldeaths;
	}
	public String getTotalrecovered() {
		return totalrecovered;
	}
	public void setTotalrecovered(String totalrecovered) {
		this.totalrecovered = totalrecovered;
	}
}
