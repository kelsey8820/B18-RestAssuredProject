package day02;

import io.restassured.RestAssured;
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

                ;



    }


}
