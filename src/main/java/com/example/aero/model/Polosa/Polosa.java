package com.example.aero.model.Polosa;

import com.example.aero.model.Plane.Plane;
import com.example.aero.model.Request.Request;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "polosa")
@Data
public class Polosa {
    @GeneratedValue
    @Id
    private Long id;
    private Boolean isBusy;
    private PolosaType polosaType;

    @OneToOne(mappedBy = "polosa", cascade = CascadeType.ALL)
    private Request request;

    public enum PolosaType {
        Vzletnaya,
        Posadochnaya
    }

    public Polosa() {}
}
