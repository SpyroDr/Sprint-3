import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;



public class OrderClient extends RestAssuredClient{

    private static final String ORDERS_PATH = "/api/v1/orders";

    @Step("Создание заказа")
    public ValidatableResponse create(Order order) {
        return given()
                .spec(getBaseSpec())
                .and()
                .body(order)
                .log().body()
                .when()
                .post(ORDERS_PATH)
                .then()
                .log().body();
    }

    @Step("Получение списка заказов")
    public ValidatableResponse getList(int limit, int page){
        return given()
                .spec(getBaseSpec())
                .queryParam("limit", limit)
                .queryParam("page", page)
                .when()
                .get(ORDERS_PATH)
                .then();
    }

    @Step("Получение заказа по трекеру")
    public ValidatableResponse getTrack(int track){
        return given()
                .spec(getBaseSpec())
                .when()
                .queryParam("t", track)
                .get(ORDERS_PATH+"/track")
                .then()
                .log().body();
    }


    @Step("Удалить заказ")
    public ValidatableResponse deleteTrack(int orderId){
        return given()
                .spec(getBaseSpec())
                .and()
                .body(String.format("{\"id\":%s}",orderId))
                .log().body()
                .when()
                .put(ORDERS_PATH + "/finish/"+orderId)
                .then()
                .log().body();
    }
}
