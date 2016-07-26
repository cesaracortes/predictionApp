package com.galaxy.gemoetrics;

import java.awt.geom.Point2D;
import java.util.Set;

public class Triangle implements IGeometricShape {

	private Point2D p1;
	private Point2D p2;
	private Point2D p3;

	public Triangle(Point2D a, Point2D b, Point2D c) {
		this.p1 = a;
		this.p2 = b;
		this.p3 = c;
	}

	@Override
	public Boolean contains(Set<Point2D> points) {

		return  points.stream().allMatch(aPoint -> contains(aPoint));

	}

	@Override
	public Boolean contains(Point2D p) {
		/* Calculate area of triangle ABC */
		double A = area(p1.getX(), p1.getY(), p2.getX(), p2.getY(), p3.getX(), p3.getY());

		/* Calculate area of triangle PBC */
		double A1 = area(p.getX(), p.getY(), p2.getX(), p2.getY(), p3.getX(), p3.getY());

		/* Calculate area of triangle PAC */
		double A2 = area(p1.getX(), p1.getY(), p.getX(), p.getY(), p3.getX(), p3.getY());

		/* Calculate area of triangle PAB */
		double A3 = area(p1.getX(), p1.getY(), p2.getX(), p2.getY(), p.getX(), p.getY());

		/* Check if sum of A1, A2 and A3 is same as A */
		return (A == A1 + A2 + A3);
	}

	double area(double d, double e, double f, double g, double h, double i) {
		return Math.abs((d * (g - i) + f * (i - e) + h * (e - g)) / 2.0);
	}

}