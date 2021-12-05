import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoginCourierNegativeTests {

    private CourierClient courierClient;
    private int courierId;

    @Before

    public void setUp() {
        courierClient = new CourierClient();
    }

    /*@After
   public void tearDown() {
        courierClient.delete(courierId);
    }*/

    @Test
    @Story("Авторизация курьера")
    @DisplayName("Авторизация курьера с невалидными данными. Негативный сценарий")
    @Description("Авторизовать курьера с невалидными данными")
    public void courierCanBeLoginWithInvalidData() {
        // Arrange
        Courier courier = new Courier("login", "password", "hn");
        courierId = courierClient.login(CourierCredentials.from(courier)).extract().path("id");
        courierClient.delete(courierId);

        // Act
        ValidatableResponse  statusCodeNegativeResponse = courierClient.login(CourierCredentials.from(courier));

        // Assert
        assertEquals("Статус не 404 conflict!",404, statusCodeNegativeResponse.extract().statusCode());

    }

    @Test
    @Story("Авторизация курьера")
    @DisplayName("Авторизация курьера без логина. Негативный сценарий")
    @Description("Авторизовать курьера без логина")
    public void courierCanBeLoginWithNullLogin() {
        // Arrange
        Courier courier = new Courier(null, "password2", "hn2");

        // Act
        ValidatableResponse  statusCodeNegativeResponse = courierClient.login(CourierCredentials.from(courier));

        // Assert
        assertEquals("Статус не 400 conflict!",400, statusCodeNegativeResponse.extract().statusCode());

    }

    @Test
    @Story("Авторизация курьера")
    @DisplayName("Авторизация курьера без пароля. Негативный сценарий")
    @Description("Авторизовать курьера без пароля")
    public void courierCanBeLoginWithNullPassword()
    {
        // Arrange
        Courier courier = new Courier("login", null, "hn2");

        // Act
        ValidatableResponse  statusCodeNegativeResponse = courierClient.login(CourierCredentials.from(courier));

        // Assert
        assertEquals("Статус не 400 conflict!",400, statusCodeNegativeResponse.extract().statusCode());

    }

}
