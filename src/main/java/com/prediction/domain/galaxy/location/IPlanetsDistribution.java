package com.prediction.domain.galaxy.location;

import com.prediction.domain.galaxy.Galaxy;
import com.prediction.wheather.WheatherStatus;

/**
 * Representa las posibles distribuciones esperadas de los planetas en una Galaxia
 * @author cesar.cortes
 *
 */
public interface IPlanetsDistribution {

	public WheatherStatus wheatherForGalaxy(Galaxy galaxy);

	public Boolean arePlanetsAligned();

	public Boolean arePlanetsAlignedToSun();

	public Boolean isSunInsidePlanetsTriangle();


}
