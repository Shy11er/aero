//package com.example.aero.model;
//
//import com.example.aero.model.Plane.Plane;
//import com.example.aero.model.Polosa.Polosa;
//import com.example.aero.model.Request.Posadka;
//import com.example.aero.model.Request.Request;
//import com.example.aero.model.Request.Vzlet;
//import com.example.aero.service.PolosaService;
//import com.example.aero.service.RequestService;
//import lombok.Data;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//@Data
//@Component
//public class Airoport {
//    private List<Request> raspisanie;
//    private List<Polosa> polosaList;
//
//    private final RequestService requestService;
//    private final PolosaService polosaService;
//
//    @Autowired
//    public Airoport(RequestService requestService, PolosaService polosaService) {
//        this.requestService = requestService;
//        this.polosaService = polosaService;
//        this.raspisanie = requestService.findAll();
//        this.polosaList = polosaService.findAll();
//    }
//
//    public void init() {
//        this.raspisanie = requestService.findAll();
//        this.polosaList = polosaService.findAll();
//    }
//
//    public boolean hasRequests() {
//        return !raspisanie.isEmpty();
//    }
//
//    public void updateQueue(LocalDateTime currDate) {
//        Iterator<Request> iterator = raspisanie.iterator();
//
//        while (iterator.hasNext()) {
//            Request request = iterator.next();
//
//            if (request.getStatus().equals("Waiting")) {
//                if (request instanceof Vzlet) {
//                    if (currDate.compareTo(((Vzlet) request).getDeparture()) >= 0) {
//                        request.setStart(currDate);
//                        iterator.remove();
//                    }
//                }
//                if (request instanceof Posadka) {
//                    if (currDate.compareTo(((Posadka) request).getArrival()) >= 0) {
//                        request.setStart(currDate);
//                        iterator.remove();
//                    }
//                }
//            }
//        }
//    }
//
//    public void polosaWork(LocalDateTime currDate) {
//        for (Polosa polosa : polosaList) {
//            Request request = getRequestInQueue(polosa);
//
//            if (request != null) {
//                workWithRequest(request, polosa, currDate);
//                doneWorkWithRequest(request, polosa, currDate);
//            }
//        }
//    }
//
//    private Request getRequestInQueue(Polosa polosa) {
//
//        return raspisanie.stream()
//                .filter(request -> !polosa.getIsBusy() && request.isCompatible(polosa))
//                .findFirst()
//                .orElse(null);
//    }
//
//    private void workWithRequest(Request request, Polosa polosa, LocalDateTime currDate) {
//        Plane plane = request.getPlane();
//        polosa.setIsBusy(true);
//        polosa.setRequest(request);
//
//        request.setStatus("Working");
//        request.setStart(currDate);
//        request.setFinish(currDate.plusMinutes(plane.getTime_to_fly()));
//    }
//
//    private void doneWorkWithRequest(Request request, Polosa polosa, LocalDateTime currDate) {
//        if (currDate.compareTo(request.getFinish()) >= 0) {
//            request.setStatus("Finished");
//            request.setPolosa(null);
//            polosa.setRequest(null);
//            polosa.setIsBusy(false);
//        }
//    }
//}

package com.example.aero.model;

import com.example.aero.model.Plane.Plane;
import com.example.aero.model.Polosa.Polosa;
import com.example.aero.model.Request.Request;
import com.example.aero.service.PolosaService;
import com.example.aero.service.RequestService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Data
@Component
public class Airoport {
    private List<Request> schedule;
    private List<Polosa> runwayList;

    private final RequestService requestService;
    private final PolosaService polosaService;

    @Autowired
    public Airoport(RequestService requestService, PolosaService polosaService) {
        this.requestService = requestService;
        this.polosaService = polosaService;
        this.schedule = requestService.findAll();
        this.runwayList = polosaService.findAll();
    }

    public void initialize() {
        this.schedule = requestService.findAll();
        this.runwayList = polosaService.findAll();
    }
//
//    public boolean hasRequests() {
//        return !schedule.isEmpty();
//    }
//
//    public void updateQueue(LocalDateTime currentTime) {
//        schedule.removeIf(request -> request.getStatus().equals("Waiting") && request.isReady(request, currentTime));
//    }
//
//    public void processRunways(LocalDateTime currentTime) {
//        for (Polosa runway : runwayList) {
//            Optional<Request> requestInQueue = findRequestForRunway(runway);
//
//            requestInQueue.ifPresent(request -> {
//                processRequest(request, runway, currentTime);
//                handleCompletedRequest(request, runway, currentTime);
//            });
//        }
//    }
//
//    private Optional<Request> findRequestForRunway(Polosa runway) {
//        return schedule.stream()
//                .filter(request -> !runway.getIsBusy() && request.isCompatible(runway))
//                .findFirst();
//    }
//
//    private void processRequest(Request request, Polosa runway, LocalDateTime currentTime) {
//        Plane plane = request.getPlane();
//        runway.setIsBusy(true);
//        runway.setRequest(request);
//
//        request.setStatus("Working");
//        request.setStart(currentTime);
//        request.setFinish(currentTime.plusMinutes(plane.getTime_to_fly()));
//    }
//
//    private void handleCompletedRequest(Request request, Polosa runway, LocalDateTime currentTime) {
//        if (currentTime.compareTo(request.getFinish()) >= 0) {
//            request.setStatus("Finished");
//            request.setPolosa(null);
//            runway.setRequest(null);
//            runway.setIsBusy(false);
//        }
//    }
}