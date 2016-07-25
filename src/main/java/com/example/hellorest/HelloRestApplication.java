package com.example.hellorest;

import javax.inject.Inject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
@RestController
public class HelloRestApplication {
	
	@Inject
	private PredictionRespository repo;

	public static void main(String[] args) {
		SpringApplication.run(HelloRestApplication.class, args);
	}
	
	@RequestMapping(value="/clima",method=RequestMethod.GET)
	public ResponseEntity<?> getPredictionForDay(@RequestParam(value="dia") Integer dayNumber) {
		putAll();
		Prediction findOne = repo.findOne(dayNumber);
		
		return new ResponseEntity<>(findOne.to_json(),HttpStatus.OK);
		
	}

	private void putAll() {
		repo.save(new Prediction("Seco", 1));
		repo.save(new Prediction("Seco", 2));
		repo.save(new Prediction("Lluvioso", 3));
		repo.save(new Prediction("Seco", 4));
		
	}
	
//	@Bean
//	public CommandLineRunner demo(PredictionRespository repo) {
//		return (args) -> {
//			repo.save(new Prediction("Seco", 1));
//			repo.save(new Prediction("Seco", 2));
//			repo.save(new Prediction("Lluvioso", 3));
//			repo.save(new Prediction("Seco", 4));
//			
//		};
//		
//	}
	


}
