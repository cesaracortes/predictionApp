package com.prediction.domain.galaxy;

import java.awt.geom.Point2D;

public interface IUbicationSystem<T> {

	void put(T aPlanet, Point2D place);

	Distance distanceFromCenter(T aPlanet);

	Boolean arePointsAligned();

	Boolean pointsAreAlignedToTheCenter();

	Boolean isCenterInsidePoints();

	Point2D positionFor(T aPlanet);

	void remove(Point2D previousPosition);

}
