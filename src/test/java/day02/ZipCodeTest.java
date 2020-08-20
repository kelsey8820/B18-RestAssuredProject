package day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

// you may also add display name at class level like you did at test level
@DisplayName("Testing Zip Code API")
public class ZipCodeTest {

    @BeforeAll
    public static void init(){
        // THE URL MUST START WITH HTTP OR HTTPS
        // OR REST ASSURED CAN NOT DECIDE IT'S A VALID URL OR NOT
        RestAssured.baseURI = "http://api.zippopotam.us" ;
        RestAssured.basePath = "/us" ;

    }

    @Test
    @DisplayName("Zip to City Test")
    public void testZipToCity(){

        given()
                .pathParam("zip",22030).
                log().all().
        when()
                .get("/{zip}").
        then()
                .log().all()
                .statusCode(is(200))
                .contentType(ContentType.JSON)
                .body("country",is("United States") )
                // get the state and check it's Virginia
                .body("places[0].state" , is("Virginia") )
                // FX FOR THE SPACE IN THE KEY
                .body("'post code'", is("22030") )

                ;



    }


}
