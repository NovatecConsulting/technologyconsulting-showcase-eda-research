package de.novatec;

import java.time.LocalDateTime;

public class Order {
  private int orderId;
  private int customerId;
  private LocalDateTime timestamp;
  private String name;

  public Order() {
    timestamp = LocalDateTime.now();
  }

  public int getOrderId() {
    return orderId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
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
    return String.format("Order(orderId=%d, customerId=%d, timestamp='%s', name='%s')", orderId, customerId, timestamp.toString(), name);
  }
}
