package com.example.aero.controller;

import com.example.aero.model.Polosa.Polosa;
import com.example.aero.service.PolosaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:5173")
@AllArgsConstructor
public class PolosaController {
    private final PolosaService polosaService;

    @GetMapping("/polosa/create/{count}")
    public ResponseEntity<List<Polosa>> create(@PathVariable("count") Long count) {
        List<Polosa> poloses = polosaService.create(count);
        return new ResponseEntity<>(poloses, HttpStatus.OK);
    }

    @DeleteMapping("/polosa/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        polosaService.delete(id);
    }

    @PatchMapping("/polosa/update/{id}")
    public void update(@PathVariable("id") Long id, @RequestBody Polosa dto) {
        polosaService.update(dto, id);
    }

    @GetMapping("/polosa/findAll")
    public ResponseEntity<List<Polosa>> findAll() {
        List<Polosa> pol = polosaService.findAll();
        return new ResponseEntity<>(pol, HttpStatus.OK);
    }
}
