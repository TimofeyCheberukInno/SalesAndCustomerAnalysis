package com.app.model;

import com.app.enums.OrderStatus;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Getter
public class Order {
    private String orderId;
    private LocalDateTime orderDate;
    private Customer customer;
    private List<OrderItem> items;
    private OrderStatus status;

    public Order(String orderId,
                 LocalDateTime orderDate,
                 Customer customer,
                 List<OrderItem> items,
                 OrderStatus status){
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.customer = customer;
        this.items = items;
        this.status = status;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null || obj.getClass() != this.getClass()) return false;

        Order other = (Order) obj;

        return Objects.equals(this.orderId, other.orderId)
                && Objects.equals(this.orderDate, other.orderDate)
                && Objects.equals(this.customer, other.customer)
                && Objects.equals(this.items, other.items)
                && Objects.equals(this.status, other.status);
    }

    @Override
    public int hashCode(){
        return Objects.hash(orderId, orderDate, customer, items, status);
    }
}
