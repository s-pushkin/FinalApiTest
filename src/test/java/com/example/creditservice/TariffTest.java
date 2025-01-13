package com.example.creditservice;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;

public class TariffTest extends BaseTest {

    @Test
    public void testGetTariffs() {
        RestAssured.given()
                .header("Authorization", "Bearer " + authToken) // Добавляем токен
                .get("/loan-service/getTariffs")
                .then()
                .statusCode(200)
                .body("data.tariffs[0].id", equalTo(1))
                .body("data.tariffs[0].type", equalTo("CONSUMER"))
                .body("data.tariffs[0].interest_rate", equalTo("11.9%")); // Убедитесь, что поле называется правильно
    }
}