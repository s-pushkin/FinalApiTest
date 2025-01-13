package com.example.creditservice;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderStatusTest extends BaseTest {

    @ParameterizedTest
    @ValueSource(strings = {"37f38d66-6c55-43b5-b981-0f9fd0b448d7", "invalid-order-id"})
    public void testGetOrderStatus(String orderId) {
        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + authToken) // Добавляем токен
                .get("/loan-service/getStatusOrder?orderId=" + orderId);

        if (orderId.equals("invalid-order-id")) {
            assertEquals(400, response.getStatusCode());
            response.then().body("error.code", equalTo("ORDER_NOT_FOUND"));
        } else {
            assertEquals(200, response.getStatusCode());
            response.then().body("data.orderStatus", notNullValue());
        }
    }
}