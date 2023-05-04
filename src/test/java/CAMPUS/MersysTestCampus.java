package CAMPUS;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class MersysTestCampus {
    Faker randomUretici=new Faker();

    int userID;






    @Test
    public void CreateGradeLevels(){
        // POST : https://test.mersys.io/school-service/api/grade-levels/school
        // Autahorization : Bearer 141200321fad2672fe320a9cbee627057de198d05237c658e8976a7fc73ed313
        //  "id": null,
        //  "name": "{{$randomFullName}}",
        //  "shortName": "{{$randomUserName}}",
        //  "nextGradeLevel": null,
        //  "order": "{{$randomInt}}",
        //
        String rndFullName=randomUretici.name().fullName();
        String rndEmail=randomUretici.internet().emailAddress();

        Map<String,String> newUser=new HashMap<>();
        newUser.put("name",rndFullName);
        newUser.put("gender","male");
        newUser.put("email",rndEmail);
        newUser.put("status",rndFullName);

        userID=

        given()
                .header("Authorization","Bearer 141200321fad2672fe320a9cbee627057de198d05237c658e8976a7fc73ed313")
                .contentType(ContentType.JSON)
                .body(newUser)
               // .log().uri()
               // .log().body()

                .when()
                .post("https://test.mersys.io/school-service/api/grade-levels/school")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().path("id")
        ;



    }
    @Test
    public void CreateGradeLevelsNegative(){

    }
    @Test
    public void UpdateGradeLevels(){

    }
    @Test
    public void DeleteGradeLevels(){

    }
    @Test
    public void DeleteGradeLevelsNegativ(){

    }


}
