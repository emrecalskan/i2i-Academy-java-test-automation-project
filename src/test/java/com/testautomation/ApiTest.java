package com.testautomation;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;

import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ApiTest {

    @Test
    public void shouldGetSinglePost() {

        Response response = given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .when()
                .get("/posts/1")
                .then()
                .log().all()

                // HTTP durum kodu kontrolü
                .statusCode(200)

                // Yanıtın 5 saniyeden kısa gelmesi beklenir
                .time(lessThan(5000L))

                // JSON body kontrolleri
                .body("id", equalTo(1))
                .body("userId", equalTo(1))
                .body("title", not(emptyString()))

                // Response nesnesini test sonunda al
                .extract()
                .response();

        System.out.println(
                "GET response time: " + response.time() + " ms");
    }

    @Test
    public void shouldCreateNewPost() {

        String requestBody = """
                {
                    "title": "Test Automation",
                    "body": "REST Assured POST request",
                    "userId": 1
                }
                """;

        Response response = given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/posts")
                .then()
                .log().all()

                // HTTP durum kodu kontrolü
                .statusCode(201)

                // Yanıt süresi kontrolü
                .time(lessThan(5000L))

                // Response body kontrolleri
                .body("title", equalTo("Test Automation"))
                .body("body", equalTo("REST Assured POST request"))
                .body("userId", equalTo(1))
                .body("id", equalTo(101))

                .extract()
                .response();

        System.out.println(
                "POST response time: " + response.time() + " ms");
    }
}