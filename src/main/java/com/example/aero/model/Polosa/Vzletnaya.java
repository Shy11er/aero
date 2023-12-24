package com.example.aero.model.Polosa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Entity
public class Vzletnaya extends Polosa {
    private String covering;

    public Vzletnaya() {
        super();
    }

    public void setCovering(String covering) {
        this.covering = covering;
    }
    public String getCovering() {
        return covering;
    }
}
