package com.example.aero.model.Polosa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Entity
public class Posadochnaya extends Polosa {
    private String covering;
    public Posadochnaya() {
        super();
    }

    public String getCovering() {
        return covering;
    }
    public void setCovering(String covering) {
        this.covering = covering;
    }
}
