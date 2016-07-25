package com.prediction.galaxy.test;

import java.awt.geom.Point2D;

import org.junit.Test;

import com.prediction.galaxy.Line;

public class TestLine {
	
	
	
	@Test
	public void testEcuation() {
		Line aLine = new Line(new Point2D.Double(0,2), new Point2D.Double(0,4));
		
		System.out.println(aLine.toString());
	};

}
