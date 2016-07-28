package com.prediction.domain.galaxy;

import java.awt.geom.Point2D;

import com.prediction.domain.galaxy.location.IPositionCalculator;
import com.prediction.domain.galaxy.location.IUbicationSystem;
import com.prediction.domain.galaxy.movement.Distance;
import com.prediction.domain.galaxy.movement.RoundUtils;
import com.prediction.domain.galaxy.movement.Velocity;
import com.prediction.domain.planet.IPlanet;

/**
 * Esta clase calcula la posicion final, asume que se hace dada una velocidad
 * angular, es decir transforma la posicion final a un punto cartesiano, no
 * polar.
 * 
 * @author cesar.cortes
 *
 */
public class PolarToCartesianPositionCalculator implements IPositionCalculator{

	private double radiansTraveledAtDay(Velocity velocity, Integer dayNumber) {
		double degrees = velocity.amount() * dayNumber;
		return Math.toRadians(degrees);
	}

	public Distance distanceFromSun(IUbicationSystem<IPlanet> ubicationSystem, IPlanet aPlanet) {
		return ubicationSystem.distanceFromCenter(aPlanet);
	}

	/**
	 * 
	 * @param radio
	 * @param velocity
	 * @param traveledTime
	 * @return
	 */
	public Point2D calculate(Distance radio, Velocity velocity, int traveledTime) {
		double x_pos = radio.amount() * convertToZero(newXPosition(radiansTraveledAtDay(velocity, traveledTime)));
		double y_pos = radio.amount() * convertToZero(newYPosition(radiansTraveledAtDay(velocity, traveledTime)));
		return new Point2D.Double(extracted(x_pos), extracted(y_pos));
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

	public Double convertToZero(Double value) {
		if (Math.abs(value) < 0.0000000000000001)
			return 0.0;
		return value;
	}

}
