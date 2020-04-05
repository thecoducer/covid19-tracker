package com.thecoducer.coronavirustracker.models;

public class IndiaCasesTimeSeries {
	
	private String dailyConfirmed;
	private String dailyDeceased;
	private String dailyRecovered;
	private String date;
	
	
	public String getDailyConfirmed() {
		return dailyConfirmed;
	}
	public void setDailyConfirmed(String dailyConfirmed) {
		this.dailyConfirmed = dailyConfirmed;
	}
	public String getDailyDeceased() {
		return dailyDeceased;
	}
	public void setDailyDeceased(String dailyDeceased) {
		this.dailyDeceased = dailyDeceased;
	}
	public String getDailyRecovered() {
		return dailyRecovered;
	}
	public void setDailyRecovered(String dailyRecovered) {
		this.dailyRecovered = dailyRecovered;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
