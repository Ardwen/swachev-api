package com.evliion.ev;

import com.evliion.ev.controller.UserController;
import com.evliion.ev.model.Vehicle;
import com.evliion.ev.payload.ApiResponse;
import com.evliion.ev.payload.UserVehicleResponse;
import com.evliion.ev.payload.VehicleRequest;
import com.evliion.ev.payload.VehicleResponse;
import net.bytebuddy.asm.Advice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PollsApplicationTests {
	@Autowired
	UserController userController;
	@Test
	public void createVehicle(){
		VehicleRequest v = new VehicleRequest();
		v.setMake("Mahindra Electric");
		v.setUser_id(1L);
		v.setModel("Verito");
		v.setModel_type("4-wheeler");
		ApiResponse res = userController.postVehicle(v);
	}

	@Test
	public void getVehicle(){

		VehicleResponse v  = userController.getVehicle(1L);
	}

	@Test
	public void getVehicles(){

		UserVehicleResponse v  = userController.getVehicles(1L);
	}



}
