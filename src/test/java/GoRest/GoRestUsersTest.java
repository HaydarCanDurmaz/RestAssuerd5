package GoRest;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GoRestUsersTest {
    // POST https://gorest.co.in/public/v2/users
    // "Authorization: Bearer 523891d26e103bab0089022d20f1820be2999a7ad693304f560132559a2a152d"
    // {"name":"{{$randomFullName}}", "gender":"male", "email":"{{$randomEmail}}", "status":"active"}
    @Test
    public void createUser(){
        int userID=
        given()
                .header("Authorization","Bearer 141200321fad2672fe320a9cbee627057de198d05237c658e8976a7fc73ed313")
                .contentType(ContentType.JSON)
                .body("{\"name\":\"haydarcandurmaz\", \"gender\":\"male\", \"email\":\"hydr25@hotmail.com\", \"status\":\"active\"}")
                .log().uri()
                .log().body()

                .when()
                .post("https://gorest.co.in/public/v2/users")
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .extract().path("id")
                ;

    }
    @Test
    public void getUserByID(){

    }
    @Test
    public void updateUser(){

    }
    @Test
    public void deleteUser(){

    }
    @Test
    public void deleteUserNegative(){

    }

}
