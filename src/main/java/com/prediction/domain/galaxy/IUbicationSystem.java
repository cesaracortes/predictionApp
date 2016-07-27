package com.prediction.galaxy;

import java.awt.Point;
import java.awt.geom.Point2D;

import com.prediction.planet.IPlanet;

public interface IUbicationSystem {

	void put(IPlanet aPlanet, Point place);

	Distance distanceFromSun(IPlanet aPlanet);

	Point2D calculatePositionFor(IPlanet aPlanet, Integer dayNumber);

	Boolean arePlanetsAlignedAtDay(Integer aDayNumber);

	Boolean sunIsInsidePlanetsTriangleAtDay(Integer aDayNumber);

	Boolean allAreAlignedToSunAtDay(Integer aDayNumber);

}
