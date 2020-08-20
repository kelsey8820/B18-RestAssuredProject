package day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class SpartanTest2 {


    @BeforeAll
    public static void setUp(){

        RestAssured.baseURI = "http://54.174.216.245:8000" ;
        RestAssured.basePath = "/api" ;

    }

    @DisplayName("Get 1 Spartan Test")
    @Test
    public void testSingleSpartan(){

        // I want to log the request I sent so I see what is the uRL , methods and so on
        given()
                .log().all().
//                .log().uri().
        when()
                .get("/spartans/971").
//                .prettyPeek().
        then()
                .log().all()
//                .log().body()
//                .log().ifValidationFails()
                .statusCode( is(200))

                ;
    }




    // quick tasks
    // add another test for hello endpoint by reusing the baseURI , basePath above
    // specify you want to get a text result back
    // check status code is 200
    // contentType is Text
    // body is Hello from Sparta
    @DisplayName("Testing /Hello again")
    @Test
    public void testHello(){

        given()
                .accept(ContentType.TEXT). // specify you want to get a text result back
        when()
                .get("/hello").  // sending request to baseURI+basePath+ /hello
        then()
                .statusCode( is(200) )  // checking status code 200
                .contentType( ContentType.TEXT)  // checking content type is text
                .body(equalTo("Hello from Sparta")) ; // checking the body

    }


    @DisplayName("Get All Spartan")
    @Test
    public void testAllSpartans(){

        // it will create the request URL as is
        // baseURI + basePath + what is after get( "This Part" )

        // i want to explicitly specify I want JSON response from this result
        given()
                .header("Accept","application/json").
        when()
                .get("/spartans").
        then()
                .statusCode( is(200) ) ;

    }


    @DisplayName("Get All Spartan 2 ")
    @Test
    public void testAllSpartans2(){


        // send the same request specifiying the accept header is application/json
        // use baseuri basepath , check status code 200 , contentType header is json

        given()
//                .baseUri("http://54.174.216.245:8000")  // alternative way of doing it
//                .basePath("/api")
//                .accept("application/json").
                .accept(ContentType.JSON).
        when()
                .get("/spartans").
        then()
                .statusCode( is(200) )
                //.contentType("application/json;charset=UTF-8")
                //.contentType( is("application/json;charset=UTF-8")  )
                // easiest way for contentType is using ContentType enum
                .contentType( ContentType.JSON )  ;


    }





}
