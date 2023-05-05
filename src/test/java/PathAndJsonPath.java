import GoRest.User;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class PathAndJsonPath {

    @Test
    public void extractingPath() {
// "post code": "90210"
        int postCode =
        given()
                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body()
                .extract().path("'post code'")
        ;
        System.out.println("postCode = " + postCode);
    }
    @Test
    public void extractingJsonPath() {
// "post code": "90210"
        int postCode =
                given()
                        .when()
                        .get("http://api.zippopotam.us/us/90210")

                        .then()
                        .log().body()
                        .extract().jsonPath().getInt("'post code'")
                // tip dönüşümü otomatik, uygun tip verilmeli
                ;
        System.out.println("postCode = " + postCode);
    }
    @Test
    public void getUsers(){
        Response response =
                given()
                        .when()
                        .get("https://gorest.co.in/public/v2/users")

                        .then()
                        //.log().body()

                        .extract().response();

        int idPath = response.path("[2].id");
        int idJsonPath = response.jsonPath().getInt("[2].id");

        System.out.println("idPath = " + idPath);
        System.out.println("idJsonPath = " + idJsonPath);


        User[] usersPath = response.as(User[].class);// as nesne dönüşümünde (POJO) dizi destekli
        List<User> usersJsonPath = response.jsonPath().getList("", User.class); // JsonPath ise List olarak verebiliyor

        System.out.println("usersPath = " + Arrays.toString(usersPath));
        System.out.println("usersJsonPath = " + usersJsonPath);






    }
    @Test
    public void getUsersV1(){


    }
}
