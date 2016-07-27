package com.prediction;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.prediction.domain.galaxy.IGalaxy;
import com.prediction.domain.galaxy.IPrediction;

@Entity
public class Prediction implements IPrediction {
	
	
	private String prediction;
	
	@Id
	private Integer dayNumber;
	
	public Prediction() {
		// TODO Auto-generated constructor stub
	}

	public Prediction(String prediction, Integer dayNumber) {
		this.setPrediction(prediction);
		this.dayNumber = dayNumber;
	}

	public static Prediction forDay(Integer aDayNumber, IGalaxy aGalaxy) {
		if (aGalaxy.allAreAlignedToSunAtDay(aDayNumber)) {
			return new Prediction("Sequia", aDayNumber);
		} else if (aGalaxy.sunIsInsidePlanetsTriangleAtDay(aDayNumber)) {
			return new Prediction("Lluvioso", aDayNumber);
		} else {
			return new Prediction("Normal", aDayNumber);

		}
	}

	@Override
	public Boolean isADryDay() {
		return "Sequia".equals(getPrediction());
	}

	@Override
	public Integer isForDay() {
		return dayNumber;
	}

	@Override
	public Boolean isANormalDay() {
		return "Normal".equals(getPrediction());
	}

	@Override
	public Boolean isARainyDay() {
		return "Lluvioso".equals(getPrediction());
	}

	public String getPrediction() {
		return prediction;
	}

	public void setPrediction(String prediction) {
		this.prediction = prediction;
	}

	public String to_json() {
		String json = "{\"dia\":" + dayNumber + "," + "\"clima\":" +  prediction + "}";
		return json;
	}



}
