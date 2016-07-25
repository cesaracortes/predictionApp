package com.prediction.galaxy;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.util.Assert;

import com.galaxy.exceptions.PlanetsInTheSameOrbitException;
import com.prediction.planet.IPlanet;

public class UbicationSystem implements IUbicationSystem {

	private Map<Point2D, IPlanet> occupiedPlaces = new HashMap<Point2D, IPlanet>();

	@Override
	public void put(IPlanet aPlanet, Point place) {
		if (occupiedPlaces.containsKey(place))
			throw new PlanetsInTheSameOrbitException();
		this.occupiedPlaces.put(place, aPlanet);
	}

	@Override
	public Distance distanceFromSun(IPlanet aPlanet) {
		Point2D ubication = ubicationFor(aPlanet);
		double amount = ubication.distance(new Point(0, 0));
		return Distance.amountWithUnit(amount, Unit.KM);
	}

	private Point2D ubicationFor(IPlanet aPlanet) {
		for (Point2D aPlace : occupiedPlaces.keySet()) {
			if (occupiedPlaces.get(aPlace).equals(aPlanet)) {
				return aPlace;
			}
		}
		throw new NotPlanetInTheSystemException();

	}

	@Override
	public Point2D calculatePositionFor(IPlanet aPlanet, Integer dayNumber) {
		double x_pos = (distanceFromSun(aPlanet).amount() * Math.cos(radiansTraveledAtDay(aPlanet, dayNumber)));
		double y_pos = (distanceFromSun(aPlanet).amount() * Math.sin(radiansTraveledAtDay(aPlanet, dayNumber)));
		return new Point2D.Double(x_pos, y_pos);
	}

	private double radiansTraveledAtDay(IPlanet aPlanet, Integer dayNumber) {
		double degrees = 90 - (aPlanet.velocity().amount() * dayNumber);
		return Math.toRadians(degrees);
	}

	@Override
	public Boolean arePlanetsAlignedAtDay(Integer aDayNumber) {
		Set<Point2D> positions = getPositions(aDayNumber);
		return areAligned(positions);
	}

	private Set<Point2D> getPositions(Integer aDayNumber) {
		return occupiedPlaces.keySet();
	}

	// TODO ver mejorar codigo
	private boolean areAligned(Set<Point2D> positions) {
		return Line.areAligned(positions);
	}

	@Override
	public Boolean allAreAlignedToSunAtDay(Integer aDayNumber) {
		Set<Point2D> positions = new HashSet<Point2D>(getPositions(aDayNumber));
		positions.add(new Point2D.Double(0, 0));
		return areAligned(positions);
	}

	@Override
	public Boolean sunIsInsidePlanetsTriangleAtDay(Integer aDayNumber) {
		// Se necesitan al menos tres puntos para definir un triangulo
		Set<Point2D> positions = getPositions(aDayNumber);
		Assert.isTrue(positions.size() >= 3);
		List<Point2D> points = new ArrayList<Point2D>(positions);
		Triangle aTriangle = new Triangle(points.get(0), points.get(1), points.get(2));
		return aTriangle.contains(new Point2D.Double(0, 0));
	}

}
