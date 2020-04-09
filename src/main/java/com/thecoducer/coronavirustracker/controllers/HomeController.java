package com.thecoducer.coronavirustracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import com.thecoducer.coronavirustracker.models.AllStats;
import com.thecoducer.coronavirustracker.models.IndiaStateDistricts;
import com.thecoducer.coronavirustracker.models.IndiaStats;
import com.thecoducer.coronavirustracker.models.TestedStats;
import com.thecoducer.coronavirustracker.models.WorldStats;
import com.thecoducer.coronavirustracker.services.AllStatsDataService;
import com.thecoducer.coronavirustracker.services.IndiaStateDistrictDataService;
import com.thecoducer.coronavirustracker.services.IndiaStatsDataService;
import com.thecoducer.coronavirustracker.services.IndiaTestedDataService;
import com.thecoducer.coronavirustracker.services.IndiaTotalCountersDataService;
import com.thecoducer.coronavirustracker.services.WorldStatsDataService;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

@Controller
public class HomeController {

	@Autowired
	AllStatsDataService allStatsDataService;

	@Autowired
	WorldStatsDataService worldStatsDataService;

	@Autowired
	IndiaStatsDataService indiaStatsDataService;

	@Autowired
	IndiaTotalCountersDataService indiaTotalCounterDataService;

	@Autowired
	IndiaTestedDataService indiaTestedDataService;

	@Autowired
	IndiaStateDistrictDataService indiaStateDistrictDataService;

	@GetMapping("/")
	public String home(Model model) {

		NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);

		AllStats allStats = allStatsDataService.getAllStats();

		List<WorldStats> worldStats = worldStatsDataService.getWorldStats();

		List<IndiaStats> indiaStats = indiaStatsDataService.getIndiaStats();

		TestedStats testStats = indiaTestedDataService.getTestStats();

		Map<String, List<IndiaStateDistricts>> indiaStateDistrictData = indiaStateDistrictDataService
				.getStateDistrictData();

		model.addAttribute("worldStats", worldStats);
		model.addAttribute("totalNewCases", worldStatsDataService.getTotalNewCasesCount());
		model.addAttribute("totalNewDeathsCount", worldStatsDataService.getTotalNewDeathsCount());
		model.addAttribute("totalWorldCases", numberFormat.format(Long.parseLong(allStats.getCases())));
		model.addAttribute("totalRecoveredCases", numberFormat.format(Long.parseLong(allStats.getRecovered())));
		model.addAttribute("totalDeathCount", numberFormat.format(Long.parseLong(allStats.getDeaths())));
		model.addAttribute("totalActiveCases", numberFormat.format(Long.parseLong(allStats.getActive())));
		model.addAttribute("affectedCountries", allStats.getAffectedCountries());

		String totalIndiaCases = numberFormat.format(Integer.valueOf(indiaTotalCounterDataService.getTotalCases()));
		String totalIndiaNewCases = numberFormat
				.format(Integer.valueOf(indiaTotalCounterDataService.getTotalNewCases()));
		String totalIndiaActiveCases = numberFormat
				.format(Integer.valueOf(indiaTotalCounterDataService.getTotalActiveCases()));
		String totalIndiaRecoveredCases = numberFormat
				.format(Integer.valueOf(indiaTotalCounterDataService.getTotalRecoveredCases()));
		String totalIndiaNewRecoveredCases = numberFormat
				.format(Integer.valueOf(indiaTotalCounterDataService.getTotalNewRecoveredCases()));
		String totalIndiaNewDeaths = numberFormat
				.format(Integer.valueOf(indiaTotalCounterDataService.getTotalNewDeaths()));
		String totalIndiaDeaths = numberFormat.format(Integer.valueOf(indiaTotalCounterDataService.getTotalDeaths()));

		model.addAttribute("indiaStats", indiaStats);
		model.addAttribute("totalIndiaCases", totalIndiaCases);
		model.addAttribute("totalIndiaNewCases", totalIndiaNewCases);
		model.addAttribute("totalIndiaActiveCases", totalIndiaActiveCases);
		model.addAttribute("totalIndiaRecoveredCases", totalIndiaRecoveredCases);
		model.addAttribute("totalIndiaNewRecoveredCases", totalIndiaNewRecoveredCases);
		model.addAttribute("totalIndiaNewDeaths", totalIndiaNewDeaths);
		model.addAttribute("totalIndiaDeaths", totalIndiaDeaths);

		String sourceIndividualTested = testStats.getSourceIndividualTested();
		String sourceSamplesTested = testStats.getSourceSamplesTested();

		String timestampIndividual = testStats.getTimestampIndividual();
		String timestampSamples = testStats.getTimestampSamples();

		String totalIndividualTested = numberFormat.format(Long.parseLong(testStats.getTotalIndividualTested()));
		String totalSamplesTested = numberFormat.format(Long.parseLong(testStats.getTotalSamplesTested()));

		model.addAttribute("sourceIndividualTested", sourceIndividualTested);
		model.addAttribute("sourceSamplesTested", sourceSamplesTested);
		model.addAttribute("timestampIndividual", timestampIndividual);
		model.addAttribute("timestampSamples", timestampSamples);
		model.addAttribute("totalIndividualTested", totalIndividualTested);
		model.addAttribute("totalSamplesTested", totalSamplesTested);

		for (Map.Entry<String, List<IndiaStateDistricts>> entry : indiaStateDistrictData.entrySet()) {
			if (entry.getValue().size() != 0) {
				model.addAttribute(entry.getKey(), entry.getValue());
			}
		}

		return "home";
	}
}