package com.prediction.domain.galaxy;

import com.prediction.domain.galaxy.location.IPlanetsDistribution;
import com.prediction.wheather.WheatherStatus;

public class PlanetsTriangleWithSunDistribution implements IPlanetsDistribution {

	@Override
	public WheatherStatus wheatherForGalaxy(Galaxy galaxy) {
		return galaxy.weatherWhenPlanetsTriangleWithSun();
	}

	@Override
	public Boolean arePlanetsAligned() {
		return false;
	}

	@Override
	public Boolean arePlanetsAlignedToSun() {
		return false;
	}

	@Override
	public Boolean isSunInsidePlanetsTriangle() {
		// TODO Auto-generated method stub
		return true;
	}

}
