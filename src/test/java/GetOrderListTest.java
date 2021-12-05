import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.emptyArray;
import static org.hamcrest.core.IsNot.not;

public class GetOrderListTest {
    private OrderClient orderClient;
    private ValidatableResponse response;
    private int limit;

    @Before
    public void setUp() {
        orderClient = new OrderClient();
    }

    @Test
    @Story("Получение списка заказов")
    @DisplayName("Создание и получение заказа. Позитивный сценарий")
    @Description("Создать заказ и получить список заказов")

    public void getOrdersList() {
        // Arrange
        Order order = Order.Randomize();

        // Act
        orderClient.create(order);
        response = orderClient.getList(limit);

        // Assert
        response.assertThat().statusCode(200);
        response.assertThat().body("data.orders", not(emptyArray()));
    }

}
