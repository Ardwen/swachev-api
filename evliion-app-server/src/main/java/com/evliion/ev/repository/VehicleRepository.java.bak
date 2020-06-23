package com.evliion.ev.repository;

import com.evliion.ev.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 *
 */
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Optional<Vehicle> findById(Long Id);
    List<Vehicle> findByUserId(Long userId);
}
