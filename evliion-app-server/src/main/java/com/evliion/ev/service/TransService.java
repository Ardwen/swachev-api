package com.evliion.ev.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.evliion.ev.repository.*;
import com.evliion.ev.model.*;
@Service
public class TransService {

	  @Autowired
	    private PollRepository pollRepository;

	    @Autowired
	    private VoteRepository voteRepository;

	    @Autowired
	    private UserRepository userRepository;

	    @Autowired
	    private VehicleRepository vehicleRepository;
	    
	    @Autowired 
	    private TransRepository transRepository;
	    
	    @Autowired 
	    private ChargePointRepository chargePointRepository;

    private static final Logger logger = LoggerFactory.getLogger(PollService.class);
/*
	public boolean createTrans(Transaction transaction)
	{
	     long userId = transaction.getUserId();
	     long stationId = transaction.getStationId();
	     if(userRepository.existsById(userId) && chargePointRepository.existsById(stationId))
	     {
			transRepository.saveAndFlush(transaction);
	    	 return true;
	     }
	     return false;
	}
	
	public String getTransDetails(long id)
	{
		Transaction transaction = transRepository.getOne(id);
		Optional<Station> stationMayNull = chargePointRepository.getStation(transaction.getStationId());
		Vehicle vehicle = vehicleRepository.getOne(transaction.getVehicleId());	
		Station station = stationMayNull.get();
		 
	
		return "{"+
		        "\"transactionId\" : " + id + "," +
		       // "\"status\" :  \"complete\","  +
		      //  "\"totalCost\" : 
		         "\"userId\": "+ transaction.getUserId() + "," +
		         "\"stationName\" : " + station.getName() + "," + 
		         "\"vehicleDetails\" : " +
		          "{" +
		         		"\"make\" : " + vehicle.getMake() + "," + 
		         		"\"model\" : " + vehicle.getModel() + 
		           "}" + "," +
		         "\"slotDuration\" : " + 
		           "{"+
		           		"\"startTime\" : " + transaction.getStartTime() + "," +
		           		"\"endTime\" : " + transaction.getEndTime() + ","  +
						"\"totalTime\" : " + transaction.getTotalTime() + ","  + 
		           		// "\"unitsConsumed\" : 
					"}" ;
		           	         
		
	}
	*/
}
