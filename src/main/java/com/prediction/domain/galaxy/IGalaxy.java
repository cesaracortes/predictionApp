package com.prediction.domain.galaxy;

import java.awt.geom.Point2D;
import java.util.List;

import com.prediction.domain.planet.IPlanet;


public interface IGalaxy {

	boolean hasPlanets();

	void addPlanet(IPlanet aPlanet, Integer distanceFromSun);

	Distance distanceTraveledAtDay(Integer dayNumber, IPlanet vulcano);

	Boolean arePlanetsAlignedAtDay(Integer i);

	Boolean allAreAlignedToSunAtDay(Integer aDayNumber);

	Point2D positionAtDay(Integer dayNumber, IPlanet vulcano);

	Boolean sunIsInsidePlanetsTriangleAtDay(Integer aDayNumber);

	Distance distanceFromSun(IPlanet aPlanet);

	Double currentPerimeterFormedByPlanets();

	List<Point2D> planetsPositions(Integer dayNumber);

	IPlanetsDistribution move();

	WheatherStatus weather();



}
