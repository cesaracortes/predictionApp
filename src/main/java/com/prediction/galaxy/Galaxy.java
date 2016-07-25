package com.prediction.galaxy;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Collection;
import java.util.HashSet;

import com.prediction.planet.IPlanet;

public class Galaxy implements IGalaxy {
	private Sun sun = new Sun();
	private IUbicationSystem ubicationSystem = new UbicationSystem();
	private Collection<IPlanet> planets = new HashSet<IPlanet>();

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
		return planets;
	}

	public Sun sun() {
		return sun;
	}

	public void addPlanet(IPlanet aPlanet, Integer distranceFromSun) {
		ubicationSystem.put(aPlanet, new Point(0, distranceFromSun));
		planets.add(aPlanet);
	}

	@Override
	public Point2D positionAtDay(Integer dayNumber, IPlanet aPlanet) {
		return ubicationSystem.calculatePositionFor(aPlanet, dayNumber);
	}

	@Override
	public Distance distanceTraveledAtDay(Integer dayNumber, IPlanet aPlanet) {
		Double amount = aPlanet.velocity().amount() * dayNumber;
		return Distance.amountWithUnit(amount, Unit.GRADES);
	}

	@Override
	public boolean arePlanetsAlignedAtDay(Integer aDayNumber) {
		return ubicationSystem.arePlanetsAlignedAtDay(aDayNumber);
	}

	@Override
	public boolean allAreAlignedToSunAtDay(Integer aDayNumber) {
		return ubicationSystem.allAreAlignedToSunAtDay(aDayNumber);
	}

	@Override
	public Boolean sunIsInsidePlanetsTriangleAtDay(Integer aDayNumber) {
		return sunIsInsidePlanetsTriangleAtDay(aDayNumber);
	}

	@Override
	public Distance distanceFromSun(IPlanet aPlanet) {
		return ubicationSystem.distanceFromSun(aPlanet);
	}

}
