package com.evliion.ev.payload;

public class EstimatedChargeTimeAndCostResponse {

	private Long vehicleId;
	private Long stationId;
	private Boolean status;
	private int timeToFullyCharge;
	private double perHourCostToCharge;

	public EstimatedChargeTimeAndCostResponse(Long vehicleId, Long stationId, Boolean status, int timeToFullyCharge,
			double perHourCostToCharge) {
		super();
		this.vehicleId = vehicleId;
		this.stationId = stationId;
		this.status = status;
		this.timeToFullyCharge = timeToFullyCharge;
		this.perHourCostToCharge = perHourCostToCharge;
	}

	public Long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}

	public Long getStationId() {
		return stationId;
	}

	public void setStationId(Long stationId) {
		this.stationId = stationId;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public double getPerHourCostToCharge() {
		return perHourCostToCharge;
	}

	public void setPerHourCostToCharge(double perHourCostToCharge) {
		this.perHourCostToCharge = perHourCostToCharge;
	}

	public int getTimeToFullyCharge() {
		return timeToFullyCharge;
	}

	public void setTimeToFullyCharge(int timeToFullyCharge) {
		this.timeToFullyCharge = timeToFullyCharge;
	}
}