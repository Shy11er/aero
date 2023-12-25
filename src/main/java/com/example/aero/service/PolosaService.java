package com.example.aero.service;

import com.example.aero.DTO.PlaneDto;
import com.example.aero.model.Plane.Airliner;
import com.example.aero.model.Plane.Plane;
import com.example.aero.model.Plane.Transport;
import com.example.aero.model.Polosa.Polosa;
import com.example.aero.model.Polosa.Posadochnaya;
import com.example.aero.model.Polosa.Vzletnaya;
import com.example.aero.repository.PlaneRepository;
import com.example.aero.repository.PolosaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class PolosaService {
    private final PolosaRepository polosaRepository;


    public List<Polosa> create(Long count) {
       List<Polosa> pol = new ArrayList<>();
       List<String> covers = Arrays.asList("Железобетонное", "Асфальтобетон", "Металлическое");

       for (int i = 0; i < count; i++) {
           Polosa polosa;
           int rand = new Random().nextInt(2);
           int randCover = new Random().nextInt(3);

           if (rand == 0) {
               Posadochnaya posadochnaya = new Posadochnaya();
               posadochnaya.setPolosaType(Polosa.PolosaType.Posadochnaya);
               posadochnaya.setCovering(covers.get(randCover));
               posadochnaya.setIsBusy(false);
               polosaRepository.save(posadochnaya);
               polosa = posadochnaya;
           } else {
               Vzletnaya vzletnaya = new Vzletnaya();
               vzletnaya.setPolosaType(Polosa.PolosaType.Vzletnaya);
               vzletnaya.setCovering(covers.get(randCover));
               vzletnaya.setIsBusy(false);
               polosaRepository.save(vzletnaya);
               polosa = vzletnaya;
           }
           polosaRepository.save(polosa);
           pol.add(polosa);
       }

       return pol;
    }

    public void delete(Long polosa_id) {
        polosaRepository.deleteById(polosa_id);
    }

    public void update(Polosa dto, Long id) {
        Polosa findPolosa = polosaRepository.findById(id).orElseThrow(() -> new RuntimeException("Полоса не найдена"));

        findPolosa.setPolosaType(dto.getPolosaType());
        polosaRepository.save(findPolosa);
    }

    public List<Polosa> findAll() {
       return polosaRepository.findAll();
    }
}
