package com.thecoducer.coronavirustracker.models;

public class IndiaRecentUpdates {
	private String update;
	private String timestamp;
	
	
	public String getUpdate() {
		return update;
	}
	public void setUpdate(String update) {
		this.update = update;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	@Override
	public String toString() {
		return "IndiaRecentUpdates [update=" + update + ", timestamp=" + timestamp + "]";
	}
}
