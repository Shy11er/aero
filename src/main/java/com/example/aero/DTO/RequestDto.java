package com.example.aero.DTO;

import com.example.aero.model.Request.Request;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RequestDto {
    private Long plane_id;
    private LocalDateTime arrival;
    private LocalDateTime departure;
    private String requestType;
    private Boolean time_changed;

    public RequestDto(Long plane_id, LocalDateTime arrival, LocalDateTime departure, String requestType, Boolean time_changed) {
        this.plane_id = plane_id;
        this.arrival = arrival;
        this.departure = departure;
        this.requestType = requestType;
        this.time_changed = time_changed;
    }
}
