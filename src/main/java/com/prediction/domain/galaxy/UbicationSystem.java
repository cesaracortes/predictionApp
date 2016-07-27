package com.prediction.domain.galaxy;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.util.Assert;

import com.prediction.domain.exceptions.PlanetsInTheSameOrbitException;
import com.prediction.domain.gemoetrics.IGeometricShape;
import com.prediction.domain.gemoetrics.Line;
import com.prediction.domain.gemoetrics.Triangle;

public class UbicationSystem<T> implements IUbicationSystem<T> {

	private Map<Point2D, T> occupiedPlaces = new HashMap<Point2D, T>();
	

	@Override
	public void put(T aPlanet, Point2D place) {
		if (occupiedPlaces.containsKey(place) && occupiedPlaces.get(place) != aPlanet)
			throw new PlanetsInTheSameOrbitException();
		this.occupiedPlaces.put(place, aPlanet);
	}

	@Override
	public Distance distanceFromCenter(T aPlanet) {
		Point2D ubication = positionFor(aPlanet);
		double amount = ubication.distance(new Point(0, 0));
		return Distance.amountWithUnit(RoundUtils.round(amount), Unit.KM);
	}

	@Override
	public Point2D positionFor(T aPlanet) {
		for (Point2D aPlace : occupiedPlaces.keySet()) {
			if (occupiedPlaces.get(aPlace).equals(aPlanet)) {
				return aPlace;
			}
		}
		throw new NotElementInTheSystemException();

	}


	@Override
	public Boolean arePointsAligned() {
		Set<Point2D> positions = getPositions();
		return areAligned(positions);
	}

	private Set<Point2D> getPositions() {
		return occupiedPlaces.keySet();
	}

	private boolean areAligned(Set<Point2D> positions) {
		return Line.areAligned(positions);
	}

	@Override
	public Boolean pointsAreAlignedToTheCenter() {
		Set<Point2D> positions = new HashSet<Point2D>(getPositions());
		positions.add(new Point2D.Double(0, 0));
		return areAligned(positions);
	}

	@Override
	public Boolean isCenterInsidePoints() {
		// Se necesitan al menos tres puntos para definir un triangulo
		Set<Point2D> positions = getPositions();
		Assert.isTrue(positions.size() >= 3);
		List<Point2D> points = new ArrayList<Point2D>(positions);
		Triangle aTriangle = new Triangle(points.get(0), points.get(1), points.get(2));
		return aTriangle.contains(new Point2D.Double(0, 0));
	}

	@Override
	public void remove(Point2D position) {
		occupiedPlaces.remove(position);
	}

	@Override
	public double perimeteForPoint() {
		Set<Point2D> positions = getPositions();
		List<Point2D> points = new ArrayList<Point2D>(positions);
		Triangle aTriangle = new Triangle(points.get(0), points.get(1), points.get(2));
		return aTriangle.perimeter();
	}

}
