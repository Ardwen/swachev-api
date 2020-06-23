package com.evliion.ev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evliion.ev.model.ChargePoint;
import com.evliion.ev.model.ChargingSlot;
import com.evliion.ev.model.Transaction;
import com.evliion.ev.model.User;
import com.evliion.ev.model.Vehicle;
import com.evliion.ev.payload.TransactionRequest;
import com.evliion.ev.payload.TransactionResponse;
import com.evliion.ev.repository.TransactionRepository;
import com.evliion.ev.repository.UserRepository;
import com.evliion.ev.repository.VehicleRepository;
import com.evliion.ev.security.UserPrincipal;

@Service
public class TransactionService {
	
	@Autowired
    private UserRepository userRepository;
    
    @Autowired
    private VehicleRepository vehicleRepository;
    
    @Autowired
    private TransactionRepository transactionRepository;
    
	public TransactionResponse createTransaction(UserPrincipal currentUser, TransactionRequest transactionRequest) {
    	User user = userRepository.getOne(currentUser.getId());
    	Vehicle vehicle = vehicleRepository.getOne(transactionRequest.getVehicleId());
    	//transactionRequest.getPricePlanId()
    	//transactionRequest.getStationId()
    	
    	ChargePoint chargePoint = new ChargePoint();
    	ChargingSlot chargingSlot = new ChargingSlot();
    	chargingSlot.setStartTimestamp(transactionRequest.getChargeStartTime());
    	chargingSlot.setEndTimestamp(transactionRequest.getChargeEndTime());
    	chargingSlot.setTimeTaken(transactionRequest.getTotalChargeTime());
    	chargingSlot.setChargePoint(chargePoint);
    	
    	//chargingSlot.setChargePoint(chargePoint);
    	//chargingSlot.setUnitsConsumed(unitsConsumed);
    	chargingSlot.setStatus("Booked");
    	Transaction transaction = new Transaction();
    	transaction.setUser(user);
    	transaction.setVehicle(vehicle);
    	transaction.setStatus("Open");
    	transaction.setChargingSlot(chargingSlot);
    	//TODO: chargingSlot add units consumed

        //return transactionRepository.save(transaction);
    	
    	TransactionResponse transactionResponse = new TransactionResponse((long) 123425514, true);
    	
    	return transactionResponse;
    }
}