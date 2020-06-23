package com.evliion.ev.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.evliion.ev.model.ChargePoint;
import com.evliion.ev.payload.ApiResponse;
import com.evliion.ev.payload.ChargePointRequest;
import com.evliion.ev.payload.ChargePointResponse;
import com.evliion.ev.service.ChargePointService;

@RestController
@RequestMapping("/api/v1")
public class ChargePointController {

	@Autowired
	private ChargePointService chargePointService;


	@PostMapping("/charge-point")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> createChargePoint(@Valid @RequestBody ChargePointRequest chargePointRequest){
		ChargePoint chargePoint = chargePointService.addChargePoint(chargePointRequest);

		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{chargePointId}")
				.buildAndExpand(chargePoint.getId()).toUri();

		return ResponseEntity.created(location)
				.body(new ApiResponse(true, "Charge Point added Successfully"));
	} 


	@GetMapping("/charge-point/{chargePointId}")
	@PreAuthorize("hasRole('USER')")
	public ChargePointResponse getChargerPointById( @PathVariable Long chargePointId){
		return chargePointService.getChargePointById(chargePointId);			
	}


	@GetMapping("/charge-points/{stationId}")
	@PreAuthorize("hasRole('USER')")
	public List<ChargePointResponse> getChargerPointByStationId(@PathVariable Long stationId){
		return chargePointService.getChargerPointByStationId(stationId);
	}
	
	@GetMapping("/charge-points")
	public List<ChargePointResponse> getAllChargePoints(@RequestParam double geoLng, 
			@RequestParam double geoLat, @RequestParam int radius){
		return chargePointService.getAllChargePoints(geoLat, geoLng, radius);
	}
}
