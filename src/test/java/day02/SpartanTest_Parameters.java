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

public class SpartanTest_Parameters {

    @BeforeAll
    public static void setUp(){

        RestAssured.baseURI = "http://54.174.216.245:8000" ;
        RestAssured.basePath = "/api" ;

    }

    @DisplayName("Testing /spartans/{id}")
    @Test
    public void testSingleSpartan(){

        given()
                .log().all()
                .pathParam("id",971).
        when()
                .get("spartans/{id}").
        then()
                .statusCode( is(200) );

    }

    @DisplayName("Testing2 /spartans/{id}")
    @Test
    public void testSingleSpartan2(){

        given()
                .log().all().
        when()
                .get("spartans/{id}" , 971 ).
        then()
                .statusCode( is(200))

                ;
    }
    @DisplayName("Testing /spartans/{id} Body")
    @Test
    public void testSingleSpartanBody(){

        given()
                .log().all()
                .pathParam("id",971).
        when()
                .get("spartans/{id}").
        then()
                .log().all()
                .statusCode(is(200))
//                .body("JSON PATH",is( "THE VALUE" )  )
                .body("id",     is(971 ) )
                .body("name",   is("Test") )
                .body("gender", is("Male") )
                .body("phone",  is(3216549870L) )

        ;



    }








}
