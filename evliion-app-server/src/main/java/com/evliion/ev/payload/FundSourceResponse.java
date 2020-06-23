package com.evliion.ev.payload;

public class FundSourceResponse {

  private String createdTime;

  private String lastModifiedTime;

  private String type;

  private String id;

  private String accountSuffix;

  private String expDate;

  private String nameOnAccount;

  public String getCreatedTime() {
    return createdTime;
  }

  public FundSourceResponse setCreatedTime(String createdTime) {
    this.createdTime = createdTime;
    return this;
  }

  public String getLastModifiedTime() {
    return lastModifiedTime;
  }

  public FundSourceResponse setLastModifiedTime(String lastModifiedTime) {
    this.lastModifiedTime = lastModifiedTime;
    return this;
  }

  public String getType() {
    return type;
  }

  public FundSourceResponse setType(String type) {
    this.type = type;
    return this;
  }

  public String getId() {
    return id;
  }

  public FundSourceResponse setId(String id) {
    this.id = id;
    return this;
  }

  public String getAccountSuffix() {
    return accountSuffix;
  }

  public FundSourceResponse setAccountSuffix(String accountSuffix) {
    this.accountSuffix = accountSuffix;
    return this;
  }

  public String getExpDate() {
    return expDate;
  }

  public FundSourceResponse setExpDate(String expDate) {
    this.expDate = expDate;
    return this;
  }

  public String getNameOnAccount() {
    return nameOnAccount;
  }

  public FundSourceResponse setNameOnAccount(String nameOnAccount) {
    this.nameOnAccount = nameOnAccount;
    return this;
  }
}
