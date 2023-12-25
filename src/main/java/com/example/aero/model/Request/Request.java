package com.example.aero.model.Request;

import com.example.aero.model.Plane.Plane;
import com.example.aero.model.Polosa.Polosa;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Table(name = "request")
@Entity
@Data
public class Request {
    @Id
    @GeneratedValue
    private Long id;
    private Boolean time_changed;
    private RequestType requestType;
    private String status;
    private LocalDateTime start;
    private LocalDateTime finish;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "plane_id")
    @JsonIgnoreProperties("request")
    private Plane plane;

    @OneToOne
    @JoinColumn(name = "polosa_id")
    private Polosa polosa;

    public boolean isCompatible(Polosa polosa) {
        if (polosa.getPolosaType() == Polosa.PolosaType.Vzletnaya && requestType == RequestType.Vzlet) {
            return true;
        } else if (polosa.getPolosaType() == Polosa.PolosaType.Posadochnaya && requestType == RequestType.Posadka) {
            return true;
        }
        return false;
    }

    public boolean isReady(Request request, LocalDateTime currentTime) {
        if (request.getStatus().equals("Waiting")) {
            if (request instanceof Vzlet) {
                if (currentTime.compareTo(((Vzlet) request).getDeparture()) >= 0) {
                    return true;
                }
            }
            if (request instanceof Posadka) {
                if (currentTime.compareTo(((Posadka) request).getArrival()) >= 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public enum RequestType {
        Vzlet,
        Posadka
    }

    public Request(){}
}
