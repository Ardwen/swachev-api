package com.evliion.ev.payload;

public enum AccountType {
  SAVINGS("savings"),
  CHECKING("checking"),
  CORPORATE("corporate"),
  LOAN("loan");

  private String value;

  AccountType(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
