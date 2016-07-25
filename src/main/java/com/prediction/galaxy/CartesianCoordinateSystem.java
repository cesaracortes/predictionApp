package com.prediction.galaxy;

import java.util.HashSet;
import java.util.Set;

public class CartesianCoordinateSystem implements CoordinateSystem {

	private Set<CartesianPoint<Double>> points = new HashSet<CartesianPoint<Double>>();

	@Override
	public void addPoint(Double x_value, Double y_value) {
		points.add(new CartesianPoint<Double>(x_value,y_value));	
	}

	@Override
	public Boolean pointsAreAligned() {
		return true;
	}

}
