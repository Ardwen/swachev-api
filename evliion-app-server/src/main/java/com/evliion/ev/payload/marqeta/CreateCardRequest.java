package com.evliion.ev.payload.marqeta;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateCardRequest {

  @JsonProperty("user_token")
  private String userToken;

  @JsonProperty("card_product_token")
  private String cardProductToken;

  public CreateCardRequest(String userToken, String cardProductToken) {
    this.userToken = userToken;
    this.cardProductToken = cardProductToken;
  }

  public String getUserToken() {
    return userToken;
  }

  public String getCardProductToken() {
    return cardProductToken;
  }
}
