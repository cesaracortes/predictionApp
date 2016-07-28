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

import com.prediction.repostory.PredictionRespository;
import com.prediction.wheather.WheatherIntensityType;
import com.prediction.wheather.WheatherReport;
import com.prediction.wheather.WheatherType;

@RestController
public class PredictionController {
	
	@Inject
	private PredictionRespository repo;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<?> index() {
		String result = "This is the main page for PredictionApp, please use the follow links for information: https://github.com/cesaracortes/predictionApp ";
		return new ResponseEntity<>(result, HttpStatus.OK);
	}


	@RequestMapping(value = "/dryDays", method = RequestMethod.GET)
	public ResponseEntity<?> getDryDays() {
		Long report = repo.countByType(WheatherType.DRY);
		String result = "{\"dryDays\":" + "\"" +report +  "}";
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/rainyDays", method = RequestMethod.GET)
	public ResponseEntity<?> getRainyDays() {
		Long report = repo.countByType(WheatherType.RAIN);
		String result = "{\"rainyDays\":" + "\"" + report +  "}";
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/optimalDays", method = RequestMethod.GET)
	public ResponseEntity<?> getOptimalDays() {
		Long report = repo.countByType(WheatherType.GOOD);
		String result = "{\"optimalDays\":" + "\"" + report +  "}";
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/maxRainyDays", method = RequestMethod.GET)
	public ResponseEntity<?> getMaxRainyDays() {
		List<WheatherReport> report = repo.findByIntensityType(WheatherIntensityType.HIGH);
		return new ResponseEntity<>(report, HttpStatus.OK);
	}

	@RequestMapping(value = "/clima", method = RequestMethod.GET)
	public ResponseEntity<?> getPredictionForDay(@RequestParam(value = "dia") Integer dayNumber) {
		WheatherReport report = repo.findOne(dayNumber);
		if(report == null){
			String error = "{\"error\":" + "\"" + " No elements founded for the filer" +  "}";
			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(report.to_json(), HttpStatus.OK);
	}

}
