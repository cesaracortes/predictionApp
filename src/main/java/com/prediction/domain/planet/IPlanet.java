package com.prediction.domain.planet;

import com.prediction.domain.galaxy.movement.Velocity;

public interface IPlanet {

	String name();

	IPlanet withVelocity(Velocity velocity);

	Velocity velocity();

}
