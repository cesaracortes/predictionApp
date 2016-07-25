package com.prediction.galaxy;

import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.util.Assert;

public class Line {

	private Point2D p1;
	private Point2D p2;

	public Line(Point2D aPoint, Point2D anothePoint) {
		if (aPoint.getX() < anothePoint.getX()) {
			this.p1 = aPoint;
			this.p2 = anothePoint;
		} else {
			this.p1 = anothePoint;
			this.p2 = aPoint;
		}

	}

	public boolean contains(Set<Point2D> positions) {
		return allHaveTheSameXorYPos(positions) || positions.stream().allMatch(aPoint -> contains(aPoint));

	}

	private boolean allHaveTheSameXorYPos(Set<Point2D> positions) {
		return positions.stream().allMatch(aPoint -> aPoint.getX() == p1.getX() && aPoint.getX() == p2.getX()) ||
		positions.stream().allMatch(aPoint -> aPoint.getY() == p1.getY() && aPoint.getY() == p1.getY());
	}

	private Boolean contains(Point2D aPoint2) {
		return aPoint2.getY() - ((getInclination() * aPoint2.getX() + getConstant()) / getYCoef()) < 0.00001 ; // Error gap to be close to 0
	}

	@Override
	public String toString() {
		Double m = getInclination();
		Double coeficienteY = getYCoef();
		Double b = getConstant();
		return coeficienteY + "y = " + m + "x" + " + " + b;
	}

	private Double getConstant() {
		return (-(p1.getX() * getInclination()) + (p1.getY() * (p2.getX() - p1.getX())));
	}

	public Double getInclination() {
		return  (p2.getY() - p1.getY());

	}
	
	public Double getYCoef(){
		return  (p2.getX() - p1.getX());
	}

	public static boolean areAligned(Set<Point2D> positions) {
		Assert.isTrue(positions.size() >= 2); //Se necesitan al menos dos puntos para definir la recta
		List<Point2D> list = new LinkedList<>(positions);
		Line line = new Line(list.get(0), list.get(1));
		return line.contains(positions);
	}

}
