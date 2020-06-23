package com.evliion.ev.payload;

public class BillingAddressResponse {
  private String firstName;
  private String lastName;
  private String address1;
  private String address2;
  private String city;
  private String state;
  private String zip;
  private String postalCode;
  private String country;

  private String id;

  public String getId() {
    return id;
  }

  public BillingAddressResponse setId(String id) {
    this.id = id;
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

  public BillingAddressResponse setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public BillingAddressResponse setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public BillingAddressResponse setAddress1(String address1) {
    this.address1 = address1;
    return this;
  }

  public BillingAddressResponse setAddress2(String address2) {
    this.address2 = address2;
    return this;
  }

  public BillingAddressResponse setCity(String city) {
    this.city = city;
    return this;
  }

  public BillingAddressResponse setState(String state) {
    this.state = state;
    return this;
  }

  public BillingAddressResponse setZip(String zip) {
    this.zip = zip;
    return this;
  }

  public BillingAddressResponse setPostalCode(String postalCode) {
    this.postalCode = postalCode;
    return this;
  }

  public BillingAddressResponse setCountry(String country) {
    this.country = country;
    return this;
  }
}
