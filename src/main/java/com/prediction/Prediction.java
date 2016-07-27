package com.prediction;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.prediction.domain.galaxy.IGalaxy;

public class Prediction {

	public static Wheather forDay(Integer aDayNumber, IGalaxy aGalaxy) {
		if (aGalaxy.allAreAlignedToSunAtDay(aDayNumber)) {
			return new Wheather(aDayNumber, 0.0, WheatherType.DRY);
		} else if (aGalaxy.sunIsInsidePlanetsTriangleAtDay(aDayNumber)) {
			Double intensity = aGalaxy.perimeterInThatDay(aDayNumber);
			return new Wheather(aDayNumber, intensity, WheatherType.RAIN);
		} else if (aGalaxy.arePlanetsAlignedAtDay(aDayNumber)) {
			return new Wheather(aDayNumber, 0.0, WheatherType.GOOD);
		} else {
			return new Wheather(aDayNumber, 0.0, WheatherType.NORMAL);
		}

	}


	public static List<Wheather> predictionsUntil(IGalaxy aGalaxy, int totalDays) {
		List<Wheather> predictions = new LinkedList<Wheather>();
		for (int dayNumber = 1; dayNumber < totalDays; dayNumber++) {
			predictions.add(Prediction.forDay(dayNumber, aGalaxy));
		}

		Stream<Wheather> rainyWheather = predictions.stream().filter(aWheather -> aWheather.isRainy());
		List<Wheather> collect = rainyWheather.collect(Collectors.toList());
		Optional<Wheather> max = collect.stream()	.max((a1, a2) -> a1.compareTo(a2));

		for (Wheather iWheather : collect) {
			if(iWheather.getIntensity().equals(max)){
				iWheather.makeHigh();
			}
		}
		return predictions;
	}

}
