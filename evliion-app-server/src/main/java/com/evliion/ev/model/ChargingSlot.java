package com.evliion.ev.model;

import com.evliion.ev.model.audit.DateAudit;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "charging_slot", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "charge_point_id"
        })
})
public class ChargingSlot extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "charge_point_id", nullable = false)
    private ChargePoint chargePoint;
    
    @NotBlank
    @Size(max = 15)
    private String startTimestamp;

    @NotBlank
    @Size(max = 15)
    private String endTimestamp;

    @NotBlank
    @Size(max = 10)
    private String status;
    
    private Long timeTaken;
    
    private Long unitsConsumed;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ChargePoint getChargePoint() {
		return chargePoint;
	}

	public void setChargePoint(ChargePoint chargePoint) {
		this.chargePoint = chargePoint;
	}

	public String getStartTimestamp() {
		return startTimestamp;
	}

	public void setStartTimestamp(String startTimestamp) {
		this.startTimestamp = startTimestamp;
	}

	public String getEndTimestamp() {
		return endTimestamp;
	}

	public void setEndTimestamp(String endTimestamp) {
		this.endTimestamp = endTimestamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getTimeTaken() {
		return timeTaken;
	}

	public void setTimeTaken(Long timeTaken) {
		this.timeTaken = timeTaken;
	}

	public Long getUnitsConsumed() {
		return unitsConsumed;
	}

	public void setUnitsConsumed(Long unitsConsumed) {
		this.unitsConsumed = unitsConsumed;
	}
    }
