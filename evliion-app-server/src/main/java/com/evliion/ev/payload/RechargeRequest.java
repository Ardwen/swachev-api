package com.evliion.ev.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class RechargeRequest {
  @Positive
  @NotNull
  private Double amount;

  @NotBlank
  private String currency;

  @NotBlank
  private String fundSourceId;

  @NotBlank
  private String billingAddressId;

  public Double getAmount() {
    return amount;
  }

  public String getCurrency() {
    return currency;
  }

  public String getFundSourceId() {
    return fundSourceId;
  }

  public String getBillingAddressId() {
    return billingAddressId;
  }
}
