package com.thecoducer.coronavirustracker.models;

public class IndiaDistrictsStats {
	private String district;
	private String confirmed;
	private String deltaConfirmed;
	
	
	
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getConfirmed() {
		return confirmed;
	}
	public void setConfirmed(String confirmed) {
		this.confirmed = confirmed;
	}
	public String getDeltaConfirmed() {
		return deltaConfirmed;
	}
	public void setDeltaConfirmed(String deltaConfirmed) {
		this.deltaConfirmed = deltaConfirmed;
	}
		
}
