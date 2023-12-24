package com.example.aero.repository;

import com.example.aero.model.Plane.Plane;
import com.example.aero.model.Request.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Long> {
}
