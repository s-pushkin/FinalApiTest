package com.example.creditservice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoanOrderTest extends BaseTest {

    @Test
    public void testCreateLoanOrder() {
        String requestBody = """
            {
                "userId": 120356894755,
                "tariffId": 1
            }
            """;

        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + authToken) // Добавляем токен
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post("/loan-service/order");

        assertEquals(200, response.getStatusCode());
        response.then().body("data.orderId", notNullValue());
    }

    @Test
    public void testCreateLoanOrderWithInvalidTariff() {
        String requestBody = """
            {
                "userId": 120356894755,
                "tariffId": 999
            }
            """;

        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + authToken) // Добавляем токен
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post("/loan-service/order");

        assertEquals(400, response.getStatusCode());
        response.then().body("error.code", equalTo("TARIFF_NOT_FOUND"));
    }
}