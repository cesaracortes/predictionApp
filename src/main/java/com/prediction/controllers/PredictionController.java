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

import com.prediction.Wheather;
import com.prediction.repostory.PredictionRespository;

@RestController
public class PredictionController {
	
	@Inject
	private PredictionRespository repo;


	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<?> getPredictionForDay() {
		Iterable<Wheather> findAll = repo.findAll();
		List<String> all = new LinkedList<String>();
		for (Wheather prediction : findAll) {
			all.add(prediction.to_json());
		}
		return new ResponseEntity<>(all, HttpStatus.OK);

	}

	@RequestMapping(value = "/clima", method = RequestMethod.GET)
	public ResponseEntity<?> getPredictionForDay(@RequestParam(value = "dia") Integer dayNumber) {
		Wheather findOne = repo.findOne(dayNumber);
		return new ResponseEntity<>(findOne.to_json(), HttpStatus.OK);
	}

}
