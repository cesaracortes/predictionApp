package com.prediction.domain.galaxy.location;

import java.awt.geom.Point2D;

import com.prediction.domain.galaxy.movement.Distance;

/**
 * Representa un sistema de ubicacion en 2D, algo similar a un plano Cartesiano
 * @author cesar.cortes
 *
 * @param <T>
 */
public interface IUbicationSystem<T> {

	void put(T element, Point2D place);

	Distance distanceFromCenter(T element);

	Boolean arePointsAligned();

	Boolean pointsAreAlignedToTheCenter();

	
	Boolean isCenterInsidePoints();

	Point2D positionFor(T element);

	/**
	 * Calcula el perimetro generado por los puntos
	 * @return
	 */
	double perimeteForPoints();

	/**
	 * Mueve un elemento de un punto hacia uno nuevo
	 * @param aPlanet
	 * @param previousPosition
	 * @param newPlace
	 */
	void moveFromTo(T aPlanet, Point2D previousPosition, Point2D newPlace);

	/**
	 * La distribución o ubicación de los puntos en el plano
	 * @return
	 */
	UbicationDistribution distribution();
	

}
