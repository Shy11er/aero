package com.example.aero.controller;

import com.example.aero.model.Raspisanie;
import com.example.aero.model.Request.Request;
import com.example.aero.repository.RequestRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class RaspisanieController {
    private final RequestRepository requestRepository;

    @GetMapping("/raspisanie")
    public ResponseEntity<Raspisanie> findAll() {
        List<Request> requests = requestRepository.findAll();
        Raspisanie raspisanie = new Raspisanie(requests);
        return new ResponseEntity<>(raspisanie, HttpStatus.OK);
    }
}
