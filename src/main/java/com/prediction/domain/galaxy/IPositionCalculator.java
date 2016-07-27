package com.prediction.domain.galaxy;

import java.awt.geom.Point2D;

public interface IPositionCalculator {

	Point2D calculate(Distance radio, Velocity velocity, int traveledTime);

}
