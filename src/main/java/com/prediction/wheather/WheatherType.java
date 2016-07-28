package com.prediction.wheather;

public enum WheatherType {
	NORMAL ("Sequia"),
	RAIN ("Rain"),
	DRY ("Seco"), 
	GOOD ("Optimo");

	
	private final String description;
	
	private WheatherType(String description) {
		this.description = description;
	}
	
	public String type() {
		return this.description;
	}
	
	@Override
	public String toString() {
		return description;
	}

}
