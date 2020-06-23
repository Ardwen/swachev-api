package com.evliion.ev.payload;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="All details about the Store address")
public class AddressRequest {
    
	@ApiModelProperty(notes = "Store address line1")
	@NotBlank
    @Size(min = 4, max = 40)
    private String line1;

	@ApiModelProperty(notes = "Store address line2")
	@NotBlank
    @Size(min = 4, max = 40)
    private String line2;
    
	@ApiModelProperty(notes = "Zip code")
	@NotNull
    private Integer zipCode;
    
	@ApiModelProperty(notes = "City")
	@NotBlank
    @Size(min = 3, max = 20)
    private String city;
    
	@ApiModelProperty(notes = "State")
	@NotBlank
    @Size(min = 4, max = 20)
    private String state;

	@ApiModelProperty(notes = "Country")
	@NotBlank
    @Size(min = 4, max = 20)
    private String country;
    
	@ApiModelProperty(notes = "lattitude")
	@NotNull
    @Valid
    private Long lattitude;
    
	@ApiModelProperty(notes = "longitude")
	@NotNull
    @Valid
    private Long longitude;

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