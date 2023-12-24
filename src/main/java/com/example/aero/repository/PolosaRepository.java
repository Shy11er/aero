package com.example.aero.repository;

import com.example.aero.model.Polosa.Polosa;
import com.example.aero.model.Request.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PolosaRepository extends JpaRepository<Polosa, Long> {
}
