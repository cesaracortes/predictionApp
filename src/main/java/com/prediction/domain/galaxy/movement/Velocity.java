package com.prediction.domain.galaxy.movement;

public class Velocity {

	private Double amount;
	private Unit aUnit;

	private Velocity(Double amount, Unit aUnit) {
		this.amount = amount;
		this.aUnit = aUnit;
	}

	public static Velocity withAmountAndUnit(Double amount, Unit aUnit) {
		return new Velocity(amount,aUnit);
	}

	public Double amount() {
		return amount;
	}

}
