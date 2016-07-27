package com.prediction.repostory;

import org.springframework.data.repository.CrudRepository;

import com.prediction.Wheather;

public interface PredictionRespository extends CrudRepository<Wheather, Integer>{
	
	

}
