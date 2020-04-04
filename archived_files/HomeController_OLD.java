package com.thecoducer.coronavirustracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import com.thecoducer.coronavirustracker.models.AllStats;
import com.thecoducer.coronavirustracker.services.CoronaVirusDataService;
import com.thecoducer.coronavirustracker.services.DeathsDataService;
import com.thecoducer.coronavirustracker.services.RecoveredCasesDataService;
import com.thecoducer.coronavirustracker.services.TestService;

import java.text.NumberFormat;
import java.util.Locale;

@Controller
public class HomeController {
	
	@Autowired
	CoronaVirusDataService coronaVirusDataService;
	
	@Autowired
	RecoveredCasesDataService recoveredCasesDataService;
	
	@Autowired
	DeathsDataService deathsDataService;
	
	@Autowired
	TestService testService;
	
	@GetMapping("/")
	public String home(Model model) {
		NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
		
		List<AllStats> allStats = coronaVirusDataService.getAllStats();
		int totalReportedCases = allStats.parallelStream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
		int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
		
		List<Integer> recoveredCasesCount = recoveredCasesDataService.getRecoveredCasesCount();
		int totalRecoveredCases = recoveredCasesCount.stream().mapToInt(stat -> stat).sum();
		
		List<Integer> deathCount = deathsDataService.getDeathCount();
		int totalDeathCount = deathCount.stream().mapToInt(stat -> stat).sum();
	
		//System.out.println("R" + recoveredCasesCount.size());
		//System.out.println("D" + deathCount.size());
		//System.out.println("A" + allStats.size());
		
		int totalActiveCases=0;
		
		int i=0;
		for(AllStats allstat:allStats) {
			if(i > recoveredCasesCount.size()-1) {
				break;
			}
			
			allstat.setLatestRecoveredCases(recoveredCasesCount.get(i));
			allstat.setLatestDeaths(deathCount.get(i));
			
			try {
				double recoveredRate = ((double)allstat.getLatestRecoveredCases() / (double)allstat.getLatestTotalCases()) * 100;
				double deathRate = ((double)allstat.getLatestDeaths() / (double)allstat.getLatestTotalCases()) * 100;
				allstat.setRecoveredRate(recoveredRate);
				allstat.setDeathRate(deathRate);
			}catch(Exception e) {
				allstat.setRecoveredRate(0);
				allstat.setDeathRate(0);
			}
			
			try {
				double deathRate = ((double)allstat.getLatestDeaths() / (double)allstat.getLatestTotalCases()) * 100;
				allstat.setDeathRate(deathRate);
			}catch(Exception e) {
				allstat.setDeathRate(0);
			}
			
			try {
				double casePercentage = ((double)allstat.getLatestTotalCases() / (double)totalReportedCases) * 100;
				allstat.setCasePercentage(casePercentage);
			}catch(Exception e) {
				allstat.setCasePercentage(0);
			}
			
			int activeCases = allstat.getLatestTotalCases() - (allstat.getLatestRecoveredCases() + allstat.getLatestDeaths());
			if(activeCases >= 0) {
				allstat.setActiveCases(activeCases);
				totalActiveCases += activeCases;
			}
			
			i++;
		}
		
		
		model.addAttribute("allStats", allStats);
		model.addAttribute("totalReportedCases", numberFormat.format(totalReportedCases));
		model.addAttribute("totalNewCases", numberFormat.format(totalNewCases));
		model.addAttribute("totalRecoveredCases", numberFormat.format(totalRecoveredCases));
		model.addAttribute("totalDeathCount", numberFormat.format(totalDeathCount));
		model.addAttribute("totalActiveCases", numberFormat.format(totalActiveCases));
		
		return "home";
	}
}

