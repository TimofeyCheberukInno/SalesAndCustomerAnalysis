package com.app.model;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class Customer {
    private String customerId;
    private String name;
    private String email;
    private LocalDateTime registeredAt;
    private int age;
    private String city;

    public Customer(String customerId,
                    String name,
                    String email,
                    LocalDateTime registeredAt,
                    int age,
                    String city){
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.registeredAt = registeredAt;
        this.age = age;
        this.city = city;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null || obj.getClass() != this.getClass()) return false;

        Customer other = (Customer) obj;

        return Objects.equals(this.customerId, other.customerId)
                && Objects.equals(this.name, other.name)
                && Objects.equals(this.email, other.email)
                && Objects.equals(this.registeredAt, other.registeredAt)
                && this.age == other.age
                && Objects.equals(this.city, other.city);
    }

    @Override
    public int hashCode(){
        return Objects.hash(customerId, name, email, registeredAt, age, city);
    }
}
