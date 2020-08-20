package day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
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
                // FX FOR THE SPACE IN THE KEY
                .body("'post code'", is("22030")    )
                .body("country",is("United States") )
                // get the state and check it's Virginia
                .body("places[0].state" , is("Virginia")   )
                // get the place name
                .body("places[0].'place name' " , is("Fairfax") )

                ;
        // if a field/key you are trying to get has space
        // then add ''  for example  " 'post code'



    }

    @DisplayName("City to Zip")
    @Test
    public void testCityToZip(){

    //api.zippopotam.us/us/:state/:city
        given()
                .pathParam("state","VA")
                .pathParam("city","Fairfax")
                .log().all().
        when()
                .get("/{state}/{city}").
//                .get("/{state}/{city}" , "VA","Fairfax" ). // second way we did with spartan
        then()
                .log().all()
                .statusCode( is(200) )
                .body("'country abbreviation'",is("US") )
                // test the latitude of first place is "38.8458"
                .body("places[0].latitude" ,is("38.8458") )
                // jsonPath hack for getting last item from jsonArray
                // -1 index indicate the last item , only works here not in postman
                .body("places[-1].latitude" , is("38.7602") )

        ;


    }


}
