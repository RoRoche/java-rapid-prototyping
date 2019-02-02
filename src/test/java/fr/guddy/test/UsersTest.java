package fr.guddy.test;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

public final class UsersTest {
    private final User userRre = new User("romain.rochegude", "Romain", "Rochegude");
    private final User userJde = new User("john.doe", "John", "Doe");

    private final Nitrite db = Nitrite.builder()
            .openOrCreate("user", "password");
    private final ObjectRepository<User> userRepository = db.getRepository(User.class);

    @RegisterExtension
    final UserApiExtension userApiExtension = new UserApiExtension(
            7000,
            userRepository
    );

    @BeforeAll
    static void beforeAll() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 7000;
    }

    @AfterEach
    void afterEach() {
        userRepository.find()
                .toList()
                .forEach(userRepository::remove);
    }

    @Test
    public void testCreateOk() {
        given().body(userRre)
                .post("/users")
                .then()
                .statusCode(HttpStatus.SC_CREATED);
    }

    @Test
    public void testCreateConflict() {
        userRepository.insert(userRre);
        given().body(userRre)
                .post("/users")
                .then()
                .statusCode(HttpStatus.SC_CONFLICT)
                .body(equalTo("User with id \"romain.rochegude\" already exists"));
    }

    @Test
    public void testGetAllOk() {
        userRepository.insert(userRre);
        userRepository.insert(userJde);
        assertThat(
                given().get("/users")
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .extract()
                        .body()
                        .jsonPath()
                        .getList(".", User.class)
        ).containsExactlyInAnyOrder(userRre, userJde);
    }

    @Test
    public void testGetAllNoContent() {
        given().get("/users")
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void testGetOneOk() {
        userRepository.insert(userRre);
        assertThat(
                given().get("/users/romain.rochegude")
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .extract()
                        .body()
                        .as(User.class)
        ).isEqualTo(userRre);
    }

    @Test
    public void testGetOneNotFound() {
        given().get("/users/romain.rochegude")
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .body(equalTo("User with id \"romain.rochegude\" not found"));
    }

    @Test
    public void testDeleteOk() {
        userRepository.insert(userRre);
        given().delete("/users/romain.rochegude")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void testDeleteNotFound() {
        given().delete("/users/romain.rochegude")
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .body(equalTo("User with id \"romain.rochegude\" not found"));
    }

    @Test
    void testUpdate() {
        given().patch("/users/romain.rochegude")
                .then()
                .statusCode(HttpStatus.SC_FORBIDDEN)
                .body(equalTo("not implemented"));
    }
}
