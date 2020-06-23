package com.evliion.ev.repository;

import com.evliion.ev.model.ChargingSlot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 */
@Repository
public interface ChargingSlotRepository extends JpaRepository<ChargingSlot, Long> {
	
}