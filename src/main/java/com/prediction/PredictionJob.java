package com.prediction;

import java.util.List;

import javax.inject.Inject;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.prediction.domain.galaxy.Galaxy;
import com.prediction.domain.galaxy.IGalaxy;
import com.prediction.domain.galaxy.PlanetFactory;
import com.prediction.domain.planet.IPlanet;
import com.prediction.repostory.PredictionRespository;

@Component
public class PredictionJob  implements ApplicationListener<ApplicationReadyEvent>{

	@Inject
	private PredictionRespository repo;

	public  void execute() {
		int totalDays = 365*10;
		IGalaxy aGalaxy = createGalaxyWithPlanets();
		List<WheatherReport> predictions = Prediction.predictionsUntil(aGalaxy,totalDays);
		repo.save(predictions);
		
		
	}

	private  IGalaxy createGalaxyWithPlanets() {
		final IGalaxy aGalaxy = Galaxy.bigBang();
		IPlanet vulcano = PlanetFactory.createVulcano();
		aGalaxy.addPlanet(vulcano, 1000);
		IPlanet ferengi = PlanetFactory.createFerengi();
		aGalaxy.addPlanet(ferengi, 500);
		IPlanet betasoide = PlanetFactory.createBetasoide();
		aGalaxy.addPlanet(betasoide, 2000);
		return aGalaxy;
	}

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		this.execute();
	}

}
