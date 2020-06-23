package com.evliion.ev.controller;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.evliion.ev.*;
import com.evliion.ev.repository.*;
import com.evliion.ev.service.TransService;
import com.evliion.ev.model.*;

@Controller
@RequestMapping(value="/api")
public class TransController {
	
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
	    private TransService transService;

	//--------------------- Create Transaction-------------------------------//
	    /*
	@GetMapping("/v1/transaction")
	public @ResponseBody String postTrans(@RequestBody Transaction transaction)
	{
        boolean success = transService.createTrans(transaction);
 
       
		  if(success)
			return "{\"success\": \"true\", \"transactionId\": " + transaction.getTransId() + " \"message\": \"Transaction created successfully\" }";
		  else
			return "{\"success\": \"false\", \"message\": \"Failed creating transaction. Please try after some time\" }";
	}
	*/
	//----------------------Retrieve Single Transaction---------------------------//
    @GetMapping("/v1/transaction{transactionId}")
	public @ResponseBody String getTrans(@PathVariable("transactionId") long id)
	{
    	if(transRepository.existsById(id))//transaction does exits in data base
    	{
    		return null;
    				//transService.getTransDetails(id);
    	}
    	else
    	{
    		return "{" +
    		     	"\"success\": \"false\"," + 
    			  "\"message\": \"Failed fetching transaction. Please try after some time\" " +
    			 "}";

    	}		
	}
				
}



