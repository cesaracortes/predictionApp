package com.prediction.galaxy;

public interface CoordinateSystem {

	void addPoint(Double x_value, Double y_value);

	Boolean pointsAreAligned();

}
