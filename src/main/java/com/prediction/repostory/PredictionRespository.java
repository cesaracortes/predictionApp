package com.prediction.repostory;

import org.springframework.data.repository.CrudRepository;

import com.prediction.WheatherReport;

public interface PredictionRespository extends CrudRepository<WheatherReport, Integer>{
	
	

}
