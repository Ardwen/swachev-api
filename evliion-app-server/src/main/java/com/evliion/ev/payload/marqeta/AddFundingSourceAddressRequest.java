package com.evliion.ev.payload.marqeta;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddFundingSourceAddressRequest {

  @JsonProperty("first_name")
  private String firstName;

  @JsonProperty("last_name")
  private String lastName;

  @JsonProperty("address_1")
  private String address1;

  @JsonProperty("address_2")
  private String address2;

  private String city;

  private String state;

  private String zip;

  private String token;

  @JsonProperty("postal_code")
  private String postalCode;

  private String country;

  @JsonProperty("user_token")
  private String userToken;

  public AddFundingSourceAddressRequest setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public AddFundingSourceAddressRequest setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public AddFundingSourceAddressRequest setAddress1(String address1) {
    this.address1 = address1;
    return this;
  }

  public String getToken() {
    return token;
  }

  public AddFundingSourceAddressRequest setToken(String token) {
    this.token = token;
    return this;
  }

  public AddFundingSourceAddressRequest setAddress2(String address2) {
    this.address2 = address2;
    return this;
  }

  public AddFundingSourceAddressRequest setCity(String city) {
    this.city = city;
    return this;
  }

  public AddFundingSourceAddressRequest setState(String state) {
    this.state = state;
    return this;
  }

  public AddFundingSourceAddressRequest setZip(String zip) {
    this.zip = zip;
    return this;
  }

  public AddFundingSourceAddressRequest setPostalCode(String postalCode) {
    this.postalCode = postalCode;
    return this;
  }

  public AddFundingSourceAddressRequest setCountry(String country) {
    this.country = country;
    return this;
  }

  public AddFundingSourceAddressRequest setUserToken(String userToken) {
    this.userToken = userToken;
    return this;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getAddress1() {
    return address1;
  }

  public String getAddress2() {
    return address2;
  }

  public String getCity() {
    return city;
  }

  public String getState() {
    return state;
  }

  public String getZip() {
    return zip;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public String getCountry() {
    return country;
  }

  public String getUserToken() {
    return userToken;
  }
}
