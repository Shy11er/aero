package com.example.aero.service;

import com.example.aero.DTO.RequestDto;
import com.example.aero.model.Plane.Airliner;
import com.example.aero.model.Plane.Plane;
import com.example.aero.model.Plane.Transport;
import com.example.aero.model.Request.Posadka;
import com.example.aero.model.Request.Request;
import com.example.aero.model.Request.Vzlet;
import com.example.aero.repository.PlaneRepository;
import com.example.aero.repository.RequestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class RequestService {
    private final RequestRepository requestRepository;
    private final PlaneRepository planeRepository;

    public Request create(RequestDto dto) {
        Plane plane = planeRepository.findById(dto.getPlane_id()).orElseThrow(()-> new RuntimeException("Plane not found"));

        Request request;

        try {
            if ("Vzlet".equals(dto.getRequestType())) {
                Vzlet vzlet = new Vzlet();
                vzlet.setDeparture(dto.getDeparture());
                vzlet.setRequestType(Request.RequestType.Vzlet);

                requestRepository.save(vzlet);
                request = vzlet;
            } else if ("Posadka".equals(dto.getRequestType())) {
                Posadka posadka = new Posadka();
                posadka.setArrival(dto.getArrival());
                posadka.setRequestType(Request.RequestType.Posadka);

                requestRepository.save(posadka);
                request = posadka;
            } else {
                throw new IllegalArgumentException("Invalid Request type or type does not match conditions.");
            }
        } catch (IllegalArgumentException e) {
            throw e;
        }

        request.setPlane(plane);
        request.setStatus("Waiting");
        request.setTime_changed(dto.getTime_changed());

        requestRepository.save(request);
        return request;
    }

    public void delete(Long id) {
        requestRepository.deleteById(id);
    }

    public void update(Request dto, Long id) {
//        Plane findPlane = planeRepository.findById(id).orElse(null);
//
//        if (findPlane != null) {
//            findPlane.setCompany(dto.getCompany());
//            findPlane.setTime_to_fly(dto.getTime_to_fly());
//            planeRepository.save(findPlane);
//        }
    }

    public List<Request> findAll() {
        List<Request> requests = requestRepository.findAll();
        return requests;
    }
}
