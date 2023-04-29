
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
    public void test(){
        given()          // hazırlık işlemleri  : (token,send body, parametreler)
                .when()  // endpoint (url), metodu
                .then(); // assertion, test, data işlemleri
    } @Test
    public void test1(){


        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")
                .then()
                .log().body()    // dönen body json datat sı,   log.all()
                .statusCode(200) // dönüş kodu 200 mü
        ;
    }



}
