package com.app;

import com.app.enums.OrderStatus;
import com.app.model.Customer;
import com.app.model.Order;
import com.app.model.OrderItem;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Metrics {
    /**
     * @param orders List of input orders
     * @return List of unique cities where orders came from
     */
    public static List<String> findUniqueCities(List<Order> orders){
         return orders.stream()
                .map(order -> order.getCustomer().getCity())
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * @param orders List of input orders
     * @return Total income for all completed orders
     */
    public static Double calculateTotalIncome(List<Order> orders){
        return orders.stream()
                .filter(order -> order.getStatus() == OrderStatus.DELIVERED)
                .mapToDouble(order -> {
                    return order.getItems().stream()
                            .map(orderItem -> orderItem.getPrice() * orderItem.getQuantity())
                            .mapToDouble(Double::doubleValue)
                            .sum();
                })
                .sum();
    }

    /**
     * @param orders List of input orders
     * @return The most popular product by sales
     */
    public static List<String> findMostPopularProduct(List<Order> orders){
        Map<String, Integer> productsAndTheirSales =
                orders.stream()
                .flatMap(order -> order.getItems().stream())
                .collect(Collectors.groupingBy(
                        OrderItem::getProductName,
                        Collectors.summingInt(OrderItem::getQuantity)
                ));

        int maxSalesAmount =
                productsAndTheirSales
                        .values()
                        .stream()
                        .max(Integer::compare)
                        .orElseThrow(() -> new RuntimeException("No products in map"));

        return productsAndTheirSales.entrySet().stream()
                .filter(pair -> pair.getValue() == maxSalesAmount)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    /**
     * @param orders List of input orders
     * @return Average check for successfully delivered orders
     */
    public static Double calculateAverageCheckForSuccessfulOrders(List<Order> orders){
        return orders.stream()
                .filter(order -> order.getStatus() == OrderStatus.DELIVERED)
                .mapToDouble(order ->{
                    return order.getItems().stream()
                            .mapToDouble(item -> item.getPrice() * item.getQuantity())
                            .sum();
                })
                .average()
                .orElseThrow();
    }

    /**
     * @param orders List of input orders
     * @return Customers who have more than 5 orders
     */
   public static List<Customer> countCustomersWithMoreThanFiveOrders(List<Order> orders) {
        return orders.stream()
                        .collect(Collectors.groupingBy(
                               Order::getCustomer,
                               Collectors.counting()
                        ))
                        .entrySet()
                        .stream()
                        .filter(pair -> pair.getValue() > 5)
                        .map(Map.Entry::getKey)
                        .collect(Collectors.toList());
   }
}
