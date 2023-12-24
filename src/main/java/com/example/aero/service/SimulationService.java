package com.example.aero.service;

import com.example.aero.DTO.PlaneDto;
import com.example.aero.DTO.RequestDto;
import com.example.aero.DTO.SimulationDto;
import com.example.aero.model.Plane.Plane;
import com.example.aero.model.Request.Request;
import com.example.aero.repository.PlaneRepository;
import com.example.aero.repository.PolosaRepository;
import com.example.aero.repository.SimulationInterface;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
//@AllArgsConstructor
public class SimulationService implements SimulationInterface {
    private final PolosaRepository polosaRepository;
    private final PlaneRepository planeRepository;
    private final RequestService requestService;

    private final PlaneService planeService;


    private boolean isRunning = false;
    private long step;
    private long min_range;
    private long max_range;
    private long planeSpawn;
    private LocalDateTime now;
    private LocalDateTime finalNow;
    private List<String> plane_title = Arrays.asList("Skyhawk X", "Thunderstrike Voyager", "Eaglewing Elite", "StellarJet Starlight", "Falcon Express", "AeroVista Horizon", "Vortex Voyager", "StratoLancer Swift", "Orion Odyssey", "Phoenix Comet", "GalaxyGlider Spectra", "CelestialHawk Nova", "Aurora Express", "SwiftWing Thunder", "JetStream Pinnacle", "StarCruiser Velocity", "SolarFlare Falcon", "InfiniteSky Nomad", "WindDancer Horizon", "Firestorm Voyager");
    private List<String> company_title = Arrays.asList("SkyWings International", "AeroLink Express", "GlobalJet Ventures", "Horizon Airlines", "EagleAir Connect", "StellarSky Flights", "AirVista Express", "TransGlobe Airways", "PacificFly Travels", "StarCruise Airlines", "Skyline Wings", "RoyalAir Expeditions", "InfiniteSkies Airlines", "Aurora Airways", "VelocityJet Ventures", "CrownWings Airlines", "Phoenix Air Connect", "CelestialHawk Flights", "WindDancer Airways", "Panorama Sky Travels");

    public SimulationService(PolosaRepository polosaRepository, PlaneRepository planeRepository, RequestService requestService, PlaneService planeService) {
        this.polosaRepository = polosaRepository;
        this.planeRepository = planeRepository;
        this.requestService = requestService;
        this.planeService = planeService;
    }

    @Override
    @Scheduled(fixedRate = 1)
    public void start() {
        if(isRunning) {

            for (int i = 0; i < planeSpawn; i++) {
                // САМОЛЕТ
                int randPlaneTitle = new Random().nextInt(plane_title.size());
                int randCompanyTitle = new Random().nextInt(company_title.size());
                int randPlaneType = new Random().nextInt(2);
                String planeType, requestType;

                if (randPlaneType == 0) {
                    planeType = "Airliner";
                } else {
                    planeType = "Transport";
                }

                PlaneDto planeDto = new PlaneDto(
                    planeType,
                    company_title.get(randCompanyTitle),
                    (long)(new Random().nextInt(5)+2),
                    plane_title.get(randPlaneTitle),
                    (long)new Random().nextInt(80) + 80,
                    (long)new Random().nextInt(500) + 500
                );

                Plane plane = planeService.create(planeDto);

                // ЗАЯВКА

                int randRequestType = new Random().nextInt(2);
                int randArr = new Random().nextInt(10);
                LocalDateTime arrival = now, departure = now;
                boolean time_changed = false;

                if (randRequestType == 0) {
                    requestType = "Vzlet";
                } else {
                    requestType = "Posadka";
                }

                if (randArr == 9) {
                    arrival.plusMinutes(new Random().nextInt((int)max_range) + min_range);
                    time_changed = true;
                    departure.plusMinutes(new Random().nextInt((int)max_range) + min_range);
                } else if (randArr == 0) {
                    time_changed = true;
                    arrival.minusMinutes(new Random().nextInt((int)max_range) + min_range);
                }

                RequestDto requestDto = new RequestDto(
                        plane.getId(),
                        arrival,
                        departure,
                        requestType,
                        time_changed
                );
                Request request = requestService.create(requestDto);
            }

            now = now.plusMinutes(step);
            System.out.println(now);
            if (now.compareTo(finalNow) >= 0) {
                isRunning = false;
                System.out.println("Все");
            }
        }
    }

    @Override
//    public void init(long step, long time, long min_range, long max_range, long planeCount, long planeSpawn) {
    public void init(SimulationDto simulationDto) {
        isRunning = true;
        this.step = simulationDto.getStep();
        this.max_range = simulationDto.getMax_range();
        this.min_range = simulationDto.getMin_range();
        this.planeSpawn = simulationDto.getPlaneSpawn();
        LocalDateTime date = LocalDateTime.now();
        this.now = LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(), (int) simulationDto.getTime(), 0);
        this.finalNow = now.plusDays(1);
    }
}
