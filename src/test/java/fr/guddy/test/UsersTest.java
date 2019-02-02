package fr.guddy.test;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public final class UsersTest {

    @RegisterExtension
    final UserApiExtension userApiExtension = new UserApiExtension(7000);

    @BeforeAll
    static void beforeAll() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 7000;
    }

    @Test
    public void testCreateOk() {
        given().post("/users")
                .then()
                .statusCode(HttpStatus.SC_FORBIDDEN)
                .body(equalTo("not implemented"));
    }

    @Test
    public void testCreateConflict() {
        given().post("/users")
                .then()
                .statusCode(HttpStatus.SC_FORBIDDEN)
                .body(equalTo("not implemented"));
    }

    @Test
    public void testGetAllOk() {
        given().get("/users")
                .then()
                .statusCode(HttpStatus.SC_FORBIDDEN)
                .body(equalTo("not implemented"));
    }

    @Test
    public void testGetAllNoContent() {
        given().post("/users")
                .then()
                .statusCode(HttpStatus.SC_FORBIDDEN)
                .body(equalTo("not implemented"));
    }

    @Test
    public void testGetOneOk() {
        given().get("/users/123")
                .then()
                .statusCode(HttpStatus.SC_FORBIDDEN)
                .body(equalTo("not implemented"));
    }

    @Test
    public void testGetOneNotFound() {
        given().get("/users/romain.rochegude")
                .then()
                .statusCode(HttpStatus.SC_FORBIDDEN)
                .body(equalTo("not implemented"));
    }

    @Test
    public void testDeleteOk() {
        given().delete("/users/romain.rochegude")
                .then()
                .statusCode(HttpStatus.SC_FORBIDDEN)
                .body(equalTo("not implemented"));
    }

    @Test
    public void testDeleteNotFound() {
        given().delete("/users/romain.rochegude")
                .then()
                .statusCode(HttpStatus.SC_FORBIDDEN)
                .body(equalTo("not implemented"));
    }

    @Test
    void testUpdate() {
        given().patch("/users/romain.rochegude")
                .then()
                .statusCode(HttpStatus.SC_FORBIDDEN)
                .body(equalTo("not implemented"));
    }
}
