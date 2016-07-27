package com.prediction.domain.galaxy;

public class PlanetsTriangleWithSunDistribution implements IPlanetsDistribution {

	@Override
	public WheatherStatus wheatherForGalaxy(Galaxy galaxy) {
		return galaxy.weatherWhenPlanetsTriangleWithSun();
	}

}
