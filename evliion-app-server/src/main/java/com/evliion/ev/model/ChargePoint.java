package com.evliion.ev.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;
import org.springframework.lang.NonNull;

@Entity
@Table(name="charge_points", uniqueConstraints = {
		@UniqueConstraint(columnNames = {
				"chargePointIdentifier"	
		})
})
public class ChargePoint {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 20)
	private String chargePointIdentifier;

	@NonNull
	private int capacity;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private ChargePointType type;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private ChargePointStatus status;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "station_id", nullable = false)
	@NonNull
	private Station station;

	public ChargePoint() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public ChargePointType getType() {
		return type;
	}

	public void setType(ChargePointType type) {
		this.type = type;
	}

	public ChargePointStatus getStatus() {
		return status;
	}

	public void setStatus(ChargePointStatus status) {
		this.status = status;
	}

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

}
