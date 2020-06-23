package com.evliion.ev.payload;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="All details about the Store")
public class StationRequest {
	@ApiModelProperty(notes = "Store name")
	@NotBlank
    @Size(min = 4, max = 40)
    private String name;

	@ApiModelProperty(notes = "Store Address")
	@NotNull
    @Valid
    private AddressRequest address;
    
	@ApiModelProperty(notes = "Store category")
	@NotBlank
    @Size(min = 3, max = 15)
    private String category;
    
	@ApiModelProperty(notes = "Store subcatedory")
	@NotBlank
    @Size(min = 3, max = 15)
    private String subCategory;
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AddressRequest getAddress() {
		return address;
	}

	public void setAddress(AddressRequest address) {
		this.address = address;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}
}