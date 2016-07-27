package com.prediction.domain.galaxy;

import java.awt.geom.Point2D;

public interface IUbicationSystem<T> {

	void put(T element, Point2D place);

	Distance distanceFromCenter(T element);

	Boolean arePointsAligned();

	Boolean pointsAreAlignedToTheCenter();

	Boolean isCenterInsidePoints();

	Point2D positionFor(T element);

	void remove(Point2D previousPosition);

	double perimeteForPoint();
	

}
