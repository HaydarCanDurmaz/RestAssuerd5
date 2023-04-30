
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class ZippoTest {
    @Test
    public void test() {
        given()          // hazırlık işlemleri  : (token,send body, parametreler)
                .when()  // endpoint (url), metodu
                .then(); // assertion, test, data işlemleri
    }

    @Test
    public void test1() {


        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")
                .then()
                .log().body()    // dönen body json datat sı,   log.all()
                .statusCode(200) // dönüş kodu 200 mü
        ;
    }

    @Test
    public void checkCounrtryInResponseBody() {
        given()
                .when()
                .get("http://api.zippopotam.us/us/90210")
                .then()
                .log().body()
                .statusCode(200)
                .body("country", equalTo("United States")); // body nin country degişkeni " United States eşitmi


        // pm.response.json().id -> body.id

    }
    //
//    PM                            RestAssured
//    body.country                  body("country")
//    body.'post code'              body("post code")
//    body.places[0].'place name'   body("places[0].'place name'")
//    body.places.'place name'      body("places.'place name'")
//    bütün place nameleri bir arraylist olarak verir
//    https://jsonpathfinder.com/

    @Test
    public void checkstateInResponseBody() {
        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                //.log().body()
                .statusCode(200)
                .body("places[0].state", equalTo("California"))
        ;
    }

    @Test
    public void checkHasItemy() {
        given()

                .when()
                .get("http://api.zippopotam.us/tr/01000")

                .then()
                // .log().body()
                .statusCode(200)
                .body("places.'place name'", hasItem("Dörtağaç Köyü"))
        // bütün place name lerin herhangi birinde Dörtağaç Köyü varmı
        ;
    }

    @Test
    public void bodyArrayHasSizeTest() {
        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                //.log().body()
                .statusCode(200)
                .body("places", hasSize(1))
        ;
    }

    @Test
    public void combiningTest() {
        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                //.log().body()
                .statusCode(200)
                .body("places", hasSize(1))  // size ı 1 mi
                .body("places.state", hasItem("California")) // verilen path deki list bu item e sahip mi
                .body("places[0].'place name'", equalTo("Beverly Hills")) // verilen path deki değer buna eşit mi
        ;
    }

    @Test
    public void pathParamTest() {


        given()
                .pathParam("ulke", "us")
                .pathParam("pstakodu", 90210)

                .when()
                .get("http://api.zippopotam.us/{ulke}/{pstakodu}")

                .then()
                .statusCode(200)
        //.log().body()
        ;

    }



}
