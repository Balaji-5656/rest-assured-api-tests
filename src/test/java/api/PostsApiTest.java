package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostsApiTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    // ---------- Positive tests ----------

    @Test
    public void getSinglePost_returns200AndCorrectFields() {
        given()
        .when()
            .get("/posts/1")
        .then()
            .statusCode(200)
            .body("id", equalTo(1))
            .body("userId", equalTo(1))
            .body("title", not(emptyOrNullString()));
    }

    @Test
    public void getAllPosts_returns100Posts() {
        given()
        .when()
            .get("/posts")
        .then()
            .statusCode(200)
            .body("size()", equalTo(100));
    }

    @Test
    public void getPostsByUser_returnsOnlyThatUsersPosts() {
        given()
            .queryParam("userId", 1)
        .when()
            .get("/posts")
        .then()
            .statusCode(200)
            .body("userId", everyItem(equalTo(1)));
    }

    @Test
    public void createPost_returns201AndEchoesData() {
        String body = "{ \"title\": \"My test post\", \"body\": \"Created by REST Assured\", \"userId\": 1 }";

        given()
            .contentType(ContentType.JSON)
            .body(body)
        .when()
            .post("/posts")
        .then()
            .statusCode(201)
            .body("title", equalTo("My test post"))
            .body("userId", equalTo(1))
            .body("id", notNullValue());
    }

    @Test
    public void updatePost_returns200AndUpdatedTitle() {
        String body = "{ \"id\": 1, \"title\": \"Updated title\", \"body\": \"Updated body\", \"userId\": 1 }";

        given()
            .contentType(ContentType.JSON)
            .body(body)
        .when()
            .put("/posts/1")
        .then()
            .statusCode(200)
            .body("title", equalTo("Updated title"));
    }

    @Test
    public void deletePost_returns200() {
        given()
        .when()
            .delete("/posts/1")
        .then()
            .statusCode(200);
    }

    // ---------- Negative tests ----------

    @Test
    public void getNonExistentPost_returns404() {
        given()
        .when()
            .get("/posts/9999")
        .then()
            .statusCode(404);
    }

    @Test
    public void getInvalidEndpoint_returns404() {
        given()
        .when()
            .get("/invalidendpoint")
        .then()
            .statusCode(404);
    }
}
