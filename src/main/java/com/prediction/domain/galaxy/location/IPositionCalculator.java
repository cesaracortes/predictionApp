package com.prediction.domain.galaxy.location;

import java.awt.geom.Point2D;

import com.prediction.domain.galaxy.movement.Distance;
import com.prediction.domain.galaxy.movement.Velocity;

public interface IPositionCalculator {

	/**
	 * Calcula la nueva posicion dada una velocidad y tiempo de viaje( por ahora expresada solo como un entero)
	 * @param radio
	 * @param velocity
	 * @param traveledTime
	 * @return la nueva posicion calculada
	 */
	Point2D calculate(Distance radio, Velocity velocity, int traveledTime);

}
