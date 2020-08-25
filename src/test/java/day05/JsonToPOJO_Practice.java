package day05;

import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.authentication.BasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Spartan;
import pojo.Spartan2;

import java.util.Base64;

import static io.restassured.RestAssured.*;
import static io.restassured.specification.ProxySpecification.auth;

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

        int newID = 368; //SecureSpartanTest.createRandomSpartan();

        Response response = given()
                                //.auth().basic("admin","admin")
                                .log().all()
                                .pathParam("id", newID).
                        when()
                                .get("/spartans/{id}")
                                .prettyPeek() ;
        // as method from response
        // accept a class type to define what is the type object you are trying to store the response as
        //  Spartan2 class we created has all the fields that match the json fields from the response
        // So it will map all the json fields to the java fields and return Spartan2 POJO Object
        // in a simple word turn below json into Java object
        /**
         * {
         *     "id": 261,
         *     "name": "Elma",
         *     "gender": "Male",
         *     "phone": 9999999998
         * }
         *
         * into  new Spartan2(261,"Elma","Male",9999999998L) Java Object
         * so we can work with the data using java object directly
         */
        Spartan2 sp = response.as( Spartan2.class ) ;
        // above line is almost as if you are doing below line
//        Spartan2 sp = new Spartan2(261,"Elma","Male",9999999998L)
        System.out.println("sp = " + sp);

    }

    @DisplayName("Search Request and save 1st Result as Spartan2 Pojo")
    @Test
    public void gettingNestedJsonAsPojo(){

         Response response =   given()
                                        .log().all()
                                        .queryParam("Male").
                                when()
                                        .get("/spartans/search")
                                        .prettyPeek();



    }






}
