package com.thecoducer.coronavirustracker.models;

public class IndiaStateDistricts {
	private String districtName;
	private String confirmed;
	private String deltaConfirmed;
	
	
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
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
	@Override
	public String toString() {
		return "IndiaStateDistricts [districtName=" + districtName + ", confirmed=" + confirmed + ", deltaConfirmed="
				+ deltaConfirmed + "]";
	}
	
}
