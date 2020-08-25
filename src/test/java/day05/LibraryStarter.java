package day05;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class LibraryStarter {

    private static String libraryToken ;

    @BeforeAll
    public static void init(){
        //TODO challenge yourself to move all the hardcoded data into configuration file
        RestAssured.baseURI = "http://library1.cybertekschool.com" ;
        RestAssured.basePath = "/rest/v1" ;
        libraryToken = loginAndGetToken("librarian69@library", "KNPXrm3S");
    }

    @Test
    public void testLibrarianGetDashBoardInfo(){

        given()
                .log().all()
                .header("x-library-token",libraryToken).
        when()
                .get("/dashboard_stats").
        then()
                .log().all()
                .statusCode(is(200))
                .contentType(ContentType.JSON)
                //TODO  challenge yourself to get real numbers from DB
                .body("book_count",equalTo("985"))    // hardcoded on purpose
                .body("borrowed_books",equalTo("600")) // hardcoded on purpose
                .body("users",equalTo("4970"))          // hardcoded on purpose
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

        Response response = given()
//                                .log().all()
                // explicitly saying the body content type is x-www-urlencoded-form-data
                .contentType(ContentType.URLENC)
                .formParam("email",username)
                .formParam("password", password ).
                        when()
                .post("/login") ;

        //token = response.path("token") ;  // this is using path method
        token = response.jsonPath().getString("token") ;
        return token ;
    }


}
