package com.prediction.domain.galaxy;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.prediction.domain.galaxy.location.IPlanetsDistribution;
import com.prediction.domain.galaxy.location.IPositionCalculator;
import com.prediction.domain.galaxy.location.IUbicationSystem;
import com.prediction.domain.galaxy.location.UbicationDistribution;
import com.prediction.domain.galaxy.location.UbicationSystem;
import com.prediction.domain.galaxy.movement.Distance;
import com.prediction.domain.galaxy.movement.Unit;
import com.prediction.domain.gemoetrics.IGeometricShape;
import com.prediction.domain.gemoetrics.Line;
import com.prediction.domain.gemoetrics.Triangle;
import com.prediction.domain.planet.IPlanet;
import com.prediction.wheather.WheatherStatus;
import com.prediction.wheather.WheatherType;

/**
 * This Class represents a Galaxy, this class is responsible for the 
 * positiona
 * @author cesar.cortes
 *
 */
public class Galaxy implements IGalaxy {
	private IUbicationSystem<IPlanet> ubicationSystem = new UbicationSystem<>();
	private Collection<IPlanet> planets = new HashSet<IPlanet>();
	private IPositionCalculator positionCalculator = new PolarToCartesianPositionCalculator();
	private Map<UbicationDistribution , IPlanetsDistribution> pointDistribution = new HashMap<>();
	private int currentDay;

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


	public void addPlanet(IPlanet aPlanet, Integer distranceFromSun) {
		ubicationSystem.put(aPlanet, new Point(distranceFromSun,0));
		planets.add(aPlanet);
	}

	@Override
	public Point2D positionAtDay(IPlanet aPlanet) {
		return  ubicationSystem.positionFor(aPlanet);
	}

	private Distance radius(IPlanet aPlanet) {
		return ubicationSystem.distanceFromCenter(aPlanet);
	}

	
	@Override
	public Distance distanceTraveledAtDay(Integer dayNumber, IPlanet aPlanet) {
		Double amount = aPlanet.velocity().amount() * dayNumber;
		return Distance.amountWithUnit(amount, Unit.GRADES);
	}

	@Override
	public Boolean arePlanetsAlignedAtDay() {
		return getCurrentPlanetDistribution().arePlanetsAligned();
	}

	@Override
	public Boolean allAreAlignedToSunAtDay() {
		return getCurrentPlanetDistribution().arePlanetsAlignedToSun();
	}

	@Override
	public Boolean sunIsInsidePlanetsTriangleAtDay() {
		return getCurrentPlanetDistribution().isSunInsidePlanetsTriangle();
	}


	@Override
	public Distance distanceFromSun(IPlanet aPlanet) {
		return radius(aPlanet);
	}

	private Double currentPerimeterFormedByPlanets() {
		return ubicationSystem.perimeteForPoints();
	}

	
	public void movePlanets(){
		currentDay++;
		Set<Point2D> positions = new HashSet<>();
		for (IPlanet iPlanet : planets) {
			positions.add(movePlanet(iPlanet));
		}
	}

	private Point2D movePlanet(IPlanet iPlanet) {
		Point2D positionFor = ubicationSystem.positionFor(iPlanet);
		Point2D newPlace = positionCalculator.calculate(radius(iPlanet),iPlanet.velocity(),currentDay);
		ubicationSystem.moveFromTo(iPlanet, positionFor, newPlace);
		return newPlace;
	}

	private Map<UbicationDistribution, IPlanetsDistribution> getPointDistribution() {
		if(pointDistribution.isEmpty()){
			configurateDistributionPosibilities();
		}
		return pointDistribution;
	}

	private void configurateDistributionPosibilities() {
		pointDistribution.put(UbicationDistribution.ALIGNED, new PlanetsAlignedDistribution());
		pointDistribution.put(UbicationDistribution.ALIGNED_TO_CENTER, new PlanetsAlignedToSunDistribution());
		pointDistribution.put(UbicationDistribution.CENTER_INSIDE_TRINGLE, new PlanetsTriangleWithSunDistribution());
		pointDistribution.put(UbicationDistribution.CENTER_OUTSIDE_TRINGLE, new PlanetsTriangleDistribution());
	}

	@Override
	public WheatherStatus currentWeather() {
		IPlanetsDistribution planetsDistribution = getCurrentPlanetDistribution();
		return wheatherFor(planetsDistribution);
	}

	private IPlanetsDistribution getCurrentPlanetDistribution() {
		return getPointDistribution().get(ubicationSystem.distribution());
	}

	private WheatherStatus wheatherFor(IPlanetsDistribution planetsDistribution) {
		return planetsDistribution.wheatherForGalaxy(this);
		
	}

	public WheatherStatus weatherWhenPlanetsAligned() {
		return new WheatherStatus(currentDay, 0.0, WheatherType.GOOD);
	}

	public WheatherStatus weatherWhenPlanetsAlignedToSun() {
		return new WheatherStatus(currentDay, 0.0, WheatherType.DRY);
	}

	public WheatherStatus weatherWhenPlanetsTriangleWithSun() {
		Double intensity = currentPerimeterFormedByPlanets();
		return new WheatherStatus(currentDay, intensity, WheatherType.RAIN);
	}

	public WheatherStatus weatherWhenPlanetsTriangle() {
		return new WheatherStatus(currentDay, 0.0, WheatherType.NORMAL);
	}


}
