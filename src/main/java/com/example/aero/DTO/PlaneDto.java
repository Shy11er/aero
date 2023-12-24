package com.example.aero.DTO;

import com.example.aero.model.Plane.Plane;
import lombok.Data;

@Data
public class PlaneDto {
    private String planeType;
    private String company;
    private String title;
    private Long time_to_fly;
    private Long passager_amount;
    private Long cargo_amount;

    public PlaneDto(String planeType, String company, Long time_to_fly, String title, Long passager_amount, Long cargo_amount) {
        this.planeType = planeType;
        this.title = title;
        this.company = company;
        this.time_to_fly = time_to_fly;
        this.passager_amount = passager_amount;
        this.cargo_amount = cargo_amount;
    }

    public PlaneDto() {}
}
