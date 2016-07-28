package com.prediction.domain.galaxy;

import java.awt.geom.Point2D;

import com.prediction.domain.galaxy.movement.Distance;
import com.prediction.domain.planet.IPlanet;
import com.prediction.wheather.WheatherStatus;


public interface IGalaxy {

	public boolean hasPlanets();

	public void addPlanet(IPlanet aPlanet, Integer distanceFromSun);

	/**
	 * 
	 * @param dayNumber
	 * @param vulcano
	 * @return La distancia viajada por un planeta en N dias.
	 */
	public Distance distanceTraveledAtDay(Integer dayNumber, IPlanet vulcano);

	/**
	 * @return true si los planetas están alineados el dia de hoy
	 */
	public Boolean arePlanetsAlignedAtDay();

	/**
	 * 
	 * @param 
	 * @return true si los planetas estan alineados con el sol el dia de hoy
	 */
	public Boolean allAreAlignedToSunAtDay();

	/**
	 * 
	 * @param dayNumber
	 * @param vulcano
	 * @return La posicion del planeta el dia de hoy
	 */
	public Point2D positionAtDay(IPlanet vulcano);

	/**
	 * 
	 * @param aDayNumber
	 * @return true si el sol está adentro del triangulo formado por los planetas el dia de hoy
	 */
	public Boolean sunIsInsidePlanetsTriangleAtDay();

	/**
	 * 
	 * @param aPlanet
	 * @return La distancia hacia la posicion del sol.
	 */
	public Distance distanceFromSun(IPlanet aPlanet);

	/**
	 * Mueve los planetas un dia segun la orbita que cada uno realiza
	 */
	public void movePlanets();

	/**
	 * Devuelve el estado del Clima en la Galaxia en el momento actual;
	 * @return
	 */
	public WheatherStatus currentWeather();



}
