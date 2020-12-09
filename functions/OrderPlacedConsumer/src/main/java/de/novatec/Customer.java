package de.novatec;

public class Customer {
  private int id;
  private String name;
  private Address address;

  public int getId() {
    return id;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return String.format("Customer(id=%d, name=%s, address=%s)", id, name, address.toString());
  }
}
