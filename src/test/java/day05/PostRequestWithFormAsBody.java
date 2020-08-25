package day05;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PostRequestWithFormAsBody {

    //posting in library app
    // body is not json , it's x-www-urlencoded-form-data

    //http://library1.cybertekschool.com/rest/v1/login
    // baseURI  is http://library1.cybertekschool.com
    // basePath is /rest/v1
    // we are working on POST /login

    // Post body , type x-www-urlencoded-form-data
    //email :    librarian69@library
    //password : KNPXrm3S

    // in URLs if you do not see port , because it's omitted because it's so common
    //  http --->> 80
    //  https --->> 443
    //  anything other than above 2 ports need to be explicitly set in the URL
    // for example spartan app use port 8000 -->> yourip:8000

    @BeforeAll
    public static void init(){

        RestAssured.baseURI = "http://library1.cybertekschool.com" ;
        RestAssured.basePath = "/rest/v1" ;

    }

    @DisplayName("POST /login request test")
    @Test
    public void testLoginEndPoint(){

        given()
                .log().all()
                .formParam("email","librarian69@library")
                .formParam("password", "KNPXrm3S" ).
        when()
                .post("/login").
        then()
                .log().all()
                .statusCode(200)
                // we can not actually validate the token since it's dynamic
                // what we can validate though -- token field exists and it's not null
                .body("token", is(  notNullValue() ) )

                ;

    }

    /**
     * A static utility method to get the token by providing username and password
     * as Post request to /Login endpoint and capture the token field from response json
     * @param username
     * @param password
     * @return the token as String from the response json
     */
    public static String loginAndGetToken(String username, String password){

        String token = "";
        // YOU FILL IT UP HERE

        return token ;
    }



}
