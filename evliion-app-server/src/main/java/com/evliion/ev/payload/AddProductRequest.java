package com.evliion.ev.payload;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddProductRequest {

	@NotBlank
    @Size(min = 4, max = 40)
    private String name;
	
    private String description;

    @NotBlank
    @Size(min = 3, max = 15)
    private String category;
    
    @NotBlank
    @Size(min = 3, max = 15)
    private String currency;

    @NotBlank
    @Size(min = 3, max = 15)
    private String unit;
    
    @NotNull
    @Valid
    private Double quantity;
    
    @NotNull
    @Valid
    private Double rate;

    @NotNull
    @Valid
    private Long storeId;
    
    private List<CustomAttribute> customAttributes;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public List<CustomAttribute> getCustomAttributes() {
		return customAttributes;
	}

	public void setCustomAttributes(List<CustomAttribute> customAttributes) {
		this.customAttributes = customAttributes;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
