package com.example.aero.controller;

import com.example.aero.DTO.PlaneDto;
import com.example.aero.model.Plane.Plane;
import com.example.aero.repository.PlaneRepository;
import com.example.aero.service.PlaneService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class PlaneController {
    private final PlaneService planeService;

    @DeleteMapping("/plane/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
       planeService.delete(id);
    }

    @PutMapping("/plane/update/{id}")
    public void update(@PathVariable("id") Long id, @RequestBody Plane plane) {
        planeService.update(plane, id);
    }

    @GetMapping("/plane/findAll")
    public ResponseEntity<List<Plane>> findAll() {
        List<Plane> planes = planeService.findAll();
        return new ResponseEntity<>(planes, HttpStatus.OK);
    }

    @PostMapping("/plane/create")
    public ResponseEntity<Plane> create(@RequestBody PlaneDto dto) {
        Plane plane = planeService.create(dto);
        return new ResponseEntity<>(plane, HttpStatus.CREATED);
    }
}
