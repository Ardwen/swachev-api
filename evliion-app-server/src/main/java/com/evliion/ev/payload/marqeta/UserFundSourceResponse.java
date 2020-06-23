package com.evliion.ev.payload.marqeta;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class UserFundSourceResponse {
  private UserFundSource[] data;

  public UserFundSource[] getData() {
    return data;
  }

  public UserFundSourceResponse setData(UserFundSource[] data) {
    this.data = data;
    return this;
  }

  public static class UserFundSource {

    @JsonProperty("created_time")
    private String createdTime;

    @JsonProperty("last_modified_time")
    private String lastModifiedTime;

    private String type;

    private String token;

    @JsonProperty("account_suffix")
    private String accountSuffix;

    @JsonProperty("exp_date")
    private String expDate;

    @JsonProperty("name_on_account")
    private String nameOnAccount;

    public String getCreatedTime() {
      return createdTime;
    }

    public UserFundSource setCreatedTime(String createdTime) {
      this.createdTime = createdTime;
      return this;
    }

    public String getLastModifiedTime() {
      return lastModifiedTime;
    }

    public UserFundSource setLastModifiedTime(String lastModifiedTime) {
      this.lastModifiedTime = lastModifiedTime;
      return this;
    }

    public String getType() {
      return type;
    }

    public UserFundSource setType(String type) {
      this.type = type;
      return this;
    }

    public String getToken() {
      return token;
    }

    public UserFundSource setToken(String token) {
      this.token = token;
      return this;
    }

    public String getAccountSuffix() {
      return accountSuffix;
    }

    public UserFundSource setAccountSuffix(String accountSuffix) {
      this.accountSuffix = accountSuffix;
      return this;
    }

    public String getExpDate() {
      return expDate;
    }

    public UserFundSource setExpDate(String expDate) {
      this.expDate = expDate;
      return this;
    }

    public String getNameOnAccount() {
      return nameOnAccount;
    }

    public UserFundSource setNameOnAccount(String nameOnAccount) {
      this.nameOnAccount = nameOnAccount;
      return this;
    }
  }
}
