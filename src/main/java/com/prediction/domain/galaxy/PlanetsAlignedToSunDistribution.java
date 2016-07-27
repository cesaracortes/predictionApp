package com.prediction.domain.galaxy;

public class PlanetsAlignedToSunDistribution implements IPlanetsDistribution {

	@Override
	public WheatherStatus wheatherForGalaxy(Galaxy galaxy) {
		return galaxy.weatherWhenPlanetsAlignedToSun();
	}

}
