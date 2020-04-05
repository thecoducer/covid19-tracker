package com.thecoducer.coronavirustracker.models;

public class TestedStats {
	
	private String sourceIndividualTested;
	private String sourceSamplesTested;
	
	private String timestampIndividual;
	private String timestampSamples;
	
	private String totalIndividualTested;
	private String totalSamplesTested;
	
	
	
	public String getSourceIndividualTested() {
		return sourceIndividualTested;
	}
	public void setSourceIndividualTested(String sourceIndividualTested) {
		this.sourceIndividualTested = sourceIndividualTested;
	}
	public String getSourceSamplesTested() {
		return sourceSamplesTested;
	}
	public void setSourceSamplesTested(String sourceSamplesTested) {
		this.sourceSamplesTested = sourceSamplesTested;
	}
	public String getTimestampIndividual() {
		return timestampIndividual;
	}
	public void setTimestampIndividual(String timestampIndividual) {
		this.timestampIndividual = timestampIndividual;
	}
	public String getTimestampSamples() {
		return timestampSamples;
	}
	public void setTimestampSamples(String timestampSamples) {
		this.timestampSamples = timestampSamples;
	}
	public String getTotalIndividualTested() {
		return totalIndividualTested;
	}
	public void setTotalIndividualTested(String totalIndividualTested) {
		this.totalIndividualTested = totalIndividualTested;
	}
	public String getTotalSamplesTested() {
		return totalSamplesTested;
	}
	public void setTotalSamplesTested(String totalSamplesTested) {
		this.totalSamplesTested = totalSamplesTested;
	}
	
}
