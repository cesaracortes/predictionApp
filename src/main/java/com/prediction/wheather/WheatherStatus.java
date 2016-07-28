package com.prediction.wheather;

import javax.persistence.Entity;
import javax.persistence.Id;

public class WheatherStatus implements  Comparable<WheatherStatus>{

	
	private Integer aDayNumber;
	private Double intensity;
	private WheatherType type;
	

	public WheatherStatus(Integer aDayNumber , Double intensity , WheatherType type ) {
		this.intensity = intensity;
		this.aDayNumber = aDayNumber;
		this.setType(type);
	}

	
	

	public Integer getDayNumber() {
		return aDayNumber;
	}

	public void setaDayNumber(Integer aDayNumber) {
		this.aDayNumber = aDayNumber;
	}



	public Boolean isRainy() {
		return WheatherType.RAIN.equals(getType());
	}


	@Override
	public int compareTo(WheatherStatus other) {
		return (int) (getIntensity() - other.getIntensity()) ;
	}


	public Double getIntensity() {
		return intensity;
	}




	public WheatherType getType() {
		return type;
	}

	public void setType(WheatherType type) {
		this.type = type;
	}


	



	
		

}
