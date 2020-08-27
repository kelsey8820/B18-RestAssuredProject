package day06;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Locations;

import static io.restassured.RestAssured.*;

public class HR_ORDS_POJO_Test {

    @BeforeAll
    public static void init(){

        RestAssured.baseURI = "http://54.174.216.245" ;
        RestAssured.port = 1000 ;
        RestAssured.basePath = "ords/hr";

    }

    @DisplayName("Testing the /location/{location_id} endpoint")
    @Test
    public void testLocation(){

      Response response = given()
                        .accept(ContentType.JSON)
                        .pathParam("location_id",1700)
                        .log().all().
                when()
                        .get("/locations/{location_id}")
                        .prettyPeek();

        Locations l1 = response.as(Locations.class) ;
        System.out.println("l1 = " + l1);


    }


}
