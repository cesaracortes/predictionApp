package com.prediction.domain.galaxy;

import com.prediction.domain.galaxy.location.IPlanetsDistribution;
import com.prediction.wheather.WheatherStatus;

public class PlanetsAlignedDistribution implements IPlanetsDistribution {

	@Override
	public WheatherStatus wheatherForGalaxy(Galaxy galaxy) {
		return galaxy.weatherWhenPlanetsAligned();
	}

	@Override
	public Boolean arePlanetsAligned() {
		return true;
	}

	@Override
	public Boolean arePlanetsAlignedToSun() {
		return false;
	}

	@Override
	public Boolean isSunInsidePlanetsTriangle() {
		return false;
	}

}
