package com.example.aero.model.Request;

import com.example.aero.model.Plane.Plane;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Entity
public class Posadka extends Request{
    private LocalDateTime arrival;

    public Posadka() {
        super();
    }

    public void setArrival(LocalDateTime arrival) {
        this.arrival = arrival;
    }

    public LocalDateTime getArrival() {
        return arrival;
    }
}
