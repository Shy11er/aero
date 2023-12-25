package com.example.aero.service;

import com.example.aero.model.Airoport;
import com.example.aero.model.Otchet;
import com.example.aero.model.Plane.Plane;
import com.example.aero.model.Polosa.Polosa;
import com.example.aero.model.Request.Posadka;
import com.example.aero.model.Request.Request;
import com.example.aero.model.Request.Vzlet;
import com.example.aero.repository.PolosaRepository;
import com.example.aero.repository.RequestRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//@AllArgsConstructor
@Service
public class AeroService {
    private final RequestService requestService;
    private final RequestRepository requestRepository;
    private final PolosaService polosaService;
    private final PolosaRepository polosaRepository;
    private final Airoport airoport;
    private final Otchet otchet;
    private final SimulationService simulationService;

    @Autowired
    public AeroService(
            RequestService requestService,
            RequestRepository requestRepository,
            PolosaService polosaService,
            PolosaRepository polosaRepository,
            Airoport airoport,
            Otchet otchet,
            SimulationService simulationService
    ) {
        this.requestService = requestService;
        this.requestRepository = requestRepository;
        this.polosaService = polosaService;
        this.polosaRepository = polosaRepository;
        this.airoport = airoport;
        this.simulationService = simulationService;
        this.otchet = otchet;
    }

    private LocalDateTime currTime;

    public void init() {
        airoport.initialize();
        int time = simulationService.getTime();
        LocalDateTime tt = LocalDateTime.now();
        currTime = tt.withHour(time).withMinute(0).withSecond(0);
    }

    public void work() {
        List<Request> requestRemove = new ArrayList<>(airoport.getSchedule());
        List<Request> requestsQueue = new ArrayList<>();
        List<Polosa> polosaList = airoport.getRunwayList();

        while (requestRemove.size() > 0) {
            // заполнение очереди
            for (Request request : requestRemove) {
                if (request.getStatus().equals("Waiting") && !requestsQueue.contains(request)) {
                    if (request instanceof Vzlet) {
                        LocalDateTime depart = ((Vzlet) request).getDeparture();
                        if (!currTime.isBefore(depart)) {
                            requestsQueue.add(request);
                        }
                    }
                    if (request instanceof Posadka) {
                        LocalDateTime arrival = ((Posadka) request).getArrival();
                        if (!currTime.isBefore(arrival)) {
                            requestsQueue.add(request);
                        }
                    }
                }
            }

            // поиск реквеста для работы в полосе
            for (Polosa polosa : polosaList) {
                for (Request request : requestsQueue) {
                    if (request.getRequestType().equals(Request.RequestType.Posadka) && request.getStatus().equals("Waiting")) {
                        if (polosa.getPolosaType().equals(Polosa.PolosaType.Posadochnaya) && !polosa.getIsBusy() && polosa.getRequest() == null) {
                            polosa.setIsBusy(true);
                            polosa.setRequest(request);

                            request.setPolosa(polosa);
                            request.setStatus("Working");
                            request.setStart(currTime);
                            request.setFinish(currTime.plusMinutes(request.getPlane().getTime_to_fly()));
                            requestRepository.save(request);
                            polosaRepository.save(polosa);
                        }
                    }

                    if (request.getRequestType().equals(Request.RequestType.Vzlet) && request.getStatus().equals("Waiting")) {
                        if (polosa.getPolosaType().equals(Polosa.PolosaType.Vzletnaya) && !polosa.getIsBusy() && polosa.getRequest() == null) {
                            polosa.setIsBusy(true);
                            polosa.setRequest(request);

                            request.setPolosa(polosa);
                            request.setStart(currTime);
                            request.setStatus("Working");
                            request.setFinish(currTime.plusMinutes(request.getPlane().getTime_to_fly()));
                            requestRepository.save(request);
                            polosaRepository.save(polosa);
                        }
                    }

                }

                // проверка заявки на занятость и дальнейшее удаление из списка
                if (polosa.getIsBusy() && polosa.getRequest() != null) {
                    Request req = polosa.getRequest();
                    LocalDateTime dep = req.getFinish();

                    if (currTime.compareTo(dep) >= 0) {
                        polosa.setIsBusy(false);
                        polosa.setRequest(null);

                        req.setStatus("Finished");
                        req.setPolosa(null);

                        polosaRepository.save(polosa);
                        requestRepository.save(req);

                        requestsQueue.remove(req);
                        requestRemove.remove(req);
                    }
                }
            }

            System.out.println(requestsQueue.size());
            currTime = currTime.plusMinutes(2);
        }

        System.out.println("DONE");
    }

    private void updateQueue() {

    }
}
