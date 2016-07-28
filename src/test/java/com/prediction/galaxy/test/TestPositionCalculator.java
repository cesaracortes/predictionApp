package com.prediction.galaxy.test;

import java.awt.geom.Point2D;

import org.junit.Assert;
import org.junit.Test;

import com.prediction.domain.galaxy.PolarToCartesianPositionCalculator;
import com.prediction.domain.galaxy.location.IPositionCalculator;
import com.prediction.domain.galaxy.movement.Distance;
import com.prediction.domain.galaxy.movement.Unit;
import com.prediction.domain.galaxy.movement.Velocity;

public class TestPositionCalculator {
	
	@Test
	public void noVelocityShouldStayInTheSamePosition(){
		
		IPositionCalculator calculator = new PolarToCartesianPositionCalculator();
		Point2D expected = new Point2D.Double(10,0);
		Point2D real = calculator.calculate(Distance.amountWithUnit(10.0,Unit.KM),Velocity.withAmountAndUnit(0.0, Unit.GRADE_PER_DAY), 1);
		Assert.assertEquals(expected, real);
	}
	
	@Test
	public void withVelocitySholdMove(){
		IPositionCalculator calculator = new PolarToCartesianPositionCalculator();
		Point2D expected = new Point2D.Double(9.96,0.87);
		Point2D real = calculator.calculate(Distance.amountWithUnit(10.0,Unit.KM),Velocity.withAmountAndUnit(5.0, Unit.GRADE_PER_DAY), 1);
		Assert.assertEquals(expected, real);
	}
	
	@Test
	public void negativeVelocityShouldBePlacedInNegativeYPosition(){
		IPositionCalculator calculator = new PolarToCartesianPositionCalculator();
		Point2D expected = new Point2D.Double(9.96,-0.87);
		Point2D real = calculator.calculate(Distance.amountWithUnit(10.0,Unit.KM),Velocity.withAmountAndUnit(-5.0, Unit.GRADE_PER_DAY), 1);
		Assert.assertEquals(expected, real);
	}

}
