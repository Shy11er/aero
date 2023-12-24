package com.example.aero.model.Plane;

import com.example.aero.model.Request.Request;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Entity
@Table(name = "plane")
@Data
public class Plane {
    @Id
    @GeneratedValue
    private Long id;
    private PlaneType planeType;
    private String company;
    private String title;
    private Long time_to_fly;
    private Long passager_amount;
    private Long cargo_amount;

    @OneToMany(mappedBy = "plane", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Request> requests;

    public enum PlaneType {
        Airliner,
        Transport
    }

    public Plane() {}

}
