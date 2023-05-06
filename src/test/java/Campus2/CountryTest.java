package Campus2;

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

public class CountryTest {

    RequestSpecification recSpec;
    String countryId;

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
    public void createCountry() {

        Faker faker = new Faker();


        Map<String, String> country = new HashMap<>();
        country.put("name", faker.country().name());
        country.put("code", faker.address().countryCode());


        countryId =
                given()
                        .spec(recSpec)
                        .body(country)
                        .log().body()

                        .when()
                        .post("/school-service/api/countries")

                        .then()
                        //.log().body()
                        .statusCode(201)
                        .extract().path("id")
        ;
        System.out.println("countryId = " + countryId);

    }

    @Test(dependsOnMethods = "createCountry")
    public void createCountryNegative() {

    }

    @Test(dependsOnMethods = "createCountryNegative")
    public void updateCountry() {

    }

    @Test(dependsOnMethods = "updateCountry")
    public void deleteCountry() {

    }

    @Test(dependsOnMethods = "deleteCountry")
    public void deleteCountryNegative() {

    }

}
