package com.prediction.domain.galaxy;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.prediction.domain.planet.IPlanet;

public class Galaxy implements IGalaxy {
	private Sun sun = new Sun();
	private IUbicationSystem<IPlanet> ubicationSystem = new UbicationSystem<>();
	private Collection<IPlanet> planets = new HashSet<IPlanet>();
	private PositionCalculator positionCalculator = new PositionCalculator();

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
		ubicationSystem.put(aPlanet, new Point(distranceFromSun,0));
		planets.add(aPlanet);
	}

	@Override
	public Point2D positionAtDay(Integer dayNumber, IPlanet aPlanet) {
		Point2D previousPosition = ubicationSystem.positionFor(aPlanet);
		Point2D place = positionCalculator.calculate(ubicationSystem.distanceFromCenter(aPlanet),aPlanet.velocity(),dayNumber,previousPosition);
		ubicationSystem.remove(previousPosition);
		ubicationSystem.put(aPlanet, place);
		return place;
	}

	

	
	@Override
	public Distance distanceTraveledAtDay(Integer dayNumber, IPlanet aPlanet) {
		Double amount = aPlanet.velocity().amount() * dayNumber;
		return Distance.amountWithUnit(amount, Unit.GRADES);
	}

	@Override
	public boolean arePlanetsAlignedAtDay(Integer aDayNumber) {
		planets.stream().forEach(aPlanet -> positionAtDay(aDayNumber, aPlanet));
		return ubicationSystem.arePointsAligned();
	}

	@Override
	public boolean allAreAlignedToSunAtDay(Integer aDayNumber) {
		planets.stream().forEach(aPlanet -> positionAtDay(aDayNumber, aPlanet));
		return ubicationSystem.arePointsAligned();
	}

	@Override
	public Boolean sunIsInsidePlanetsTriangleAtDay(Integer aDayNumber) {
		planets.stream().forEach(aPlanet -> positionAtDay(aDayNumber, aPlanet));
		return ubicationSystem.isCenterInsidePoints();
	}


	@Override
	public Distance distanceFromSun(IPlanet aPlanet) {
		return ubicationSystem.distanceFromCenter(aPlanet);
	}

	@Override
	public double perimeterInThatDay(Integer dayNumber) {
		return ubicationSystem.perimeteForPoint();
	}

	@Override
	public List<Point2D> planetsPositions(Integer dayNumber) {
		Stream<Point2D> map = planets.stream().map(aPlanet -> ubicationSystem.positionFor(aPlanet));
		return map.collect(Collectors.toList());
		
	}


}
