package com.prediction;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.prediction.domain.galaxy.IGalaxy;
import com.prediction.domain.galaxy.location.IPlanetsDistribution;
import com.prediction.domain.gemoetrics.IGeometricShape;
import com.prediction.wheather.WheatherIntensityType;
import com.prediction.wheather.WheatherReport;
import com.prediction.wheather.WheatherStatus;
import com.prediction.wheather.WheatherType;

public class PredictionReporter {
	
	/**
	 * Predice el estado del Clima en la galaxia para el dia actual
	 * @param aDayNumber
	 * @param aGalaxy
	 * @return
	 */
	public WheatherStatus wheatherForCurrentDay(IGalaxy aGalaxy) {
		aGalaxy.movePlanets();
		return aGalaxy.currentWeather();
	}


	/**
	 * Realiza las predicciones sobre la Galaxia hasta el dia que se le indica
	 * @param aGalaxy
	 * @param totalDays
	 * @return
	 */
	public static List<WheatherReport> predictionsUntil(IGalaxy aGalaxy, int totalDays) {
		List<WheatherStatus> predictions = new LinkedList<WheatherStatus>();
		PredictionReporter reporter = new PredictionReporter();
		for (int dayNumber = 1; dayNumber < totalDays; dayNumber++) {
			predictions.add(reporter.wheatherForCurrentDay(aGalaxy));
		}
		return reporter.makeReports(predictions);
	}



	/**
	 * Crea el reporte final sobre el estado del clima en la Galaxia
	 * @param predictions
	 * @param max
	 * @return
	 */
	private  List<WheatherReport> makeReports(List<WheatherStatus> predictions) {
		WheatherStatus max = getMaximunIntesity(predictions);
		List<WheatherReport> reports = new LinkedList<WheatherReport>();
		for (WheatherStatus iWheather : predictions) {
			if(iWheather.getIntensity().equals(max.getIntensity())){
				reports.add(new WheatherReport(iWheather.getDayNumber() , iWheather.getType() , WheatherIntensityType.HIGH));
			}else{
				reports.add(new WheatherReport(iWheather.getDayNumber() , iWheather.getType() ,  WheatherIntensityType.NORMAL));
			}
		}
		return reports;
	}


	/**
	 * Devuelve la maxima intesidad para un dia lluvioso
	 * @param predictions
	 * @return
	 */
	private  WheatherStatus getMaximunIntesity(List<WheatherStatus> predictions) {
		Stream<WheatherStatus> rainyWheather = predictions.stream().filter(aWheather -> aWheather.isRainy());
		List<WheatherStatus> collect = rainyWheather.collect(Collectors.toList());
		Optional<WheatherStatus> max = collect.stream().max((a1, a2) -> a1.compareTo(a2));
		return max.get();
	}

}
