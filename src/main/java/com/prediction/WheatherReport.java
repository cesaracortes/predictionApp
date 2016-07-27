package com.prediction;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prediction.domain.galaxy.WheatherIntensityType;
import com.prediction.domain.galaxy.WheatherType;

@Entity
public class WheatherReport  {

	@Id
	private Integer day;
	private WheatherType type;
	private WheatherIntensityType intensityType;
	
	public WheatherReport() {
		// TODO Auto-generated constructor stub
	}
	

	public WheatherReport(Integer dayNumber, WheatherType type, WheatherIntensityType intensityType) {
		this.day = dayNumber;
		this.type = type;
		this.setIntensityType(intensityType);
	}
	
	
	public String to_json() {
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		try {
			json = mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		String json = "{\"dia\":" + "\"" + getDayNumber() + "," + "\"clima\":" + getType().toString() + "," + "\"intensity\":" + intensityType.toString()+ "}";
		return json;
	}
	
	public Integer getDay() {
		return day;
	}


	public WheatherType getClima() {
		return type;
	}


	public WheatherIntensityType getIntensityType() {
		return intensityType;
	}


	public void setIntensityType(WheatherIntensityType intensityType) {
		this.intensityType = intensityType;
	}


	
	

}
