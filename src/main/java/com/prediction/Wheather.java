package com.prediction;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Wheather implements  Comparable<Wheather>{

	
	@Id
	private Integer aDayNumber;
	@Transient
	private WheatherIntensity intensity;
	private WheatherType type;
	
	public Wheather() {
		// TODO Auto-generated constructor stub
	}

	public Wheather(Integer aDayNumber , Double intensity , WheatherType type ) {
		this.intensity = new WheatherIntensity(intensity,WheatherIntensityType.NORMAL);
		this.aDayNumber = aDayNumber;
		this.type = type;
	}

	
	public String to_json() {
		String json = "{\"dia\":" + getDayNumber() + "," + "\"clima\":" + type.toString() + "}";
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
	public int compareTo(Wheather other) {
		return (int) (getIntensity() - other.getIntensity()) ;
	}


	public Double getIntensity() {
		return intensity.getValue();
	}



	public void makeHigh() {
		intensity.setType(WheatherIntensityType.HIGH);
		
	}


	



	
		

}
