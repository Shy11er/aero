package com.example.aero.controller;

import com.example.aero.DTO.SimulationDto;
import com.example.aero.service.SimulationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class SimulationController {
    private final SimulationService simulationService;

    @GetMapping("/simulation/start")
    public String startSimulation(@RequestBody SimulationDto simulationDto) {
        simulationService.init(simulationDto);
        return "Симуляция началась";
    }
}
