package day09;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Spartan;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class SpartanRoleBaseAccessControlNegativeTest_ReuseClassLevel {

    static RequestSpecification requestSpec ;
    static ResponseSpecification responseSpec ;

    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://54.160.106.84";
        RestAssured.port = 8000;
        RestAssured.basePath = "/api";
        // setting the value of requestSpec
        requestSpec = given()
                .auth().basic("user", "user")
                .accept(ContentType.JSON) // we are getting 403 with json body so accept header is json
                .log().all();
        // setting the value of responseSpec using ResponseSpecBuilder object
        ResponseSpecBuilder resSpecBuilder = new ResponseSpecBuilder() ;
        responseSpec = resSpecBuilder
                .expectStatusCode(403)
                .expectContentType(ContentType.JSON)
                .expectHeader("Date", notNullValue(String.class)  )
                .log(LogDetail.ALL)
                .build();

    }

    @DisplayName("User should not be able to delete data")
    @Test
    public void testUserCanNotDeleteData(){

        given()
                .spec(requestSpec).
        when()
                .delete("/spartans/{id}" , 10).
        then()
                .spec(responseSpec);

    }

    @DisplayName("User should not be able to update data")
    @Test
    public void testUserCanNotUpdateData(){

        Spartan spartanObj = new Spartan("some name", "Male", 8888888888L) ;

        given()
                .spec(requestSpec)
                .contentType(ContentType.JSON)
                .body(spartanObj).
        when()
                .put("/spartans/{id}", 10).
        then()
                .spec(responseSpec) ;


    }

    @DisplayName("User should not be able to post data")
    @Test
    public void testUserCanNotPostData(){

        Spartan spartanObj = new Spartan("some name", "Male", 8888888888L) ;

        given()
                .spec(requestSpec)
                .contentType(ContentType.JSON)
                .body(spartanObj).
        when()
                .post("/spartans").
        then()
                .spec(responseSpec);

    }




}
