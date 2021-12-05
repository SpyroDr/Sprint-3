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
    public ValidatableResponse getList(int limit ){
        return given()
                .spec(getBaseSpec())
                .when()
                .get(ORDERS_PATH+"?limit="+limit+"&page=0")
                .then();
    }

    @Step("Получение заказа по трекеру")
    public ValidatableResponse getTrack(int track){
        return given()
                .spec(getBaseSpec())
                .when()
                .get(ORDERS_PATH+"/track?t="+track)
                .then()
                .log().body();
    }


    @Step("Удалить заказ")
    public ValidatableResponse deleteTrack(int orderId){
        return given()
                .spec(getBaseSpec())
                .and()
                .body("{\"id\":"+orderId+"}")
                .log().body()
                .when()
                .put(ORDERS_PATH + "/finish/"+orderId)
                .then()
                .log().body();
    }
}
