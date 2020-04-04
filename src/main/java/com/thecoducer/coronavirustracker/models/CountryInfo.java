package com.thecoducer.coronavirustracker.models;

public class CountryInfo {

	private String flag; 

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