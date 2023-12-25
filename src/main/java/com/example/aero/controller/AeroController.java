package com.example.aero.controller;

import com.example.aero.service.AeroService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


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
        System.out.println("DONE");
    }
}
