package com.evliion.ev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evliion.ev.exception.ResourceNotFoundException;
import com.evliion.ev.model.Card;
import com.evliion.ev.model.Station;
import com.evliion.ev.model.User;
import com.evliion.ev.model.Vehicle;
import com.evliion.ev.payload.EstimatedChargeTimeAndCostResponse;
import com.evliion.ev.payload.VehicleRequest;
import com.evliion.ev.payload.VehicleResponse;
import com.evliion.ev.repository.StationRepository;
import com.evliion.ev.repository.UserRepository;
import com.evliion.ev.repository.VehicleRepository;
import com.evliion.ev.security.UserPrincipal;
import com.evliion.ev.util.ModelMapper;

@Service
public class VehicleService {
	
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private VehicleRepository vehicleRepository;
    
    @Autowired
    private StationRepository stationRepository;
	
	public Vehicle addVehicle(UserPrincipal currentUser, VehicleRequest vehicleRequest) {
    	User user = userRepository.getOne(currentUser.getId());
    	Vehicle vehicle = new Vehicle();
    	vehicle.setMake(vehicleRequest.getMake());
    	vehicle.setModel(vehicleRequest.getModel());
    	vehicle.setVehicleType(vehicleRequest.getVehicleType());
    	vehicle.setBatteryCapacity(vehicleRequest.getBatteryCapacity());
    	vehicle.setUser(user);
        return vehicleRepository.save(vehicle);
    }
    
    public VehicleResponse getVehicleById(Long vehicleId, UserPrincipal currentUser) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(
                () -> new ResourceNotFoundException("Vehicle", "id", vehicleId));
        User user = userRepository.getOne(currentUser.getId());
        return ModelMapper.mapVehicleToVehicleResponse(vehicle, user);
    }
    
    public boolean deleteVehicle(Long vehicleId) {
    	Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(
				()-> new ResourceNotFoundException("Vehicle", "id", vehicleId));
    	vehicleRepository.delete(vehicle);
		return vehicleRepository.existsById(vehicleId);		
	}
    
    public EstimatedChargeTimeAndCostResponse getEstimatedChargeTimeAndCost(Long vehicleId, Long stationId, UserPrincipal currentUser) {
    	/*
    	Station station = stationRepository.findById(vehicleId).orElseThrow(
                () -> new ResourceNotFoundException("Station", "id", vehicleId));
    	*/
    	//TODO - 
    	EstimatedChargeTimeAndCostResponse estimatedChargeTimeAndCostResponse = new EstimatedChargeTimeAndCostResponse(vehicleId, stationId, true, 120, 20);
        return estimatedChargeTimeAndCostResponse;
    }    
}