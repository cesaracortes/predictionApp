package com.prediction.domain.galaxy;

public class RoundUtils {

	public static double round(double value) {
		long factor = (long) Math.pow(10, 2);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}
}
