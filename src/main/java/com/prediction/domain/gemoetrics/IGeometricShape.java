package com.prediction.domain.gemoetrics;

import java.awt.geom.Point2D;
import java.util.Set;

public interface IGeometricShape {

	Boolean contains(Set<Point2D> points);
	
	Boolean contains(Point2D points);

}
