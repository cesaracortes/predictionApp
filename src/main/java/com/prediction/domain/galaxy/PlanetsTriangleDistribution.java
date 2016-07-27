package com.prediction.domain.galaxy;

public class PlanetsTriangleDistribution implements IPlanetsDistribution {

	@Override
	public WheatherStatus wheatherForGalaxy(Galaxy galaxy) {
		return galaxy.weatherWhenPlanetsTriangle();
	}

}
