package CAMPUS;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import io.restassured.specification.RequestSpecification;
import org.apache.http.cookie.Cookie;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;


public class MersysTestCampus {
    Faker faker = new Faker();

    String gradeID;
    String gradeName;

    RequestSpecification recSpec;

    @BeforeClass
    public void Login() {
        baseURI = "https://test.mersys.io";


        Map<String, String> userCredential = new HashMap<>();
        userCredential.put("username", "turkeyts");
        userCredential.put("password", "TechnoStudy123");
        userCredential.put("rememberMe", "true");

        Cookies cookies =
                given()
                        .contentType(ContentType.JSON)
                        .body(userCredential)

                        .when()
                        .post("/auth/login")

                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract().response().getDetailedCookies();

        recSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addCookies(cookies)
                .build();

    }


    @Test
    public void CreateGradeLevels() {
        Map<String, String> country = new HashMap<>();
        gradeName = faker.address().country() + faker.number().digits(5);
        country.put("name", gradeName);
        country.put("code", faker.address().countryCode() + faker.number().digits(5));


        gradeID =
                given()
                        .spec(recSpec)
                        .body(country)
                        .log().body()

                        .when()
                        .post("/school-service/api/grade-levels")

                        .then()
                        //.log().body()
                        .statusCode(201)
                        .extract().path("id")
        ;
        System.out.println("gradeID = " + gradeID);


    }

    @Test(dependsOnMethods = "CreateGradeLevels")
    public void CreateGradeLevelsNegative() {
        Map<String, String> country = new HashMap<>();
        country.put("name", gradeName);
        country.put("code", faker.address().countryCode() + faker.number().digits(5));


        given()
                .spec(recSpec)
                .body(country)
                .log().body()

                .when()
                .post("/school-service/api/grade-levels")

                .then()
                //.log().body()
                .statusCode(400)
                .body("message", containsString("already"))
        ;


    }

    @Test(dependsOnMethods = "CreateGradeLevelsNegative")
    public void UpdateGradeLevels() {
        Map<String, String> country = new HashMap<>();
        country.put("id", gradeID);

        gradeName = "haydar ülkesi" + faker.number().digits(7);
        country.put("name", gradeName);
        country.put("code", faker.address().countryCode() + faker.number().digits(5));

        given()
                .spec(recSpec)
                .body(country) // giden body
                //.log().body() // giden body yi log olarak göster

                .when()
                .put("/school-service/api/grade-levels")

                .then()
                .log().body() // gelen body yi log olarak göster
                .statusCode(200)
                .body("name", equalTo(gradeName))
        ;

    }

    @Test(dependsOnMethods = "UpdateGradeLevels")
    public void DeleteGradeLevels() {
        given()
                .spec(recSpec)
                .pathParam("gradeID", gradeID)
                .log().uri()

                .when()
                .delete("/school-service/api/grade-levels/{gradeID}")

                .then()
                .log().body()
                .statusCode(200)
        ;

    }

    @Test
    public void DeleteGradeLevelsNegativ() {
        given()
                .spec(recSpec)
                .pathParam("gradeID", gradeID)
                .log().uri()

                .when()
                .delete("/school-service/api/grade-levels/{gradeID}")

                .then()
                .log().body()
                .statusCode(400)
                .body("message", equalTo("Country not found"))

        ;

    }


}
