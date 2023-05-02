package CAMPUS;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class MersysTestCampus {
    // POST : https://test.mersys.io/school-service/api/grade-levels/school
    // Autahorization : Bearer 141200321fad2672fe320a9cbee627057de198d05237c658e8976a7fc73ed313
    //  "id": null,
    //  "name": "{{$randomFullName}}",
    //  "shortName": "{{$randomUserName}}",
    //  "nextGradeLevel": null,
    //  "order": "{{$randomInt}}",
    //  "translateName": [],



    @Test
    public void CreateGradeLevels(){
        int fullNameid=
        given()
                .header("Authorization","Bearer 141200321fad2672fe320a9cbee627057de198d05237c658e8976a7fc73ed313")
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"id\": null,\n" +
                        "  \"name\": \"{{$randomFullName}}\",\n" +
                        "  \"shortName\": \"{{$randomUserName}}\",\n" +
                        "  \"nextGradeLevel\": null,\n" +
                        "  \"order\": \"{{$randomInt}}\",\n" +
                        "  \"translateName\": [],\n" +
                        "  \"translateShortName\": [],\n" +
                        "  \"active\": true,\n" +
                        "  \"schoolIds\": [\n" +
                        "    \"6390f3207a3bcb6a7ac977f9\"\n" +
                        "  ],\n" +
                        "  \"showToAllSchools\": false\n" +
                        "}")
                .log().uri()
                .log().body()

                .when()
                .post("https://test.mersys.io/school-service/api/grade-levels/school")
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
