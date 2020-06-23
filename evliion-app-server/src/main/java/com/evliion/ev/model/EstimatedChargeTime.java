package com.evliion.ev.model;

import com.evliion.ev.model.audit.DateAudit;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "estimatedChargeTime", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "vehicle_id"
        })
})
public class EstimatedChargeTime extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;
    
    @NotBlank
    @Size(max = 15)
    private String chargeTime;

    @NotBlank
    @Size(max = 15)
    private String model;

    @NotBlank
    @Size(max = 15)
    private String vehicleType;
}