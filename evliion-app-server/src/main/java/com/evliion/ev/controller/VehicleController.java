package com.evliion.ev.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.evliion.ev.model.Vehicle;
import com.evliion.ev.payload.ApiResponse;
import com.evliion.ev.payload.EstimatedChargeTimeAndCostResponse;
import com.evliion.ev.payload.VehicleRequest;
import com.evliion.ev.payload.VehicleResponse;
import com.evliion.ev.security.CurrentUser;
import com.evliion.ev.security.UserPrincipal;
import com.evliion.ev.service.VehicleService;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {
	
    @Autowired
    private VehicleService vehicleService;
	
    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> addVehicle(@Valid @RequestBody VehicleRequest vehicleRequest, @CurrentUser UserPrincipal currentUser) {
    	Vehicle vehicle = vehicleService.addVehicle(currentUser, vehicleRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{vehicleId}")
                .buildAndExpand(vehicle.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Vehicle Added Successfully", vehicle.getId()));
    }
    
    @GetMapping("/{vehicleId}")
    public VehicleResponse getVehicleById(@CurrentUser UserPrincipal currentUser,
                                    @PathVariable Long vehicleId) {
        return vehicleService.getVehicleById(vehicleId, currentUser);
    }
    
    /*
    @GetMapping("/{userId}")
    public VehicleResponse getAllVehiclesByUserId(@CurrentUser UserPrincipal currentUser,
                                    @PathVariable Long userId) {
        return pollService.getVehicleById(userId, currentUser);
    }
    */
    
    @DeleteMapping("/{vehicleId}")	
    @PreAuthorize("hasRole('USER')")
	public ApiResponse deletePaymentMethod(@PathVariable Long vehicleId){
		boolean success = vehicleService.deleteVehicle(vehicleId);
        if(!success)
            return new ApiResponse(true, "Card deleted successfully");
        return new ApiResponse(false, "Failed deleting card. Please try after some time");
	}
    
    @GetMapping("/estimatedChargeTimeAndCost")
    public EstimatedChargeTimeAndCostResponse getEstimatedChargeTimeAndCost(@CurrentUser UserPrincipal currentUser,
                                    @RequestParam("vehicleId") Long vehicleId, @RequestParam("stationId") Long stationId) {
        return vehicleService.getEstimatedChargeTimeAndCost(vehicleId, stationId, currentUser);
    }
}
