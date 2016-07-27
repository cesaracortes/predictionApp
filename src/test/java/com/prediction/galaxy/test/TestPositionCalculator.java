package com.prediction.galaxy.test;

import java.awt.geom.Point2D;

import org.junit.Assert;
import org.junit.Test;

import com.prediction.domain.galaxy.Distance;
import com.prediction.domain.galaxy.PositionCalculator;
import com.prediction.domain.galaxy.Unit;
import com.prediction.domain.galaxy.Velocity;

public class TestPositionCalculator {
	
	@Test
	public void noVelocityShouldStayInTheSamePosition(){
		
		PositionCalculator calculator = new PositionCalculator();
		Point2D expected = new Point2D.Double(10,0);
		Point2D real = calculator.calculate(Distance.amountWithUnit(10.0,Unit.KM),Velocity.withAmountAndUnit(0.0, Unit.GRADE_PER_DAY), 1, new Point2D.Double(0,0));
		Assert.assertEquals(expected, real);
	}
	
	@Test
	public void withVelocitySholdMove(){
		PositionCalculator calculator = new PositionCalculator();
		Point2D expected = new Point2D.Double(9.96,0.87);
		Point2D real = calculator.calculate(Distance.amountWithUnit(10.0,Unit.KM),Velocity.withAmountAndUnit(5.0, Unit.GRADE_PER_DAY), 1, new Point2D.Double(0,0));
		Assert.assertEquals(expected, real);
	}
	
	@Test
	public void negativeVelocityShouldBePlacedInNegativeYPosition(){
		PositionCalculator calculator = new PositionCalculator();
		Point2D expected = new Point2D.Double(9.96,-0.87);
		Point2D real = calculator.calculate(Distance.amountWithUnit(10.0,Unit.KM),Velocity.withAmountAndUnit(-5.0, Unit.GRADE_PER_DAY), 1, new Point2D.Double(0,0));
		Assert.assertEquals(expected, real);
	}

}
