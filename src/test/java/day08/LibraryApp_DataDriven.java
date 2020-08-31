package day08;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import utility.ConfigurationReader;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class LibraryApp_DataDriven {

    @BeforeAll
    public static void init(){

        RestAssured.baseURI = ConfigurationReader.getProperty("library1.base_url");
        RestAssured.basePath = "/rest/v1";

    }

    // give a name your test in this format
    // iteration 1 | username:firstColdata , password: secondColdata
    @ParameterizedTest(name = "iteration {index} | username:{0} , password:{1}")
    @CsvFileSource(resources = "/credentials.csv")
    public void testLoginCredentials(String user, String pass){

//        System.out.println("user = " + user);
//        System.out.println("pass = " + pass);
          // so now lets make a post request to /login
         // content type is x-www-form-urlencoded
         // form data  email , password
         // check if the status code 200  if the password is correct
         // check the token field from the response is not null
         given()
                 .log().all()
                .contentType(ContentType.URLENC)
                .formParam("email", user )
                 .formParam("password" , pass).
         when()
                 .post("/login").
         then()
                 .statusCode(200)
                 .body("token", notNullValue() )
                ;

    }


    @AfterAll
    public static void cleanUp(){
        RestAssured.reset();
    }



}




