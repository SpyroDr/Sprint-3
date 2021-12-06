import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CreateCourierNegativeTests {

    private CourierClient courierClient;
    //private int courierId;


    @Before

    public void setUp() {
        courierClient = new CourierClient();
    }


    @Test
    @Story("Создание курьера")
    @DisplayName("Создание курьера без логина. Негативный сценарий")
    @Description("Создать курьера без логина")

    public void courierWithoutLogin() {
        // Arrange
        Courier courier = new Courier(null, "password11", "hna11");


        // Act
        ValidatableResponse courierNotCreated = courierClient.create(courier);
        //courierId = courierClient.login(CourierCredentials.from(courier)).extract().path("id");
        //boolean isCourierDeleted = courierClient.delete(courierId);


        // Assert
        assertEquals("Статус не 400 conflict!",400, courierNotCreated.extract().statusCode());

    }

    @Test
    @Story("Создание курьера")
    @DisplayName("Создание курьера без пароля. Негативный сценарий")
    @Description("Создать курьера без пароля")

    public void courierWithoutPassword() {
        // Arrange
        Courier courier = new Courier("login", null, "hna");
        // Act
        ValidatableResponse courierNotCreated = courierClient.create(courier);

        // Assert
        assertEquals("Статус не 400 conflict!",400, courierNotCreated.extract().statusCode());

    }

    @Test
    @Story("Создание курьера")
    @DisplayName("Создание курьера без firstName. Негативный сценарий")
    @Description("Создать курьера без firstName")
    public void courierWithoutFirstName() {
        // Arrange
        Courier courier = new Courier("login", "password", null);

        // Act
        ValidatableResponse courierNotCreated = courierClient.create(courier);

        // Assert
        assertEquals("Статус не 409 conflict!",409, courierNotCreated.extract().statusCode());

    }

}