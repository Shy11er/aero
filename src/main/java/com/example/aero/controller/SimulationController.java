package com.example.aero.controller;

import com.example.aero.DTO.SimulationDto;
import com.example.aero.service.SimulationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class SimulationController {
    private final SimulationService simulationService;

    @PostMapping("/simulation/start")
    public String startSimulation(@RequestBody SimulationDto simulationDto) {
        simulationService.init(simulationDto);
        return "Симуляция началась";
    }
}
