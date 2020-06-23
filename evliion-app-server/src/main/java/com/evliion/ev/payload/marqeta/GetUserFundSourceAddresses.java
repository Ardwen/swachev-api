package com.evliion.ev.payload.marqeta;

public class GetUserFundSourceAddresses {
  private AddFundingSourceAddressRequest[] data;

  public AddFundingSourceAddressRequest[] getData() {
    return data;
  }

  public GetUserFundSourceAddresses setData(AddFundingSourceAddressRequest[] data) {
    this.data = data;
    return this;
  }
}
