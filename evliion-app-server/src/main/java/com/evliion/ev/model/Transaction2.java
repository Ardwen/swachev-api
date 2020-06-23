package com.evliion.ev.model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
 

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"userId",
"stationId",
"vehicleId",
"slotDuration",
"pricePlanId"
})

@Entity//This tells Hibernate to make a table out of this class
@Table(name = "transactions")
public class Transaction2 {

@JsonProperty("userId")
private long userId;
@JsonProperty("stationId")
private long stationId;
@JsonProperty("vehicleId")
private long vehicleId;
@JsonProperty("pricePlanId")
private long pricePlanId;

private String startTime;
private String endTime;
private String totalTime;


public Transaction2()
{
	this.transId = System.currentTimeMillis();//intialize transid when transaction is constructor is called
}
@Id
private Long transId;


@JsonProperty("userId")
public long getUserId() {
return userId;
}

@JsonProperty("userId")
public void setUserId(long userId) {
this.userId = userId;
}

@JsonProperty("stationId")
public Long getStationId() {
return stationId;
}

@JsonProperty("stationId")
public void setStationId(long stationId) {
this.stationId = stationId;
}

@JsonProperty("vehicleId")
public long getVehicleId() {
return vehicleId;
}

@JsonProperty("vehicleId")
public void setVehicleId(long vehicleId) {
this.vehicleId = vehicleId;
}



@JsonProperty("slotDuration")
public void setSlotDuration(Map<String, String> slotduration) {

	this.startTime = slotduration.get("startTime");
	this.endTime = slotduration.get("endTime");
	this.totalTime = slotduration.get("totalTime");
}

@JsonProperty("pricePlanId")
public long getPricePlanId() {
return pricePlanId;
}

@JsonProperty("pricePlanId")
public void setPricePlanId(long pricePlanId) {
this.pricePlanId = pricePlanId;
}

public long getTransId(){
	return this.transId;
}

public void setTransId(long transId) {
	this.transId = transId;
			
}

public String getStartTime()
{
	return this.startTime;
}

public String getEndTime()
{
	return this.endTime;
}

public String getTotalTime()
{
	return this.totalTime;
}

}