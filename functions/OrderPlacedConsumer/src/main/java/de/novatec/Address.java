package de.novatec;

public class Address {
  private String city;
  private String street;
  private String zip;
  private String country;

  public String getCity() {
    return city;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public void setCity(String city) {
    this.city = city;
  }

  @Override
  public String toString() {
    return String.format("Address(city=%s, street=%s, zip=%s, country=%s)", city, street, zip, country);
  }
}
