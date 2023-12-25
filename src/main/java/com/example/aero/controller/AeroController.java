package com.example.aero.controller;

import com.example.aero.model.Otchet;
import com.example.aero.service.AeroService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


@RestController
//@AllArgsConstructor
public class AeroController {
    private final AeroService aeroService;

    @Autowired
    public AeroController(AeroService aeroService) {
        this.aeroService = aeroService;
    }

    @GetMapping("/aero")
    public void aero(){
        aeroService.init();
        aeroService.work();
    }

    @GetMapping("/aero/otchet")
    public ResponseEntity<Otchet> otchet(){
        Otchet otchet =  aeroService.get();
            return new ResponseEntity<>(otchet, HttpStatus.OK);
    }
}
