package com.prediction.wheather;

public enum WheatherIntensityType {
	NORMAL ("Normal")	, 
	HIGH ("Pico");
	
	private String description;

	private WheatherIntensityType(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return description;
	}
}
