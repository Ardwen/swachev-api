package com.evliion.ev.payload;

import javax.validation.constraints.*;

/**
 * 
 */
public class TransactionRequest {

	@NotBlank
	private String chargeStartTime;
	@NotBlank
	private String chargeEndTime;
	private Long stationId;
	private Long vehicleId;
	private Long totalChargeTime;
	private Long totalCost;

	public Long getStationId() {
		return stationId;
	}

	public void setStationId(Long stationId) {
		this.stationId = stationId;
	}

	public Long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getChargeStartTime() {
		return chargeStartTime;
	}

	public void setChargeStartTime(String chargeStartTime) {
		this.chargeStartTime = chargeStartTime;
	}

	public String getChargeEndTime() {
		return chargeEndTime;
	}

	public void setChargeEndTime(String chargeEndTime) {
		this.chargeEndTime = chargeEndTime;
	}

	public Long getTotalChargeTime() {
		return totalChargeTime;
	}

	public void setTotalChargeTime(Long totalChargeTime) {
		this.totalChargeTime = totalChargeTime;
	}

	public Long getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Long totalCost) {
		this.totalCost = totalCost;
	}
}