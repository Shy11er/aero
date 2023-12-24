package com.example.aero.DTO;

import lombok.Data;

@Data
public class SimulationDto {
    private long step;
    private long time;
    private long min_range;
    private long max_range;
    private long planeSpawn;

}
