package com.prediction;

public class WheatherIntensity {

	private Double intensity;
	private WheatherIntensityType type;

	public WheatherIntensity(Double intensity, WheatherIntensityType type) {
		this.intensity = intensity;
		this.type = type;
	}

	public Double getValue() {
		return intensity;
	}

	public void setType(WheatherIntensityType type) {
		this.type = type;
		
	}

}
