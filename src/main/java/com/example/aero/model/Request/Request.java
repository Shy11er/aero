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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "plane_id")
    @JsonIgnoreProperties("request")
    private Plane plane;

    @OneToOne
    @JoinColumn(name = "polosa_id")
    private Polosa polosa;

    public enum RequestType {
        Vzlet,
        Posadka
    }

    public Request(){}
}
