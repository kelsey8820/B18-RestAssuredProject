package day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class OpenMovieDB_Test {


    @BeforeAll
    public static void init(){

        RestAssured.baseURI = "http://www.omdbapi.com" ;

    }

    @Test
    @DisplayName("Test Movie API")
    public void testMovies(){

        given()
                .log().all()
                .queryParam("apikey","26aa5b74")
                .queryParam("t","Boss Baby")
                .queryParam("plot","full").
        when()
                .get().  // what if my URL is already complete , DO NOTHING
        then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                // checking title contains Boss Baby
                // if you are searching for exact match use is equalTo
                .body("Title" , containsString("Boss Baby") )
                .body("Ratings[0].Value" , is("6.3/10") )
                .body("Ratings[-1].Value" , is("50/100"))

        ;


    }



}
