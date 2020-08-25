package day05;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class SecureSpartanTest {

    // These instances are created from the image : Cybertek_Latest_BasicAuth
    //54.160.106.84
    //100.24.235.129
    //23.23.75.140
    // so it require username password to access the application
    // yours is created from Cybertek_Latest_NoAuth
    // it has the Spartan App version that does not require username password

     // add @BeforeAll and use the spartanApp ID with basic auth
    @BeforeAll
    public static void init(){

        RestAssured.baseURI = "http://54.160.106.84" ;
        RestAssured.port = 8000;
        RestAssured.basePath = "/api" ;
    }

     // add a test
     // make a simple get request /spartans/{id}
    @DisplayName("Test GET /spartan/{id} Endpoint with No Credentials")
    @Test
    public void testGetSingleSpartanSecured(){

        given()
                .log().all()
                .pathParam("id",174).
        when()
                .get("/spartans/{id}").
        then()
                .log().all()
                .statusCode( 401 )
        ;


    }

    @DisplayName("Test GET /spartan/{id} Endpoint with Credentials")
    @Test
    public void testGettingSpartanWithCredentials(){




    }


}
