package com.prediction.domain.galaxy;

public class Distance {

	private Double amount;

	public Double amount() {
		return amount;
	}

	private Unit aUnit;

	private Distance(Double amount, Unit aUnit) {
		this.amount = Math.abs(amount);
		this.aUnit = aUnit;
	}

	public static Distance amountWithUnit(Double amount, Unit aUnit) {
		return new Distance(amount, aUnit);
	}

	@Override
	public String toString() {
		return amount + aUnit.toString();
	}

	@Override
	public boolean equals(Object obj) {
		try {
			Distance aDistance = (Distance) obj;
			return amount.equals(aDistance.amount) && aUnit.equals(aDistance.aUnit);
		} catch (Exception e) {
			return false;
		}
	}

}
