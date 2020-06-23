package com.evliion.ev.payload.marqeta;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoadFundsRequest {
  @JsonProperty("user_token")
  private String userToken;

  @JsonProperty("funding_source_token")
  private String fundingSourceToken;

  @JsonProperty("funding_source_address_token")
  private String fundingSourceAddressToken;

  @JsonProperty("currency_code")
  private String currencyCode;

  private Double amount;

  public LoadFundsRequest(String userToken, String fundingSourceToken, String fundingSourceAddressToken,
                          String currencyCode, Double amount) {
    this.userToken = userToken;
    this.fundingSourceToken = fundingSourceToken;
    this.fundingSourceAddressToken = fundingSourceAddressToken;
    this.currencyCode = currencyCode;
    this.amount = amount;
  }

  public String getUserToken() {
    return userToken;
  }

  public String getFundingSourceToken() {
    return fundingSourceToken;
  }

  public String getCurrencyCode() {
    return currencyCode;
  }

  public Double getAmount() {
    return amount;
  }
}
