package day02;

import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.* ;
import static org.hamcrest.Matchers.*;

public class SpartanTest {

    @DisplayName("Get All Spartan")
    @Test
    public void testAllSpartans(){

        //String spartanURL = "http://54.174.216.245:8000/api/spartans" ;
        // how to set base URL , port , base path so I can reuse them
        RestAssured.baseURI = "http://54.174.216.245:8000" ;
        RestAssured.basePath = "/api" ;

        // it will create the request URL as is
        // baseURI + basePath + what is after get( "This Part" )

        when()
                .get("/spartans").
        then()
                .statusCode( is(200) ) ;

    }




}
