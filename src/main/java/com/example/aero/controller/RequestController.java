package com.example.aero.controller;

import com.example.aero.DTO.RequestDto;
import com.example.aero.model.Plane.Plane;
import com.example.aero.model.Request.Request;
import com.example.aero.service.PlaneService;
import com.example.aero.service.RequestService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("http://localhost:5173")
public class RequestController {
    private final RequestService requestService;

    @DeleteMapping("/request/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        requestService.delete(id);
    }

    @PatchMapping("/request/update/{id}")
    public void update(@PathVariable("id") Long id, @RequestBody Request request) {
        requestService.update(request, id);
    }

    @GetMapping("/request/findAll")
    public ResponseEntity<List<Request>> findAll() {
        List<Request> requests = requestService.findAll();
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }

    @PostMapping("/request/create")
    public ResponseEntity<Request> create(@RequestBody RequestDto dto) {
        Request request = requestService.create(dto);
        return new ResponseEntity<>(request, HttpStatus.CREATED);
    }
}
