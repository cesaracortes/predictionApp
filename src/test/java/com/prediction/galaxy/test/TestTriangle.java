package com.prediction.galaxy.test;

import java.awt.Point;

import org.junit.Assert;
import org.junit.Test;

import com.galaxy.gemoetrics.Triangle;

public class TestTriangle {
	
	@Test
	public void testInsideTriangle(){
		Point a = new Point(0,0);
		Point b = new Point(3,5);
		Point c = new Point(6,0);
		Triangle t = new Triangle(a,b,c);
		
		Assert.assertTrue(t.contains(new Point(3,2)));

	}
	
	@Test
	public void testOutsideTriangle(){
		Point a = new Point(0,0);
		Point b = new Point(3,5);
		Point c = new Point(6,0);
		Triangle t = new Triangle(a,b,c);
		
		Assert.assertFalse(t.contains(new Point(7,2)));

	}
	
	@Test
	public void testEdgePointIsInsideTriangle(){
		Point a = new Point(0,0);
		Point b = new Point(3,5);
		Point c = new Point(6,0);
		Triangle t = new Triangle(a,b,c);
		
		Assert.assertTrue(t.contains(new Point(3,3)));

	}

}
