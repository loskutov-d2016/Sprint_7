package praktikum.courier;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import java.net.HttpURLConnection;

public class CourierChecks {
    @Step("успешный логин")
    public int loginSuccess(ValidatableResponse loginResponse) {
        return loginResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract()
                .path("id");
    }

    @Step("успешное создание курьера")
    public void created(ValidatableResponse createResponse) {
        createResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_CREATED)
                .extract()
                .path("ok");
    }

    @Step("проверка ошибки")
    public void checkError(ValidatableResponse response, int expectedStatusCode) {
        response.assertThat().statusCode(expectedStatusCode);
    }
}