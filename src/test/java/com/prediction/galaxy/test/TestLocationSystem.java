package com.prediction.galaxy.test;

import static org.junit.Assert.assertTrue;

import java.awt.geom.Point2D;

import org.junit.Test;

import com.prediction.domain.galaxy.location.IUbicationSystem;
import com.prediction.domain.galaxy.location.UbicationSystem;

public class TestLocationSystem {
	
	@Test
	public void testPointsWithSameXValueAreAligned(){
		IUbicationSystem<Integer> system= new UbicationSystem<Integer>();
		system.put(10, new Point2D.Double(0,10));
		system.put(5,new Point2D.Double(0,0));
		assertTrue(system.arePointsAligned());
	}
	
	@Test
	public void testPointsWithSameYValueAreAligned(){
		IUbicationSystem<Integer> system= new UbicationSystem<Integer>();
		system.put(10, new Point2D.Double(-100,10));
		system.put(5,new Point2D.Double(3,10));
		assertTrue(system.arePointsAligned());
	}



}
