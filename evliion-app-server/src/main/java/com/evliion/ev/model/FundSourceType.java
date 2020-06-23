package com.evliion.ev.model;

public enum FundSourceType {
  CARD("CARD"),
  ACH("ACH");

  private String value;

  FundSourceType(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
