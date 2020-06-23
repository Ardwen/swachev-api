package com.evliion.ev.payload;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;

/*
 * ToDo - Look if @NotNull or @NotBlank is valid
 */
public class VehicleRequest {
	
    //@NotBlank
	private int batteryCapacity;

	@NotBlank
	private String make;

	@NotBlank
	private String model;

	@NotBlank
	private String vehicleType;

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public int getBatteryCapacity() {
		return batteryCapacity;
	}

	public void setBatteryCapacity(int batteryCapacity) {
		this.batteryCapacity = batteryCapacity;
	}
}