package fr.guddy.test;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

final class FirstRestAssured {

    @RegisterExtension
    final UserApiExtension userApiExtension = new UserApiExtension(7000);

    @BeforeAll
    static void beforeAll() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 7000;
    }

    @Test
    void testHelloWorld() {
        given().get("/")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(equalTo("Hello World"));
    }
}
