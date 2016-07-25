package com.prediction.planet;

import com.prediction.galaxy.Velocity;

public interface IPlanet {

	String name();

	IPlanet withVelocity(Velocity velocity);

	Velocity velocity();

}
