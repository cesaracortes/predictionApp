package com.example.hellorest;

import javax.inject.Inject;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.prediction.galaxy.Galaxy;
import com.prediction.galaxy.IGalaxy;
import com.prediction.galaxy.PlanetFactory;
import com.prediction.planet.IPlanet;

@Component
public class PredictionJob  implements ApplicationListener<ApplicationReadyEvent>{

	@Inject
	private PredictionRespository repo;

	public  void execute() {
		int totalDays = 365*10;
		IGalaxy aGalaxy = createGalaxyWithPlanets();
		for (int dayNumber = 1; dayNumber < totalDays ; dayNumber++) {
			repo.save(Prediction.forDay(dayNumber, aGalaxy));
		}
		
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
