package com.prediction.galaxy.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

import com.prediction.domain.exceptions.ElementsInTheSamePositionException;
import com.prediction.domain.galaxy.Galaxy;
import com.prediction.domain.galaxy.IGalaxy;
import com.prediction.domain.galaxy.PlanetFactory;
import com.prediction.domain.galaxy.movement.Distance;
import com.prediction.domain.galaxy.movement.Unit;
import com.prediction.domain.galaxy.movement.Velocity;
import com.prediction.domain.planet.IPlanet;
import com.prediction.domain.planet.Planet;

public class TestGalaxy {

	@Test
	public void whenItsCreatedHasASunAndNoPlanets() {
		IGalaxy aGalaxy = Galaxy.bigBang();
		assertFalse(aGalaxy.hasPlanets());
	}

	@Test
	public void canHavePlanets() {
		IGalaxy aGalaxy = createGalaxyWithPlanets();
		assertTrue(aGalaxy.hasPlanets());
	}

	@Test
	public void planetsAreAlignedWhenGalaxyIsCreated() {
		IGalaxy aGalaxy = createGalaxyWithPlanets();
		Boolean areAligned = aGalaxy.arePlanetsAlignedAtDay();
		assertTrue(areAligned);
	}

	@Test(expected = ElementsInTheSamePositionException.class)
	public void canNotHavePlanetsWithTheSameDistanceFromSun() {
		IGalaxy aGalaxy = createGalaxyWithPlanets();
		aGalaxy.addPlanet(PlanetFactory.createVulcano(), 500);
		aGalaxy.addPlanet(PlanetFactory.createFerengi(), 500);
	}

	@Test
	public void distanceFromSun() {
		IGalaxy aGalaxy = Galaxy.bigBang();
		IPlanet vulcano = PlanetFactory.createVulcano();
		aGalaxy.addPlanet(vulcano, 500);
		assertEquals(Distance.amountWithUnit(500.0, Unit.KM), aGalaxy.distanceFromSun(vulcano));
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
		for (int i = 1; i <= 5; i++) {
			aGalaxy.movePlanets();
			assertTrue(aGalaxy.arePlanetsAlignedAtDay());	
		}
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
		assertTrue(aGalaxy.allAreAlignedToSunAtDay());
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
			aGalaxy.movePlanets();
			assertTrue(aGalaxy.arePlanetsAlignedAtDay());
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
