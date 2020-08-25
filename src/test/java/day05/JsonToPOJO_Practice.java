package day05;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class JsonToPOJO_Practice {

    @BeforeAll
    public static void init(){

        RestAssured.baseURI = "http://54.160.106.84" ;
        RestAssured.port = 8000;
        RestAssured.basePath = "/api" ;
    }

    @DisplayName("Json to Pojo GET /spartans/{id} ")
    @Test
    public void testSpartanJsonToSpartanObject(){


        Response response = given()
                                .auth().basic("admin","admin")
                                .log().all()
                                .pathParam("id", 261).
                        when()
                                .get("/spartans/{id}")
                                .prettyPeek() ;

    }



}
