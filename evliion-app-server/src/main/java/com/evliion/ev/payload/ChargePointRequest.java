package com.evliion.ev.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

public class ChargePointRequest {


	@NotBlank
	@Size(max = 20)
	private String chargePointIdentifier;

	@NonNull
	private int capacity;

	@NotBlank
	@Size(max = 20)
	private String type;

	@Size(max = 20)
	private String status;

	@NonNull
	private Long stationId;

	public Long getStationId() {
		return stationId;
	}

	public String getChargePointIdentifier() {
		return chargePointIdentifier;
	}

	public int getCapacity() {
		return capacity;
	}

	public String getType() {
		return type;
	}

	public String getStatus() {
		return status;
	}   

}
