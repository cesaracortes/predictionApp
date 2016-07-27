package com.prediction;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class WheatherReport implements  Comparable<WheatherReport>{

	
	@Id
	private Integer aDayNumber;
	private Double intensity;
	private WheatherType type;
	private WheatherIntensityType intensityType = WheatherIntensityType.NORMAL;
	
	public WheatherReport() {
		// TODO Auto-generated constructor stub
	}

	public WheatherReport(Integer aDayNumber , Double intensity , WheatherType type ) {
		this.intensity = intensity;
		this.aDayNumber = aDayNumber;
		this.type = type;
	}

	
	public String to_json() {
		String json = "{\"dia\":" + getDayNumber() + "," + "\"clima\":" + type.toString() + "," + "\"intensity\":" + intensityType.toString()+ "}";
		return json;
	}

	public Integer getDayNumber() {
		return aDayNumber;
	}

	public void setaDayNumber(Integer aDayNumber) {
		this.aDayNumber = aDayNumber;
	}



	public Boolean isRainy() {
		return WheatherType.RAIN.equals(type);
	}


	@Override
	public int compareTo(WheatherReport other) {
		return (int) (getIntensity() - other.getIntensity()) ;
	}


	public Double getIntensity() {
		return intensity;
	}



	public void makeHigh() {
		intensityType = WheatherIntensityType.HIGH;
		
	}


	



	
		

}
