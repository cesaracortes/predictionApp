package com.prediction.galaxy.test;

import static org.junit.Assert.*;

import java.awt.geom.Line2D;

import org.junit.Test;

import com.prediction.galaxy.CartesianCoordinateSystem;
import com.prediction.galaxy.CoordinateSystem;

public class TestCoordinateSystem {
	
	@Test
	public void testPointsWithSameXValueAreAligned(){
		CoordinateSystem system= new CartesianCoordinateSystem();
		system.addPoint(5.0,3.0);
		system.addPoint(5.0,0.0);
		assertTrue(system.pointsAreAligned());
	}
	
	@Test
	public void testPointsWithSameYValueAreAligned(){
		CoordinateSystem system= new CartesianCoordinateSystem();
		system.addPoint(5.0,3.0);
		system.addPoint(2.0,3.0);
		assertTrue(system.pointsAreAligned());
	}



}
