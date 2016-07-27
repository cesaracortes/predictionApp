package com.prediction.domain.galaxy;

public interface IPrediction {

	Boolean isADryDay();

	Integer isForDay();

	Boolean isANormalDay();

	Boolean isARainyDay();

	Object to_json();

}
