package day05;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class SecureSpartanTest {

     //54.160.106.84
    //100.24.235.129
    //23.23.75.140
     // add @BeforeAll and use the spartanApp ID with basic auth
    @BeforeAll
    public static void init(){

        RestAssured.baseURI = "http://54.160.106.84" ;
        RestAssured.port = 8000;
        RestAssured.basePath = "/api" ;
    }

     // add a test
     // make a simple get request /spartans/{id}




}
