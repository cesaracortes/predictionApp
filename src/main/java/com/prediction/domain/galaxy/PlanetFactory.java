package com.prediction.domain.galaxy;

import com.prediction.domain.planet.IPlanet;
import com.prediction.domain.planet.Planet;

public class PlanetFactory {

	public static IPlanet createVulcano() {
		Velocity velocity = Velocity.withAmountAndUnit(-5.0, Unit.GRADE_PER_DAY);
		return Planet.named("Vulcano").withVelocity(velocity);
	}

	public static IPlanet createFerengi() {
		Velocity velocity = Velocity.withAmountAndUnit(1.0, Unit.GRADE_PER_DAY);
		return Planet.named("Ferengi").withVelocity(velocity);
	}

	public static IPlanet createBetasoide() {
		Velocity velocity = Velocity.withAmountAndUnit(3.0, Unit.GRADE_PER_DAY);
		return Planet.named("Betasoide").withVelocity(velocity);
	}

}
