package praktikum.orders;

import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Client;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class OrderTests extends Client {
    private OrderClient orderClient;

    @Parameterized.Parameter
    public OrderData orderData;

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][] {
                { new OrderData("Naruto", "Uchiha", "Konoha, 142 apt.", 4, "+7 800 355 35 35", 5, "2020-06-06", "Saske, come back to Konoha", List.of("BLACK")) },
                { new OrderData("Naruto", "Uchiha", "Konoha, 142 apt.", 4, "+7 800 355 35 35", 5, "2020-06-06", "Saske, come back to Konoha", List.of("BLACK", "GREY")) },
                { new OrderData("Naruto", "Uchiha", "Konoha, 142 apt.", 4, "+7 800 355 35 35", 5, "2020-06-06", "Saske, come back to Konoha", List.of()) }
        };
    }

    @Before
    public void setUp() {
        orderClient = new OrderClient();
    }

    @Test
    public void createOrder() {
        Order order = new Order(orderData.firstName, orderData.lastName, orderData.address, orderData.metroStation,
                orderData.phone, orderData.rentTime, orderData.deliveryDate, orderData.comment, orderData.color);
        ValidatableResponse response = orderClient.createOrder(order);
        response.statusCode(201);

        Integer track = response.extract().path("track");
        assertNotNull("Track should not be null", track);
        assertTrue("Track should be an Integer", track instanceof Integer);
    }
}