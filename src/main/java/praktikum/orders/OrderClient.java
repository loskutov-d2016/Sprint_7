package praktikum.orders;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import praktikum.Client;

public class OrderClient extends Client {
    private static final String ORDERS = "orders";

    @Step("Создать заказ")
    public ValidatableResponse createOrder(Order order) {
        return spec()
                .body(order)
                .when()
                .post(ORDERS)
                .then().log().all();
    }

    @Step("Получить список заказов")
    public ValidatableResponse getOrders() {
        return spec()
                .when()
                .get(ORDERS)
                .then().log().all();
    }
}