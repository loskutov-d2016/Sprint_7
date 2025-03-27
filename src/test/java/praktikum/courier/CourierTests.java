package praktikum.courier;

import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import com.github.javafaker.Faker;

public class CourierTests {
    private final CourierClient courierClient = new CourierClient();
    private final Faker faker = new Faker();

    @Test
    public void createCourierTest() {
        Courier courier = new Courier(faker.name().username(), faker.internet().password(), faker.name().fullName());
        ValidatableResponse response = courierClient.createCourier(courier);
        response.statusCode(201);
        assertTrue(response.extract().path("ok"));
    }

    @Test
    public void createDuplicateCourierTest() {
        Courier courier = new Courier(faker.name().username(), faker.internet().password(), faker.name().fullName());
        courierClient.createCourier(courier);
        ValidatableResponse response = courierClient.createCourier(courier);
        response.statusCode(409);
        assertEquals("Этот логин уже используется.", response.extract().path("message"));
    }

    @Test
    public void createCourierWithoutRequiredFieldsTest() {
        ValidatableResponse response = courierClient.createCourier(new Courier("", "", ""));
        response.statusCode(400);
        assertEquals("Недостаточно данных для создания учетной записи", response.extract().path("message"));
    }

    @Test
    public void loginCourierTest() {
        String username = faker.name().username();
        String password = faker.internet().password();
        Courier courier = new Courier(username, password, faker.name().fullName());
        courierClient.createCourier(courier);

        Credentials creds = new Credentials(username, password);
        ValidatableResponse response = courierClient.logIn(creds);
        response.statusCode(200);
        int id = response.extract().path("id");
        assertTrue(id > 0);
    }

    @Test
    public void loginWithoutCredentialsTest() {
        Credentials creds = new Credentials("", "");
        ValidatableResponse response = courierClient.logIn(creds);
        response.statusCode(400);
        assertEquals("Недостаточно данных для входа", response.extract().path("message"));
    }

    @Test
    public void loginWithInvalidCredentialsTest() {
        Credentials creds = new Credentials("InvalidLogin", "InvalidPassword");
        ValidatableResponse response = courierClient.logIn(creds);
        response.statusCode(404);
        assertEquals("Учетная запись не найдена", response.extract().path("message"));
    }

    @Test
    public void createCourierWithInvalidLoginTest() {
        Courier courier = new Courier(faker.name().username(), faker.internet().password(), faker.name().fullName());
        ValidatableResponse createResponse = courierClient.createCourier(courier);
        assertTrue(createResponse.extract().path("ok"));

        Credentials creds = new Credentials("InvalidLogin", courier.getPassword());
        ValidatableResponse response = courierClient.logIn(creds);
        response.statusCode(404);
        assertEquals("Учетная запись не найдена", response.extract().path("message"));
    }

    @Test
    public void deleteCourierTest() {
        Courier courier = new Courier(faker.name().username(), faker.internet().password(), faker.name().fullName());
        ValidatableResponse createResponse = courierClient.createCourier(courier);
        assertTrue(createResponse.extract().path("ok"));

        Credentials creds = new Credentials(courier.getLogin(), courier.getPassword());
        ValidatableResponse loginResponse = courierClient.logIn(creds);

        loginResponse.statusCode(200);
        int id = loginResponse.extract().path("id");

        ValidatableResponse deleteResponse = courierClient.delete(id);
        deleteResponse.statusCode(200);
        assertTrue(deleteResponse.extract().path("ok"));
    }
}