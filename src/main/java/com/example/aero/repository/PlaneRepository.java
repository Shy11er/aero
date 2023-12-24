package com.example.aero.repository;

import com.example.aero.model.Plane.Plane;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaneRepository extends JpaRepository<Plane, Long> {
}
