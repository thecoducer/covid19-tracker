package com.thecoducer.coronavirustracker.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.thecoducer.coronavirustracker.models.IndiaRecentUpdates;

@Service
public class IndiaRecentUpdatesDataService {

	List<IndiaRecentUpdates> indiaRecentUpdatesList = new ArrayList<>();

	@PostConstruct
	@Scheduled(cron = "0 0/5 * * * *")
	public void fetchRecentUpdates() throws MalformedURLException, ParseException, IOException {

		JSONArray recentUpdatesArray = (JSONArray) new JSONParser()
				.parse(IOUtils.toString(new URL("https://api.covid19india.org/updatelog/log.json").openStream()));

		List<IndiaRecentUpdates> newIndiaRecentUpdatesList = new ArrayList<>();

		int size = recentUpdatesArray.size();

		for (int i = size - 1; i >= (size - 5);) {

			JSONObject x_obj = (JSONObject) recentUpdatesArray.get(i);

			String update = (String) x_obj.get("update");
			String timestamp = String.valueOf(x_obj.get("timestamp"));

			// converting time in milliseconds to desired date format
			Date d = new Date(Long.parseLong(timestamp) * 1000);
			timestamp = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(d);

			String[] strings = update.split("\n");

			int len = strings.length;

			if (len == 1) {

				IndiaRecentUpdates obj = new IndiaRecentUpdates();

				obj.setUpdate(update);
				obj.setTimestamp(timestamp);

				newIndiaRecentUpdatesList.add(obj);
				i--;

			} else {

				for (int j = 0; j < strings.length; j++) {

					if (i < (size - 5)) {
						break;
					}

					IndiaRecentUpdates obj = new IndiaRecentUpdates();

					obj.setUpdate(strings[j]);
					obj.setTimestamp(timestamp);

					newIndiaRecentUpdatesList.add(obj);
					i--;
				}
			}
		}

		this.indiaRecentUpdatesList = newIndiaRecentUpdatesList;
	}

	public List<IndiaRecentUpdates> getIndiaRecentUpdates() {
		return indiaRecentUpdatesList;
	}

}