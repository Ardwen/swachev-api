package com.evliion.ev.payload.marqeta;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AchRequest {
  @JsonProperty("account_number")
  private String accountNumber;

  @JsonProperty("name_on_account")
  private String nameOnAccount;

  @JsonProperty("account_type")
  private String accountType;

  @JsonProperty("bank_name")
  private String bankName;

  @JsonProperty("routing_number")
  private String routingNumber;

  @JsonProperty("verification_override")
  private boolean verificationOverride;

  @JsonProperty("user_token")
  private String userToken;

  @JsonProperty("is_default_account")
  private boolean isDefaultAccount;

  public boolean isDefaultAccount() {
    return isDefaultAccount;
  }

  public AchRequest setDefaultAccount(boolean defaultAccount) {
    isDefaultAccount = defaultAccount;
    return this;
  }

  public AchRequest setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
    return this;
  }

  public AchRequest setNameOnAccount(String nameOnAccount) {
    this.nameOnAccount = nameOnAccount;
    return this;
  }

  public AchRequest setAccountType(String accountType) {
    this.accountType = accountType;
    return this;
  }

  public AchRequest setBankName(String bankName) {
    this.bankName = bankName;
    return this;
  }

  public AchRequest setRoutingNumber(String routingNumber) {
    this.routingNumber = routingNumber;
    return this;
  }

  public AchRequest setVerificationOverride(boolean verificationOverride) {
    this.verificationOverride = verificationOverride;
    return this;
  }

  public AchRequest setUserToken(String userToken) {
    this.userToken = userToken;
    return this;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public String getNameOnAccount() {
    return nameOnAccount;
  }

  public String getAccountType() {
    return accountType;
  }

  public String getBankName() {
    return bankName;
  }

  public String getRoutingNumber() {
    return routingNumber;
  }

  public boolean isVerificationOverride() {
    return verificationOverride;
  }

  public String getUserToken() {
    return userToken;
  }
}
