package com.thecoducer.coronavirustracker.models;

import java.util.Comparator;

public class IndiaStateDistricts {
	private String districtName;
	private String confirmed;
	private String deltaConfirmed;
	
	// Comparator to sort by total confirmed cases
	public static Comparator<IndiaStateDistricts> iSDComparator = new Comparator<IndiaStateDistricts>() {
		public int compare(IndiaStateDistricts d1, IndiaStateDistricts d2) {
			long c1 = Long.parseLong(d1.getConfirmed());
			long c2 = Long.parseLong(d2.getConfirmed());
			
			if(c1 == c2) {
				return 0;
			}else if(c1 > c2) {
				return -1;
			}else {
				return 1;
			}
		}
	};
	
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
