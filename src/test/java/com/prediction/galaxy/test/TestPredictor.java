package com.prediction.galaxy.test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.mockito.Mockito;

import com.example.hellorest.Prediction;
import com.prediction.galaxy.Galaxy;
import com.prediction.galaxy.IGalaxy;
import com.prediction.galaxy.IPrediction;

public class TestPredictor {
	
	
	@Test
	public void testDroughtPeriod() {
		IGalaxy aGalaxy = Mockito.mock(Galaxy.class);
		Mockito.when(aGalaxy.allAreAlignedToSunAtDay(0)).thenReturn(true);
		IPrediction prediction = Prediction.forDay(0,aGalaxy);
		assertTrue(prediction.isADryDay());
		assertEquals(new Integer(0),prediction.isForDay());
	}

	@Test
	public void whenGalaxyIsNotAlignedHaveANormalDay() {
		IGalaxy aGalaxy = Mockito.mock(Galaxy.class);
		Mockito.when(aGalaxy.allAreAlignedToSunAtDay(4)).thenReturn(false);
		IPrediction prediction = Prediction.forDay(4,aGalaxy);
		assertTrue(prediction.isANormalDay());
		assertEquals(new Integer(4),prediction.isForDay());
	}

	@Test
	public void whenSunIsInsidePlanetsShouldRain() {
		IGalaxy aGalaxy = Mockito.mock(Galaxy.class);
		Mockito.when(aGalaxy.sunIsInsidePlanetsTriangleAtDay(5)).thenReturn(true);
		IPrediction prediction = Prediction.forDay(5,aGalaxy);
		assertTrue(prediction.isARainyDay());
		assertEquals(new Integer(5),prediction.isForDay());
	}
	

}
