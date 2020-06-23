package com.evliion.ev.model;

import com.evliion.ev.model.audit.DateAudit;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "address")
public class Address extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 40)
    private String line1;

    @NotBlank
    @Size(max = 40)
    private String line2;
    
    //@NotNull
    //@Max(6)
    private Integer zipCode;
    
    @NotBlank
    @Size(max = 10)
    private String city;
    
    @NotBlank
    @Size(max = 20)
    private String state;

    @NotBlank
    @Size(max = 10)
    private String country;
    
    //@NotEmpty
    @NotNull
    private Long lattitude;
    
    //@NotEmpty
    @NotNull
    private Long longitude;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLine1() {
		return line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}

	public Integer getZipCode() {
		return zipCode;
	}

	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLine2() {
		return line2;
	}

	public void setLine2(String line2) {
		this.line2 = line2;
	}

	public Long getLattitude() {
		return lattitude;
	}

	public void setLattitude(Long lattitude) {
		this.lattitude = lattitude;
	}

	public Long getLongitude() {
		return longitude;
	}

	public void setLongitude(Long longitude) {
		this.longitude = longitude;
	}
}