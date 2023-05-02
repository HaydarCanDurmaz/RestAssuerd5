package CAMPUS;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

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
        String rndShortName=randomUretici.name().firstName();
        int rndInt=randomUretici.idNumber().hashCode();
        userID=

        given()
                .header("Authorization","Bearer 141200321fad2672fe320a9cbee627057de198d05237c658e8976a7fc73ed313")
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"id\": null,\n" +
                        "  \"name\": \""+rndFullName+"\",\n" +
                        "  \"shortName\": \""+rndShortName+"\"\",\n" +
                        "  \"nextGradeLevel\": null,\n" +
                        "  \"order\": \"\""+rndInt+"\"}\",\n" +
                        "  \"translateName\": [],\n" +
                        "  \"translateShortName\": [],\n" +
                        "  \"active\": true,\n" +
                        "  \"schoolIds\": [\n" +
                        "    \"6390f3207a3bcb6a7ac977f9\"\n" +
                        "  ],\n" +
                        "  \"showToAllSchools\": false\n" +
                        "}")
               // .log().uri()
               // .log().body()

                .when()
                .get("https://test.mersys.io/school-service/api/grade-levels/school/6390f3207a3bcb6a7ac977f9")
                .then()
                .statusCode(201)
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
