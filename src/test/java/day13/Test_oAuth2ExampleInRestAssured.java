package day13;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class Test_oAuth2ExampleInRestAssured {

    // Request to get the token
    // https://cybertek-reservation-api-qa.herokuapp.com/sign?email=jalabaster7f@drupal.org&password=terimapam

    // Request to get all rooms by authorizing it with token
    //https://cybertek-reservation-api-qa.herokuapp.com/api/rooms

    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "https://cybertek-reservation-api-qa.herokuapp.com" ;
    }

    @DisplayName("Getting the token and making authorized request")
    @Test
    public void test(){

           String accessToken =
                given()
                        .queryParam("email","jalabaster7f@drupal.org")
                        .queryParam("password", "terimapam").
                when()
                        .get("/sign").prettyPeek()
                        .path("accessToken") ;


        /** we want to save the access token so we can use it for next request
         * {
         *     "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyMDI1IiwiYXVkIjoic3R1ZGVudC10ZWFtLWxlYWRlciJ9.Fo9bllgK_UoOS4SGB0OXmo2-J3_F9YA5i-ZGLSykIXI",
         *     "refreshToken": "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyMDI1IiwiYXVkIjoic3R1ZGVudC10ZWFtLWxlYWRlciJ9.Fo9bllgK_UoOS4SGB0OXmo2-J3_F9YA5i-ZGLSykIXI"
         * }
         */
        // send GET /api/rooms to get all the rooms
        given()
//                .header("Authorization", "Bearer "+ accessToken)
                  .auth().oauth2(accessToken)
                  .log().all().
        when()
                .get("/api/rooms").
        then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                ;
    }



}
