package GoRest;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GoRestUsersTest {
    Faker randomUretici = new Faker();

    int userID;


    @Test(enabled = false)
    public void createUserJson() {
        // POST https://gorest.co.in/public/v2/users
        // "Authorization: Bearer 523891d26e103bab0089022d20f1820be2999a7ad693304f560132559a2a152d"
        // {"name":"{{$randomFullName}}", "gender":"male", "email":"{{$randomEmail}}", "status":"active"}
        String rndFullname = randomUretici.name().fullName();
        String rndEmail = randomUretici.internet().emailAddress();

        userID =
                given()

                        .header("Authorization", "Bearer 141200321fad2672fe320a9cbee627057de198d05237c658e8976a7fc73ed313")

                        .body("{\"name\":\"" + rndFullname + "\", \"gender\":\"male\", \"email\":\"" + rndEmail + "\", \"status\":\"active\"}")
                        //.log().uri()
                        //.log().body()

                        .when()
                        .post("")

                        .then()
                        .log().body()
                        .statusCode(201)
                        .contentType(ContentType.JSON)
                        .extract().path("id");
    }

    @Test
    public void createUserMap() {
        // POST https://gorest.co.in/public/v2/users
        // "Authorization: Bearer 523891d26e103bab0089022d20f1820be2999a7ad693304f560132559a2a152d"
        // {"name":"{{$randomFullName}}", "gender":"male", "email":"{{$randomEmail}}", "status":"active"}

        String rndFullName = randomUretici.name().fullName();
        String rndEmail = randomUretici.internet().emailAddress();

        Map<String, String> newUser = new HashMap<>();
        newUser.put("name", rndFullName);
        newUser.put("gender", "male");
        newUser.put("email", rndEmail);
        newUser.put("status", "active");

        userID =
                given()
                        .header("Authorization", "Bearer 141200321fad2672fe320a9cbee627057de198d05237c658e8976a7fc73ed313")
                        .contentType(ContentType.JSON)
                        .body(newUser)
                        // .log().uri()
                        // .log().body()

                        .when()
                        .post("https://gorest.co.in/public/v2/users")
                        .then()
                        .log().body() // hata varsa yazar bilgi verir
                        .statusCode(201)
                        .contentType(ContentType.JSON)
                        .extract().path("id")
        ;

    }
    @Test(enabled = true)
    public void createUserClas() {
        // POST https://gorest.co.in/public/v2/users
        // "Authorization: Bearer 523891d26e103bab0089022d20f1820be2999a7ad693304f560132559a2a152d"
        // {"name":"{{$randomFullName}}", "gender":"male", "email":"{{$randomEmail}}", "status":"active"}

        String rndFullName = randomUretici.name().fullName();
        String rndEmail = randomUretici.internet().emailAddress();

     User newUser=new User();
        newUser.name=rndFullName;
        newUser.gender="male";
        newUser.email=rndEmail;
        newUser.status="active";

        userID =
                given()
                        .header("Authorization", "Bearer 141200321fad2672fe320a9cbee627057de198d05237c658e8976a7fc73ed313")
                        .contentType(ContentType.JSON)
                        .body(newUser)
                        // .log().uri()
                        // .log().body()

                        .when()
                        .post("https://gorest.co.in/public/v2/users")
                        .then()
                        .log().body() // hata varsa yazar bilgi verir
                        .statusCode(201)
                        .contentType(ContentType.JSON)
                        .extract().path("id")
        ;

    }

    @Test(dependsOnMethods = "createUserMap")
    public void getUserByID() {
        given()
                .header("Authorization", "Bearer 141200321fad2672fe320a9cbee627057de198d05237c658e8976a7fc73ed313")

                .when()
                .get("https://gorest.co.in/public/v2/users" + userID)

                .then()
                .log().body()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", equalTo(userID))
        ;

    }

    @Test
    public void updateUser() {

    }

    @Test
    public void deleteUser() {

    }

    @Test
    public void deleteUserNegative() {

    }

}
