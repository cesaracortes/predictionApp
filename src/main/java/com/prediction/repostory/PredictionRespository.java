package com.prediction.repostory;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.prediction.wheather.WheatherIntensityType;
import com.prediction.wheather.WheatherReport;
import com.prediction.wheather.WheatherType;

public interface PredictionRespository extends CrudRepository<WheatherReport, Integer>{
	
    public long countByType(WheatherType wheatherType);

	public List<WheatherReport> findByIntensityType(WheatherIntensityType high);

}
