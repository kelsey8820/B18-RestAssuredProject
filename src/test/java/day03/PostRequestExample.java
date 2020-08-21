package day03;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class PostRequestExample {

    @BeforeAll
    public static void init(){

        RestAssured.baseURI = "http://54.158.178.13" ;
        RestAssured.port = 8000 ;
        RestAssured.basePath = "/api" ;

    }

    @DisplayName("Testing POST /api/spartans")
    @Test
    public void testAddSpartan(){

        String myBodyData = "{\n" +
                        "  \"name\"  : \"Adam\",\n" +
                        "  \"gender\": \"Male\",\n" +
                        "  \"phone\": 6234567890\n" +
                        "}" ;

        System.out.println("myBodyData = " + myBodyData);

        given()
                .contentType( ContentType.JSON )
                .body(myBodyData)
                .log().all().
        when()
                .post("/spartans").
        then()
                .statusCode( is(201) )
                .contentType(ContentType.JSON)
            ;
    }





}
