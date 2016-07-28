package com.prediction.domain.galaxy;

import com.prediction.domain.galaxy.location.IPlanetsDistribution;
import com.prediction.wheather.WheatherStatus;

public class PlanetsAlignedToSunDistribution implements IPlanetsDistribution {

	@Override
	public WheatherStatus wheatherForGalaxy(Galaxy galaxy) {
		return galaxy.weatherWhenPlanetsAlignedToSun();
	}

	@Override
	public Boolean arePlanetsAligned() {
		return true;
	}

	@Override
	public Boolean arePlanetsAlignedToSun() {
		return true;
	}

	@Override
	public Boolean isSunInsidePlanetsTriangle() {
		return false;
	}

}
