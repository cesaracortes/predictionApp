package com.prediction.domain.galaxy;

public class PlanetsAlignedDistribution implements IPlanetsDistribution {

	@Override
	public WheatherStatus wheatherForGalaxy(Galaxy galaxy) {
		return galaxy.weatherWhenPlanetsAligned();
	}

}
