package com.evliion.ev.payload;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CardRequest {

	@NotBlank
	@Size(max = 40)
	@Valid
	private String name;

	@NotBlank
	@Size(min = 16, max = 16)
	@Valid
	private String number;

	@NotNull
	@Min(1)
	@Max(12)
	@Valid
	private int expMonth;

	@NotNull
	@Valid
	private int expYear;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getExpMonth() {
		return expMonth;
	}

	public void setExpMonth(int expMonth) {
		this.expMonth = expMonth;
	}

	public int getExpYear() {
		return expYear;
	}

	public void setExpYear(int expYear) {
		this.expYear = expYear;
	}   

}
