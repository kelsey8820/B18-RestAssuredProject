package day03;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class ExtractingDataUsingJsonPathMethods {

    @DisplayName("Getting Movie info")
    @Test
    public void test1(){

        Response response =
                given()
                        .log().all()
                        .baseUri("http://www.omdbapi.com")
                        .queryParam("apikey","26aa5b74")
                        .queryParam("t","Boss Baby").
               when()
                        .get();



    }



}
