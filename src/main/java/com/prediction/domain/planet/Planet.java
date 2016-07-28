package com.prediction.domain.planet;

import com.prediction.domain.exceptions.CreationException;
import com.prediction.domain.galaxy.movement.Velocity;

public class Planet implements IPlanet {

	private String aName;
	private Velocity velocity;

	public Planet(String aName) {
		this.aName = aName;
	}

	public static IPlanet named(String aName) {
		checkName(aName);
		return new Planet(aName);
	}

	private static void checkName(String aName) {
		if(aName == null || aName.equals(""))
			throw new CreationException("A Name for a Planet should not be empty");
	}
	
	

	public String name() {
		return aName;
	}

	public IPlanet withVelocity(Velocity velocity) {
		this.velocity = velocity;
		return this;
	}

	public Velocity velocity() {
		return velocity;
	}

}
