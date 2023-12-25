package com.example.aero.model;

import com.example.aero.model.Request.Request;
import jakarta.persistence.Entity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Raspisanie {
    private List<Request> requests = new ArrayList<>();

    public Raspisanie(List<Request> requests) {
        this.requests = requests;
    }
}
