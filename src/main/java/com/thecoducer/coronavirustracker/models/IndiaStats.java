package com.thecoducer.coronavirustracker.models;

public class IndiaStats {
	
	private String active;				//statewise -> active
	private String confirmed;			//statewise -> confirmed
	private String deaths;				//statewise -> deaths
	private String recovered;			//statewise -> recovered
	private String state;				//statewise -> state
	
	//private String delta_Active;		//statewise -> delta -> active
	private String delta_Confirmed;		//statewise -> delta -> confirmed
	private String delta_Deaths;		//statewise -> delta -> deaths
	private String delta_Recovered;		//statewise -> delta -> recovered 
	
	private String recoveredRate;
	private String deathRate;
	private String casePercentage;		
	
	
	
	public String getRecoveredRate() {
		return recoveredRate;
	}
	public void setRecoveredRate(String recoveredRate) {
		this.recoveredRate = recoveredRate;
	}
	public String getDeathRate() {
		return deathRate;
	}
	public void setDeathRate(String deathRate) {
		this.deathRate = deathRate;
	}
	public String getCasePercentage() {
		return casePercentage;
	}
	public void setCasePercentage(String casePercentage) {
		this.casePercentage = casePercentage;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getConfirmed() {
		return confirmed;
	}
	public void setConfirmed(String confirmed) {
		this.confirmed = confirmed;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
//	public String getDelta_Active() {
//		return delta_Active;
//	}
//	public void setDelta_Active(String delta_Active) {
//		this.delta_Active = delta_Active;
//	}
	public String getDelta_Confirmed() {
		return delta_Confirmed;
	}
	public void setDelta_Confirmed(String delta_Confirmed) {
		this.delta_Confirmed = delta_Confirmed;
	}
	public String getDelta_Deaths() {
		return delta_Deaths;
	}
	public void setDelta_Deaths(String delta_Deaths) {
		this.delta_Deaths = delta_Deaths;
	}
	public String getDelta_Recovered() {
		return delta_Recovered;
	}
	public void setDelta_Recovered(String delta_Recovered) {
		this.delta_Recovered = delta_Recovered;
	}
	
}