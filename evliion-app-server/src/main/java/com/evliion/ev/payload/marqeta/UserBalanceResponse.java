package com.evliion.ev.payload.marqeta;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class UserBalanceResponse {
  @JsonProperty("error_code")
  private String errorCode;

  @JsonProperty("error_message")
  private String errorMessage;

  private GpaBalance gpa;

  private static class GpaBalance {
    @JsonProperty("available_balance")
    private Double availableBalance;

    @JsonProperty("ledger_balance")
    private Double ledgerBalance;

    @JsonProperty("currency_code")
    private String currencyCode;

    @JsonProperty("credit_balance")
    private Double creditBalance;

    @JsonProperty("pending_credits")
    private Double pendingCredits;

    private Map<String, GpaBalance> balances;

    public Double getAvailableBalance() {
      return availableBalance;
    }

    public void setAvailableBalance(Double availableBalance) {
      this.availableBalance = availableBalance;
    }

    public Double getLedgerBalance() {
      return ledgerBalance;
    }

    public void setLedgerBalance(Double ledgerBalance) {
      this.ledgerBalance = ledgerBalance;
    }

    public String getCurrencyCode() {
      return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
      this.currencyCode = currencyCode;
    }

    public Double getCreditBalance() {
      return creditBalance;
    }

    public void setCreditBalance(Double creditBalance) {
      this.creditBalance = creditBalance;
    }

    public Double getPendingCredits() {
      return pendingCredits;
    }

    public void setPendingCredits(Double pendingCredits) {
      this.pendingCredits = pendingCredits;
    }

    public Map<String, GpaBalance> getBalances() {
      return balances;
    }

    public void setBalances(Map<String, GpaBalance> balances) {
      this.balances = balances;
    }
  }

  public String getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public GpaBalance getGpa() {
    return gpa;
  }

  public void setGpa(GpaBalance gpa) {
    this.gpa = gpa;
  }
}
