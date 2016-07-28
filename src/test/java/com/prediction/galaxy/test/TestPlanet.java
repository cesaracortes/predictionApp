package com.prediction.galaxy.test;

import org.junit.Assert;
import org.junit.Test;

import com.prediction.domain.exceptions.CreationException;
import com.prediction.domain.galaxy.movement.Unit;
import com.prediction.domain.galaxy.movement.Velocity;
import com.prediction.domain.planet.IPlanet;
import com.prediction.domain.planet.Planet;

public class TestPlanet {

	@Test
	public void testPlanetName() {

		IPlanet aPlanet = Planet.named("Vulcano");

		Assert.assertEquals("Vulcano", aPlanet.name());

	}

	@Test(expected = CreationException.class)
	public void testPlanetShouldHaveAValidName() {
		Planet.named("");
		Planet.named(null);
	}

	@Test
	public void testPlanetVelocity() {
		Velocity velocity = Velocity.withAmountAndUnit(1.0,Unit.GRADE_PER_DAY); 
		IPlanet aPlanet = Planet.named("Ferengi").withVelocity(velocity);
		Assert.assertEquals(velocity,aPlanet.velocity());
	}

}
