package com.evliion.ev.payload.marqeta;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentCardRequest {
  @JsonProperty("account_number")
  private String accountNumber;

  @JsonProperty("cvv_number")
  private String cvvNumber;

  @JsonProperty("exp_date")
  private String expiryDate;

  @JsonProperty("user_token")
  private String userToken;

  public PaymentCardRequest setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
    return this;
  }

  public PaymentCardRequest setCvvNumber(String cvvNumber) {
    this.cvvNumber = cvvNumber;
    return this;
  }

  public PaymentCardRequest setExpiryDate(String expiryDate) {
    this.expiryDate = expiryDate;
    return this;
  }

  public PaymentCardRequest setUserToken(String userToken) {
    this.userToken = userToken;
    return this;
  }
}
