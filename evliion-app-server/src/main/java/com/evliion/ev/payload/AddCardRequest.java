package com.evliion.ev.payload;

public class AddCardRequest {
  private String accountNumber;
  private String cvvNumber;
  private String expMonth;
  private String expYear;
  private boolean isDefaultAccount;

  public boolean isDefaultAccount() {
    return isDefaultAccount;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public String getCvvNumber() {
    return cvvNumber;
  }

  public String getExpYear() {
    return expYear;
  }

  public String getExpMonth() {
    return expMonth;
  }
}
