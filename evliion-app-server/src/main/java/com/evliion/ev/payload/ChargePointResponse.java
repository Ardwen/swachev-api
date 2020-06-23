package com.evliion.ev.payload;

public class ChargePointResponse {

	private Long stationId;
	private Long chargePointId;
	private String chargePointIdentifier;
	private int capacity;
	private String type;
	private String status;

	public ChargePointResponse() {

	}
	public Long getStationId() {
		return stationId;
	}
	public void setStationId(Long stationId) {
		this.stationId = stationId;
	}
	public Long getChargePointId() {
		return chargePointId;
	}
	public void setChargePointId(Long chargePointId) {
		this.chargePointId = chargePointId;
	}
	public String getChargePointIdentifier() {
		return chargePointIdentifier;
	}
	public void setChargePointIdentifier(String chargePointIdentifier) {
		this.chargePointIdentifier = chargePointIdentifier;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
