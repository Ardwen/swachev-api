package com.evliion.ev;

import com.evliion.ev.controller.TransController;
import com.evliion.ev.controller.UserController;
import com.evliion.ev.model.Transaction;
import com.evliion.ev.model.Vehicle;
import com.evliion.ev.payload.ApiResponse;
import com.evliion.ev.payload.UserVehicleResponse;
import com.evliion.ev.payload.VehicleRequest;
import com.evliion.ev.payload.VehicleResponse;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.bytebuddy.asm.Advice;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.format.Parser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // for restTemplate

public class TransTest {

	@Autowired
	TransController transController;
	
	@Test
	public void createTransaction()throws JSONException, ParseException, JsonParseException, JsonMappingException, IOException
	{ /*
		String file = "createTrans.json";
		ObjectMapper mapper = new ObjectMapper();
		Transaction transaction = mapper.readValue(new File(file), Transaction.class);
				
		String apiResponse = transController.postTrans(transaction);
		
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(apiResponse);s
		
		
		assertEquals("false", json.getAsString("success"));	
		*/
	}
	
}
