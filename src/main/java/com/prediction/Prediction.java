package com.prediction;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.prediction.domain.galaxy.IGalaxy;

public class Prediction {

	public static WheatherReport forDay(Integer aDayNumber, IGalaxy aGalaxy) {
		if (aGalaxy.allAreAlignedToSunAtDay(aDayNumber)) {
			return new WheatherReport(aDayNumber, 0.0, WheatherType.DRY);
		} else if (aGalaxy.sunIsInsidePlanetsTriangleAtDay(aDayNumber)) {
			Double intensity = aGalaxy.perimeterAtDay(aDayNumber);
			return new WheatherReport(aDayNumber, intensity, WheatherType.RAIN);
		} else if (aGalaxy.arePlanetsAlignedAtDay(aDayNumber)) {
			return new WheatherReport(aDayNumber, 0.0, WheatherType.GOOD);
		} else {
			return new WheatherReport(aDayNumber, 0.0, WheatherType.NORMAL);
		}

	}


	public static List<WheatherReport> predictionsUntil(IGalaxy aGalaxy, int totalDays) {
		List<WheatherReport> predictions = new LinkedList<WheatherReport>();
		for (int dayNumber = 1; dayNumber < totalDays; dayNumber++) {
			predictions.add(Prediction.forDay(dayNumber, aGalaxy));
		}

		Stream<WheatherReport> rainyWheather = predictions.stream().filter(aWheather -> aWheather.isRainy());
		List<WheatherReport> collect = rainyWheather.collect(Collectors.toList());
		Optional<WheatherReport> max = collect.stream().max((a1, a2) -> a1.compareTo(a2));


		for (WheatherReport iWheather : collect) {
			if(iWheather.getIntensity().equals(max.get().getIntensity())){
				iWheather.makeHigh();
			}
		}
		return predictions;
	}

}
