package com.prediction;

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

}
