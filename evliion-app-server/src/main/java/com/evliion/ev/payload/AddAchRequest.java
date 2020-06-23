package com.evliion.ev.payload;

public class AddAchRequest {
  private String accountNumber;
  private String nameOnAccount;
  private AccountType accountType;
  private String bankName;
  private String routingNumber;

  private boolean isDefaultAccount;

  public boolean isDefaultAccount() {
    return isDefaultAccount;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public String getNameOnAccount() {
    return nameOnAccount;
  }

  public AccountType getAccountType() {
    return accountType;
  }

  public String getBankName() {
    return bankName;
  }

  public String getRoutingNumber() {
    return routingNumber;
  }
}
