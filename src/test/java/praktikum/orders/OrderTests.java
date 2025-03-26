package praktikum.orders;

import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import praktikum.Client;

import java.util.List;

import static org.junit.Assert.*;

public class OrderTests extends Client {
    private OrderClient orderClient;

    @Before
    public void setUp() {
        orderClient = new OrderClient();
    }

    @Test
    public void createOrderWithColor() {
        Order order = new Order("Naruto", "Uchiha", "Konoha, 142 apt.", 4,
                "+7 800 355 35 35", 5, "2020-06-06",
                "Saske, come back to Konoha", List.of("BLACK"));
        ValidatableResponse response = orderClient.createOrder(order);
        response.statusCode(201);
        assertNotNull(response.extract().path("track"));
    }

    @Test
    public void createOrderWithMultipleColors() {
        Order order = new Order("Naruto", "Uchiha", "Konoha, 142 apt.", 4,
                "+7 800 355 35 35", 5, "2020-06-06",
                "Saske, come back to Konoha", List.of("BLACK", "GREY"));
        ValidatableResponse response = orderClient.createOrder(order);
        response.statusCode(201);
        assertNotNull(response.extract().path("track"));
    }

    @Test
    public void createOrderWithoutColor() {
        Order order = new Order("Naruto", "Uchiha", "Konoha, 142 apt.", 4,
                "+7 800 355 35 35", 5, "2020-06-06",
                "Saske, come back to Konoha", List.of());
        ValidatableResponse response = orderClient.createOrder(order);
        response.statusCode(201);
        assertNotNull(response.extract().path("track"));
    }

    @Test
    public void getOrdersList() {
        ValidatableResponse response = orderClient.getOrders();
        response.statusCode(200);
        assertNotNull(response.extract().path("orders"));
    }
}