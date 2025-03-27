package praktikum.orders;

import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import praktikum.Client;

import java.util.List;

import static org.junit.Assert.*;

public class GetOrdersListTest extends Client {
    private OrderClient orderClient;

    @Before
    public void setUp() {
        orderClient = new OrderClient();
    }

    @Test
    public void getOrdersList() {
        ValidatableResponse response = orderClient.getOrders();
        response.statusCode(200);

        List<?> orders = response.extract().path("orders");
        assertNotNull("Orders list should not be null", orders);
        assertTrue("Orders should be a List", orders instanceof List);
    }
}