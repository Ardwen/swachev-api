package com.evliion.ev.payload;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Address {
    
	@NotBlank
    @Size(min = 4, max = 40)
    private String line1;

    @NotNull
    @Valid
    private Address line2;
    
    @NotNull
    @Max(6)
    private Integer zipCode;
    
    @NotBlank
    @Size(min = 3, max = 10)
    private String city;
    
    @NotBlank
    @Size(min = 4, max = 10)
    private String state;

    @NotBlank
    @Size(min = 4, max = 10)
    private String country;

	public String getLine1() {
		return line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}

	public Address getLine2() {
		return line2;
	}

	public void setLine2(Address line2) {
		this.line2 = line2;
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
}