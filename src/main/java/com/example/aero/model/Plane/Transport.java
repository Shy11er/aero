package com.example.aero.model.Plane;

import com.example.aero.model.Request.Request;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Entity
public class Transport extends Plane {
    private Long cargo_amount;

    public Transport() {
        super(); // provide default values or adjust as needed
    }

}
