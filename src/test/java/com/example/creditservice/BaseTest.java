package com.example.creditservice;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    protected static String authToken;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8080";

        // Аутентификация и получение токена
        String authRequestBody = """
            {
                "email": "ivanov@mail.ru",
                "password": "1234"
            }
            """;

        Response authResponse = RestAssured.given()
                .contentType("application/json")
                .body(authRequestBody)
                .post("/auth/authenticate");

        authToken = authResponse.jsonPath().getString("token"); // Предположим, что токен возвращается в поле "token"
    }
}
