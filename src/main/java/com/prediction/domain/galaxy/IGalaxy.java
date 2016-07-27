package com.prediction.domain.galaxy;

import java.awt.geom.Point2D;
import java.util.List;

import com.prediction.domain.planet.IPlanet;


public interface IGalaxy {

	boolean hasPlanets();

	Sun sun();

	// TODO cambiar integers por distance
	void addPlanet(IPlanet aPlanet, Integer distanceFromSun);

	Distance distanceTraveledAtDay(Integer dayNumber, IPlanet vulcano);

	boolean arePlanetsAlignedAtDay(Integer i);

	boolean allAreAlignedToSunAtDay(Integer aDayNumber);

	Point2D positionAtDay(Integer dayNumber, IPlanet vulcano);

	Boolean sunIsInsidePlanetsTriangleAtDay(Integer aDayNumber);

	Distance distanceFromSun(IPlanet aPlanet);

	double perimeterInThatDay(Integer aDayNumber);

	List<Point2D> planetsPositions(Integer dayNumber);



}
