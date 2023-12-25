package com.example.aero.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Otchet {
    private long amountRequest = 0; //
    private long maxDelay = 0;
    private double averDelay = 0;
    private long maxQueueSize = 0; //
    private double averQueueSize = 0; //
    private double averPolosa = 0; //

    public Otchet(){}
}
