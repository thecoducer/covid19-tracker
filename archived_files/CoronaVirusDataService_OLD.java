package com.thecoducer.coronavirustracker.services;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.thecoducer.coronavirustracker.models.AllStats;

@Service
public class CoronaVirusDataService {

    private static String  VIRUS_DATA_CSV = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    
//    private static String RECOVERED_CASES = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_recovered_global.csv";
//    
//    private static String DEATHS = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_global.csv";

    private List<AllStats> allStats = new ArrayList<>();
    
    //when the instance of this class is created, this method is called
    //since we have annotated it with PostConstruct
    //Scheduled annotation takes cron expression and call the method again at a scheduled time
    //so that records are updated on our app
    @PostConstruct
    @Scheduled(cron = "* */5 * * * *")
    public void fetchVirusData() throws IOException, InterruptedException{
    	
    	List<AllStats> newStats = new ArrayList<>();
    	
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
        		.uri(URI.create(VIRUS_DATA_CSV))
        		.build();
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        //System.out.println(httpResponse.body());
        
        
        StringReader csvBodyReader = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
        
        for (CSVRecord record : records) {
            AllStats allStat = new AllStats();
            String stateName = record.get("Province/State");
            if(stateName.equals("")) {
            	allStat.setState("Unknown");
            }else {
            	allStat.setState(stateName);
            }
            
            allStat.setCountry(record.get("Country/Region"));
            int latestCases = Integer.parseInt(record.get(record.size() - 1));
            int prevDayCases = Integer.parseInt(record.get(record.size() - 2));
            allStat.setLatestTotalCases(latestCases);
            allStat.setDiffFromPrevDay(latestCases - prevDayCases);
            //System.out.println(allStat);
            newStats.add(allStat);
        }
        
        this.allStats = newStats;
    }

	public List<AllStats> getAllStats() {
		return allStats;
	}

}

