package com.app;

import com.app.enums.Category;
import com.app.enums.OrderStatus;
import com.app.model.Customer;
import com.app.model.Order;
import com.app.model.OrderItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MetricsTest {
    private List<Order> orders;

    @BeforeEach
    void setUp(TestInfo testInfo){
        OrderItem orderItem1 = new OrderItem("Smartphone", 1, 799.99, Category.ELECTRONICS); // x2
        OrderItem orderItem2 = new OrderItem("T-Shirt", 2, 19.99, Category.CLOTHING); // x1
        OrderItem orderItem3 = new OrderItem("Java Programming Book", 1, 49.99, Category.BOOKS);
        OrderItem orderItem4 = new OrderItem("Blender", 1, 59.95, Category.HOME);
        OrderItem orderItem5 = new OrderItem("Lipstick", 3, 12.50, Category.BEAUTY);
        OrderItem orderItem6 = new OrderItem("Lego Set", 1, 89.99, Category.TOYS);
        OrderItem orderItem7 = new OrderItem("Wireless Earbuds", 1, 129.99, Category.ELECTRONICS);
        OrderItem orderItem8 = new OrderItem("Jeans", 1, 45.00, Category.CLOTHING);
        OrderItem orderItem9 = new OrderItem("Cookbook", 1, 24.99, Category.BOOKS);
        OrderItem orderItem10 = new OrderItem("Perfume", 1, 65.75, Category.BEAUTY);

        List<OrderItem> orderItems = List.of(
                orderItem1,
                orderItem2,
                orderItem3,
                orderItem4,
                orderItem5,
                orderItem6,
                orderItem7,
                orderItem8,
                orderItem9,
                orderItem10);

        if(testInfo.getTestMethod().orElseThrow().getName().equals("countCustomersWithMoreThanFiveOrdersSuccessfulTest2")){
            orders = buildOrdersWithSixDuplicateCustomers(orderItems);
        }
        else{
            orders = buildOrders(orderItems);
        }
    }

    @DisplayName("findUniqueCities(List<Order>) successful test")
    @Test
    void findUniqueCitiesSuccessfulTest(){
        List<String> expectedList = new ArrayList<>(List.of("Minsk", "Grodno", "Brest", "Vitebsk"));
        assertEquals(expectedList, Metrics.findUniqueCities(orders));
    }

    @DisplayName("calculateTotalIncome(List<Order>) successful test")
    @Test
    void calculateTotalIncomeSuccessfulTest(){
        Double expectedIncome = 1118.15;
        assertEquals(expectedIncome, Metrics.calculateTotalIncome(orders));
    }

    @DisplayName("findMostPopularProduct(List<Order>) successful test")
    @Test
    void findMostPopularProductSuccessfulTest(){
        List<String> expectedList = new ArrayList<>(List.of("Lipstick"));
        assertEquals(expectedList, Metrics.findMostPopularProduct(orders));
    }

    @DisplayName("calculateAverageCheckForSuccessfulOrders(List<Order>) successful test")
    @Test
    void calculateAverageCheckForSuccessfulOrdersSuccessfulTest(){
        double allIncomeFromSuccessfulOrders = 1118.15;
        Double expectedAverage = allIncomeFromSuccessfulOrders / 2;
        assertEquals(expectedAverage, Metrics.calculateAverageCheckForSuccessfulOrders(orders));
    }

    @DisplayName("countCustomersWithMoreThanFiveOrders(List<Order>) successful test when answer is empty list")
    @Test
    void countCustomersWithMoreThanFiveOrdersSuccessfulTest1(){
        List<Customer> expectedList = new ArrayList<>();
        assertEquals(expectedList, Metrics.countCustomersWithMoreThanFiveOrders(orders));
    }

    @DisplayName("countCustomersWithMoreThanFiveOrders(List<Order>) successful test when answer is non-empty list")
    @Test
    void countCustomersWithMoreThanFiveOrdersSuccessfulTest2(){
        List<Customer> expectedList = new ArrayList<>(List.of(
                new Customer("Customer", "Timofey", "timofey@gmail.com",
                LocalDateTime.of(2012, 9, 1, 10, 0, 0), 28, "Minsk")
        ));
        assertEquals(expectedList, Metrics.countCustomersWithMoreThanFiveOrders(orders));
    }

    private List<Order> buildOrdersWithSixDuplicateCustomers(List<OrderItem> orderItems){
        Customer customerTimofey = new Customer("Customer", "Timofey", "timofey@gmail.com",
                LocalDateTime.of(2012, 9, 1, 10, 0, 0), 28, "Minsk");

        Order order1 = new Order("Order1",
                LocalDateTime.of(2012, 10, 29, 13, 56, 39),
                customerTimofey,
                List.of(
                        orderItems.get(1),
                        orderItems.get(2)
                ),
                OrderStatus.DELIVERED);

        Order order2 = new Order("Order2",
                LocalDateTime.of(2013, 5, 15, 9, 30, 0),
                customerTimofey,
                List.of(
                        orderItems.get(3),
                        orderItems.get(4)
                ),
                OrderStatus.SHIPPED);

        Order order3 = new Order("Order3",
                LocalDateTime.of(2014, 8, 22, 16, 45, 20),
                customerTimofey,
                List.of(
                        orderItems.get(5),
                        orderItems.get(6)
                ),
                OrderStatus.DELIVERED);

        Order order4 = new Order("Order4",
                LocalDateTime.of(2015, 3, 10, 11, 5, 55),
                customerTimofey,
                List.of(
                        orderItems.get(7),
                        orderItems.get(8)
                ),
                OrderStatus.CANCELLED);

        Order order5 = new Order("Order5",
                LocalDateTime.of(2016, 12, 5, 20, 15, 10),
                customerTimofey,
                List.of(
                        orderItems.get(9),
                        orderItems.get(0)
                ),
                OrderStatus.PROCESSING);

        Order order6 = new Order("Order6",
                LocalDateTime.of(2017, 2, 14, 12, 30, 0),
                customerTimofey,
                List.of(
                        orderItems.get(7),
                        orderItems.get(4)
                ),
                OrderStatus.DELIVERED);

        Order order7 = new Order("Order7",
                LocalDateTime.of(2014, 8, 22, 16, 45, 20),
                new Customer("Customer", "Ivan", "ivan@gmail.com",
                        LocalDateTime.of(2012, 9, 1, 10, 0, 0), 28, "Minsk"),
                List.of(
                        orderItems.get(1),
                        orderItems.get(9)
                ),
                OrderStatus.DELIVERED);

        return List.of(order1, order2, order3, order4, order5, order6, order7);
    }

    private List<Order> buildOrders(List<OrderItem> orderItems){
        Order order1 = new Order("Order1",
                LocalDateTime.of(2012, 10, 29, 13, 56, 39),
                new Customer("Customer1", "Timofey", "email1@gmail.com",
                        LocalDateTime.of(2012, 11, 29, 23, 6, 35), 15, "Minsk"),
                List.of(orderItems.get(0), orderItems.get(1), orderItems.get(4), orderItems.get(8)),
                OrderStatus.DELIVERED);

        Order order2 = new Order("Order2",
                LocalDateTime.of(2013, 5, 15, 9, 30, 0),
                new Customer("Customer2", "Anna", "email2@gmail.com",
                        LocalDateTime.of(2013, 1, 12, 12, 15, 0), 25, "Grodno"),
                List.of(orderItems.get(2), orderItems.get(6)),
                OrderStatus.SHIPPED);

        Order order3 = new Order("Order3",
                LocalDateTime.of(2014, 8, 22, 16, 45, 20),
                new Customer("Customer3", "Dmitry", "email3@gmail.com",
                        LocalDateTime.of(2014, 7, 5, 18, 30, 0), 32, "Brest"),
                List.of(orderItems.get(3), orderItems.get(5), orderItems.get(9)),
                OrderStatus.DELIVERED);

        Order order4 = new Order("Order4",
                LocalDateTime.of(2015, 3, 10, 11, 5, 55),
                new Customer("Customer4", "Olga", "email4@gmail.com",
                        LocalDateTime.of(2015, 2, 28, 14, 0, 0), 28, "Vitebsk"),
                List.of(orderItems.get(7), orderItems.get(8), orderItems.get(1), orderItems.get(4)),
                OrderStatus.CANCELLED);

        Order order5 = new Order("Order5",
                LocalDateTime.of(2016, 12, 5, 20, 15, 10),
                new Customer("Customer5", "Ivan", "email5@gmail.com",
                        LocalDateTime.of(2016, 11, 15, 10, 45, 0), 40, "Minsk"),
                List.of(orderItems.get(0), orderItems.get(2), orderItems.get(6), orderItems.get(9)),
                OrderStatus.PROCESSING);

        return List.of(order1, order2, order3, order4, order5);
    }
}
