package com.app.model;

import com.app.enums.Category;
import lombok.Getter;

import java.util.Objects;

@Getter
public class OrderItem {
    private String productName;
    private int quantity;
    private double price;
    private Category category;

    public OrderItem(String productName,
                     int quantity,
                     double price,
                     Category category){
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.category = category;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;

        OrderItem other = (OrderItem) obj;

        return Objects.equals(this.productName, other.productName)
                && this.quantity == other.quantity
                && this.price == other.price
                && Objects.equals(this.category, other.category);
    }

    @Override
    public int hashCode(){
        return Objects.hash(productName, quantity, price, category);
    }
}
