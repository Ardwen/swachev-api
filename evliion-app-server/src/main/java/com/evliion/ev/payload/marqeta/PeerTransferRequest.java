package com.evliion.ev.payload.marqeta;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PeerTransferRequest {
  @JsonProperty("sender_user_token")
  private String senderUserToken;

  @JsonProperty("recipient_user_token")
  private String recipientUserToken;

  @JsonProperty("currency_code")
  private String currencyCode;

  private Double amount;

  public PeerTransferRequest(String senderUserToken, String recipientUserToken, String currencyCode, Double amount) {
    this.senderUserToken = senderUserToken;
    this.recipientUserToken = recipientUserToken;
    this.currencyCode = currencyCode;
    this.amount = amount;
  }

  public String getCurrencyCode() {
    return currencyCode;
  }

  public Double getAmount() {
    return amount;
  }

  public String getSenderUserToken() {
    return senderUserToken;
  }

  public String getRecipientUserToken() {
    return recipientUserToken;
  }
}
