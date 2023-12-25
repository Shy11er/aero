package com.example.aero.model;

import lombok.Data;

@Data
public class Otchet {
    private long amountRequest;
    private long maxDelay;
    private double averDelay;
    private long maxQueueSize;
    private double averQueueSize;
    private double averPolosa;
}
