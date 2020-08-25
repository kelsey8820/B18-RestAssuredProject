package day05;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Spartan;


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

            int newId = createRandomSpartan();

            given()
                    .log().all()
                    .auth().basic("admin","admin")
                    .pathParam("id",newId).
            when()
                    .get("/spartans/{id}").
            then()
                    .log().all()
                    .statusCode(200) ;

    }

    public static int createRandomSpartan(){

        Faker faker = new Faker();
        String name = faker.name().firstName();
        String gender = faker.demographic().sex();
        long phone = faker.number().numberBetween(1000000000L,9999999999L);
        // this what we are going pass for post body
        Spartan sp = new Spartan(name, gender, phone) ;

        Response response = given()
                                .log().all()
                                .auth().basic("admin","admin")
                                .contentType(ContentType.JSON)
                                .body(sp).
                            when()
                                .post("/spartans")
                                .prettyPeek();

        return response.jsonPath().getInt("data.id") ;
    }


}
