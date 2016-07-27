package com.prediction.domain.galaxy;

import java.awt.geom.Point2D;

import com.prediction.domain.planet.IPlanet;

public class PositionCalculator {

	private double radiansTraveledAtDay(Velocity velocity, Integer dayNumber) {
		double degrees = velocity.amount() * dayNumber;
		return Math.toRadians(degrees);
	}

	public Distance distanceFromSun(IUbicationSystem<IPlanet> ubicationSystem, IPlanet aPlanet) {
		return ubicationSystem.distanceFromCenter(aPlanet);
	}

	public Point2D calculate(Distance radio , Velocity velocity, int dayNumber, Point2D po ) {
		double x_pos =  radio.amount() *  convertToZero(newXPosition(radiansTraveledAtDay(velocity, dayNumber)));
		double y_pos = radio.amount() * convertToZero(newYPosition(radiansTraveledAtDay(velocity, dayNumber)));
		return new Point2D.Double(extracted(x_pos),extracted(y_pos));		
	}

	private double extracted(double x_pos) {
		return RoundUtils.round(x_pos);
	}
	


	private double newYPosition(double radiansTraveledAtDay) {
		return Math.sin(radiansTraveledAtDay);
	}

	private Double newXPosition(double radiansTraveledAtDay) {
		return Math.cos(radiansTraveledAtDay);
	}


	
	public  Double convertToZero(Double value){
		if(Math.abs(value) <  0.0000000000000001)
			return 0.0;
		return value;
	}

		

}
