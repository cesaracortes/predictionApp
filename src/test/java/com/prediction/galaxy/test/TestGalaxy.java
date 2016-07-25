package com.prediction.galaxy.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.geom.Point2D;

import org.junit.Ignore;
import org.junit.Test;

import com.galaxy.exceptions.PlanetsInTheSameOrbitException;
import com.prediction.galaxy.Distance;
import com.prediction.galaxy.Galaxy;
import com.prediction.galaxy.IGalaxy;
import com.prediction.galaxy.PlanetFactory;
import com.prediction.galaxy.Unit;
import com.prediction.galaxy.Velocity;
import com.prediction.planet.IPlanet;
import com.prediction.planet.Planet;

public class TestGalaxy {

	@Test
	public void whenItsCreatedHasASunAndNoPlanets() {
		IGalaxy aGalaxy = Galaxy.bigBang();
		assertFalse(aGalaxy.hasPlanets());
	}

	@Test
	public void canHavePlanets() {
		// TODO cambiar
		IGalaxy aGalaxy = createGalaxyWithPlanets();
		assertTrue(aGalaxy.hasPlanets());
	}

	@Test
	public void planetsAreAlignedWhenGalaxyIsCreated() {
		IGalaxy aGalaxy = createGalaxyWithPlanets();
		Boolean areAligned = aGalaxy.arePlanetsAlignedAtDay(0);
		assertTrue(areAligned);
	}

	@Test(expected = PlanetsInTheSameOrbitException.class)
	public void cantHavePlanetsWithTheSameDistanceFromSun() {
		IGalaxy aGalaxy = createGalaxyWithPlanets();
		aGalaxy.addPlanet(PlanetFactory.createVulcano(), 500);
		aGalaxy.addPlanet(PlanetFactory.createFerengi(), 500);
	}

	@Test
	public void distanceFromSun() {
		IGalaxy aGalaxy = Galaxy.bigBang();
		IPlanet vulcano = PlanetFactory.createVulcano();
		aGalaxy.addPlanet(vulcano, 500);
		assertEquals(new Integer(500), aGalaxy.distanceTo(vulcano));
	}

	// Planets position

	@Test
	public void planetStartAtPositionRelativeToSun() {
		IGalaxy aGalaxy = Galaxy.bigBang();
		IPlanet vulcano = PlanetFactory.createVulcano();
		aGalaxy.addPlanet(vulcano, 500);
		assertEquals(new Point2D.Double(0, 500), aGalaxy.positionAtDay(0, vulcano));
	}


	
	@Test
	public void testPlanetsAreAligned(){
		IGalaxy aGalaxy = Galaxy.bigBang();
		IPlanet vulcano = Planet.named("vulcano").withVelocity(Velocity.withAmountAndUnit(5.0, Unit.GRADE_PER_DAY));
		IPlanet ferengi = Planet.named("ferengi").withVelocity(Velocity.withAmountAndUnit(5.0, Unit.GRADE_PER_DAY));
		IPlanet betasoide = Planet.named("betasoide").withVelocity(Velocity.withAmountAndUnit(5.0, Unit.GRADE_PER_DAY));
		aGalaxy.addPlanet(vulcano, 500);
		aGalaxy.addPlanet(ferengi, 1000);
		aGalaxy.addPlanet(betasoide, 2000);
		assertTrue(aGalaxy.arePlanetsAlignedAtDay(5));	
	}



	@Test
	public void planetDistanceTraveledInOneDay() {
		IGalaxy aGalaxy = Galaxy.bigBang();
		IPlanet vulcano = PlanetFactory.createVulcano();
		aGalaxy.addPlanet(vulcano, 500);
		Distance distance = Distance.amountWithUnit(new Double(5), Unit.GRADES);
		assertEquals(distance, aGalaxy.distanceTraveledAtDay(1, vulcano));
	}

	@Test
	public void allPlanetsAreAlignedToSunWhenGalaxyIsCreated() {
		IGalaxy aGalaxy = createGalaxyWithPlanets();
		assertTrue(aGalaxy.allAreAlignedToSunAtDay(0));
	}
	
	public void testSunIsInsidePlanets(){
		IGalaxy aGalaxy = createGalaxyWithPlanets();
		aGalaxy.sunIsInsidePlanetsTriangleAtDay(2);
		
	}

	@Test
	@Ignore
	public void whenPlanetsHaveAllTheSameVelocityAreAlwaysAligned() {
		IGalaxy aGalaxy = Galaxy.bigBang();
		IPlanet vulcano = Planet.named("vulcano").withVelocity(Velocity.withAmountAndUnit(5.0, Unit.GRADE_PER_DAY));
		IPlanet ferengi = Planet.named("ferengi").withVelocity(Velocity.withAmountAndUnit(5.0, Unit.GRADE_PER_DAY));
		aGalaxy.addPlanet(vulcano, 500);
		aGalaxy.addPlanet(ferengi, 500);
		for (int i = 1; i < 100; i++) {
			assertTrue(aGalaxy.arePlanetsAlignedAtDay(i));
		}
	}



	private IGalaxy createGalaxyWithPlanets() {
		final IGalaxy aGalaxy = Galaxy.bigBang();
		IPlanet vulcano = PlanetFactory.createVulcano();
		aGalaxy.addPlanet(vulcano, 1000);
		IPlanet ferengi = PlanetFactory.createFerengi();
		aGalaxy.addPlanet(ferengi, 500);
		IPlanet betasoide = PlanetFactory.createBetasoide();
		aGalaxy.addPlanet(betasoide, 2000);
		return aGalaxy;
	}

}
