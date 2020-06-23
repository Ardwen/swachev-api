package com.evliion.ev.payload.response;

import java.util.ArrayList;
import java.util.List;

import com.evliion.ev.payload.CustomAttribute;

public class ProductResponse {
    private String name;
    
    private String description;

    private String category;
    
    private String currency;

    private String unit;
    
    private Double rate;
    
    private List<CustomAttribute> attributes = new ArrayList<>();

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

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public List<CustomAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<CustomAttribute> attributes) {
		this.attributes = attributes;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}