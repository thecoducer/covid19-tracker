package com.thecoducer.coronavirustracker.models;

public class IndiaZones {
	private String district;
	private String lastupdated;
	private String state;
	private String zone;
	
	
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getLastupdated() {
		return lastupdated;
	}
	public void setLastupdated(String lastupdated) {
		this.lastupdated = lastupdated;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	@Override
	public String toString() {
		return "IndiaZones [district=" + district + ", lastupdated=" + lastupdated + ", state=" + state + ", zone="
				+ zone + "]";
	}
}
