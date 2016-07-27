package com.prediction.controllers;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prediction.WheatherReport;
import com.prediction.domain.galaxy.WheatherType;
import com.prediction.repostory.PredictionRespository;

@RestController
public class PredictionController {
	
	@Inject
	private PredictionRespository repo;


	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<?> getPredictionForDay() {
		Iterable<WheatherReport> findAll = repo.findAll();
		List<String> all = new LinkedList<String>();
		for (WheatherReport report : findAll) {
			all.add(report.to_json());
		}
		return new ResponseEntity<>(all, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/dryDays", method = RequestMethod.GET)
	public ResponseEntity<?> getDryDays() {
		Long report = repo.countByType(WheatherType.DRY);
		return new ResponseEntity<>(report, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/rainyDays", method = RequestMethod.GET)
	public ResponseEntity<?> getRainyDays() {
		Long report = repo.countByType(WheatherType.RAIN);
		return new ResponseEntity<>(report, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/optimalDays", method = RequestMethod.GET)
	public ResponseEntity<?> getOptimalDays() {
		Long report = repo.countByType(WheatherType.GOOD);
		return new ResponseEntity<>(report, HttpStatus.OK);
	}

	@RequestMapping(value = "/clima", method = RequestMethod.GET)
	public ResponseEntity<?> getPredictionForDay(@RequestParam(value = "dia") Integer dayNumber) {
		WheatherReport report = repo.findOne(dayNumber);
		if(report == null){
			return new ResponseEntity<>("No elements founded for the filer", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(report.to_json(), HttpStatus.OK);
	}

}
