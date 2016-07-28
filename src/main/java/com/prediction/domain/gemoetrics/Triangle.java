package com.prediction.domain.gemoetrics;

import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.prediction.domain.galaxy.movement.RoundUtils;

public class Triangle implements IGeometricShape {

	private Point2D p1;
	private Point2D p2;
	private Point2D p3;

	public Triangle(Point2D a, Point2D b, Point2D c) {
		this.p1 = a;
		this.p2 = b;
		this.p3 = c;
	}

	public Triangle(Set<Point2D> positions) {
		List<Point2D> list = new LinkedList<>(positions);
		this.p1 = list.get(0);
		this.p2 = list.get(1);
		this.p3 = list.get(3);
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

	public double perimeter() {
		double a = RoundUtils.round(p1.distance(p2));
		double b = RoundUtils.round(p2.distance(p3));
		double c = RoundUtils.round(p3.distance(p1));
		return RoundUtils.round(a + b + c);
	}

}
