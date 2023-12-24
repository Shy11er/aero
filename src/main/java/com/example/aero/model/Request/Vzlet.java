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
public class Vzlet extends Request{
    private LocalDateTime departure;

    public Vzlet() {
        super();
    }

    public LocalDateTime getDeparture() {
        return departure;
    }
    public void setDeparture(LocalDateTime departure) {
        this.departure = departure;
    }
}
