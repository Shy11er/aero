package com.example.aero.service;

import com.example.aero.DTO.PlaneDto;
import com.example.aero.model.Plane.Airliner;
import com.example.aero.model.Plane.Plane;
import com.example.aero.model.Plane.Transport;
import com.example.aero.repository.PlaneRepository;
import lombok.AllArgsConstructor;
import lombok.extern.flogger.Flogger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class PlaneService {
    private final PlaneRepository planeRepository;


    public Plane create(PlaneDto newplane) {
        Plane plane;
        try {
            if ("Airliner".equals(newplane.getPlaneType())) {
                  Airliner airliner = new Airliner();
                    airliner.setCompany(newplane.getCompany());
                    airliner.setPlaneType(Plane.PlaneType.Airliner);
                    airliner.setPassager_amount(newplane.getPassager_amount());
                    airliner.setTime_to_fly(newplane.getTime_to_fly());
                    airliner.setTitle(newplane.getTitle());
                planeRepository.save(airliner);
                plane = airliner;
            } else if ("Transport".equals(newplane.getPlaneType())) {
              Transport transport = new Transport();
                transport.setCompany(newplane.getCompany());
                transport.setPlaneType(Plane.PlaneType.Transport);
                transport.setCargo_amount(newplane.getCargo_amount());
                transport.setTime_to_fly(newplane.getTime_to_fly());
                transport.setTitle(newplane.getTitle());
                planeRepository.save(transport);
                plane = transport;
            } else {
                throw new IllegalArgumentException("Invalid Plane type or type does not match conditions.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Failed to create plane: {}".formatted(e.getMessage()));
            throw e;
        }

        return plane;
    }

    public void delete(Long plane_id) {
        planeRepository.deleteById(plane_id);
    }

    public void update(Plane dto, Long id) {
        Plane findPlane = planeRepository.findById(id).orElse(null);

        if (findPlane != null) {
            findPlane.setCompany(dto.getCompany());
            findPlane.setTime_to_fly(dto.getTime_to_fly());
            planeRepository.save(findPlane);
        }
    }

    public List<Plane> findAll() {
        List<Plane> planes = planeRepository.findAll();
        return planes;
    }
}
