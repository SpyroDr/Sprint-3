import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CreateCourierTest {

    private CourierClient courierClient;
    private int courierId;


    @Before
    public void setUp() {
        courierClient = new CourierClient();
    }

    @After
    public void tearDown() {
        courierClient.delete(courierId);
    }


    @Test
    @Story("Создание курьера")
    @Description("Создание курьера. Позитивный сценарий")
    @DisplayName("Создание курьера. Позитивный сценарий")
    public void courierCanBeCreatedWithValidData() {
        // Arrange
        Courier courier = Courier.getRandom();

        // Act
        boolean isCourierCreated = courierClient.create(courier).extract().path("ok");
        courierId = courierClient.login(CourierCredentials.from(courier)).extract().path("id");
        //boolean isCourierDeleted = courierClient.delete(courierId);

        // Assert
        assertTrue("Courier is not created", isCourierCreated);
        assertThat("Courier ID is incorrect", courierId, is(not(0)));

    }
    @Test
    @Story("Создание курьера")
    @DisplayName("Создание курьера с одинаковыми данными")
    @Description("Нельзя создать курьера с однинаковыми данными")
    public void courierCanNotBeEqual() {
        // Arrange
        Courier courier = Courier.getRandom();


        // Act
        courierClient.create(courier);
        courierId = courierClient.login(CourierCredentials.from(courier)).extract().path("id");
        ValidatableResponse  statusCodeNegativeResponse = courierClient.create(courier);


        // Assert
        assertEquals("Статус не 409 conflict!",409, statusCodeNegativeResponse.extract().statusCode());

    }

}
