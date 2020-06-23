package com.evliion.ev.payload;

public class GetWalletResponse {
  private FundSourceResponse[] fundSources;
  private BillingAddressResponse[] billingAddresses;

  public FundSourceResponse[] getFundSources() {
    return fundSources;
  }

  public GetWalletResponse setFundSources(FundSourceResponse[] fundSources) {
    this.fundSources = fundSources;
    return this;
  }

  public BillingAddressResponse[] getBillingAddresses() {
    return billingAddresses;
  }

  public GetWalletResponse setBillingAddresses(BillingAddressResponse[] billingAddresses) {
    this.billingAddresses = billingAddresses;
    return this;
  }
}
