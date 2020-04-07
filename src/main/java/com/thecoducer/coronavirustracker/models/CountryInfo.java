package com.thecoducer.coronavirustracker.models;

public class CountryInfo {

	private String flag; 
	private String iso3;
	
	

	public String getIso3() {
		return iso3;
	}

	public void setIso3(String iso3) {
		this.iso3 = iso3;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "CountryInfo [flag=" + flag + "]";
	}
	
}