package day03;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class SpartanSearchExtractData {

    @BeforeAll
    public static void init(){

        RestAssured.baseURI = "http://54.174.216.245" ;
        RestAssured.port = 8000 ;
        RestAssured.basePath = "/api" ;

    }

    @Test
    public void test1(){

        Response response =  given()
                                    .queryParam("gender","Female").
                             when()
                                     .get("/spartans/search") ;

    }




}
