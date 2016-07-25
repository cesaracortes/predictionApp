package com.prediction.galaxy;

import java.awt.geom.Point2D;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.galaxy.exceptions.PlanetsInTheSameOrbitException;
import com.prediction.planet.IPlanet;

public class Galaxy implements IGalaxy {
	private Sun sun = new Sun();
	private Map<IPlanet, Integer> distanceFromSun = new HashMap<IPlanet, Integer>();

	public static IGalaxy bigBang() {
		return new Galaxy();
	}

	public boolean hasPlanets() {
		return !planets().isEmpty();
	}

	public void addPlanet(IPlanet aPlanet) {
		planets().add(aPlanet);
	}

	private Collection<IPlanet> planets() {
		return distanceFromSun.keySet();
	}

	public Sun sun() {
		return sun;
	}


	public void addPlanet(IPlanet aPlanet, Integer distranceFromSun) {
		if (distanceFromSun.values().contains(distranceFromSun))
			throw new PlanetsInTheSameOrbitException();
		this.distanceFromSun.put(aPlanet, distranceFromSun);

	}

	public Integer distanceTo(IPlanet planet) {
		return distanceFromSun.get(planet);
	}

	@Override
	public Point2D positionAtDay(Integer dayNumber, IPlanet aPlanet) {
		double x_pos = (distanceFromSun.get(aPlanet) * Math.cos(radiansTraveledAtDay(aPlanet, dayNumber)));
		double y_pos = (distanceFromSun.get(aPlanet) * Math.sin(radiansTraveledAtDay(aPlanet, dayNumber)));
		return new Point2D.Double(x_pos, y_pos);
	}

	private double radiansTraveledAtDay(IPlanet aPlanet, Integer dayNumber) {
		double degrees = 90 - (aPlanet.velocity().amount() * dayNumber);
		return Math.toRadians(degrees);
	}

	@Override
	public Distance distanceTraveledAtDay(Integer dayNumber, IPlanet aPlanet) {
		Double amount = aPlanet.velocity().amount() * dayNumber;
		return Distance.amountWithUnit(amount, Unit.GRADES);
	}

	@Override
	public boolean arePlanetsAlignedAtDay(Integer aDayNumber) {
		List<Point2D> positions = getPositions(aDayNumber);
		return areAligned(positions);
	}

	private List<Point2D> getPositions(Integer aDayNumber) {
		List<Point2D> positions = new LinkedList<Point2D>();
		for (IPlanet aPlanet : planets()) {
			positions.add(positionAtDay(aDayNumber, aPlanet));
		}
		return positions;
	}

	private boolean areAligned(List<Point2D> positions) {
		Point2D aPoint = positions.get(0);
		Point2D anothePoint = positions.get(1);
		Line aLine = new Line(aPoint, anothePoint);
		return aLine.contains(positions);
	}



	@Override
	public boolean allAreAlignedToSunAtDay(Integer aDayNumber) {
		List<Point2D> positions = getPositions(aDayNumber);
		positions.add(new Point2D.Double(0,0));
		return areAligned(positions);
	}

	@Override
	public Boolean sunIsInsidePlanetsTriangleAtDay(Integer aDayNumber) {
		List<Point2D> positions = getPositions(aDayNumber);
		
		Triangle aTriangle = new Triangle(positions.get(0), positions.get(1), positions.get(2));
		
		return aTriangle.contains(new Point2D.Double(0,0));
	}



}
