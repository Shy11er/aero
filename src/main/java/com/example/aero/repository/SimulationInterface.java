package com.example.aero.repository;

import com.example.aero.DTO.SimulationDto;

public interface SimulationInterface {
    void start();
    void init(SimulationDto simulationDto);
}
