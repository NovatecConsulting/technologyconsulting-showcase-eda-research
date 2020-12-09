package de.novatec;

public class Order {
  private int orderId;
  private int customerId;
  private String product;
  private int amount;

  public int getOrderId() {
    return orderId;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public String getProduct() {
    return product;
  }

  public void setProduct(String product) {
    this.product = product;
  }

  public int getCustomerId() {
    return customerId;
  }

  public void setCustomerId(int customerId) {
    this.customerId = customerId;
  }

  public void setOrderId(int orderId) {
    this.orderId = orderId;
  }

  @Override
  public String toString() {
    return String.format("Order(orderId=%d, customerId=%d, product='%s', amount=%d)", orderId, customerId, product, amount);
  }
}
