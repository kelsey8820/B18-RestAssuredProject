package day06;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Locations;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

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

    @DisplayName("Testing the /location endpoint")
    @Test
    public void testLocations(){

        // save all street address to List<String>
        // save all Locations into List<Location>
        Response response = get("/locations").prettyPeek();

        List<Locations> locationsList =
                response.jsonPath().getList("items",Locations.class) ;

//        for(Locations eachLocation  :  locationsList){
//            System.out.println("eachLocation = " + eachLocation);
//        }
//        locationsList.forEach(eachLocation-> System.out.println("eachLocation = " + eachLocation) );

        // how do we assert we have 29 items in the list
        // using hamcrest library assertion to check the list with certain size
        //import static org.hamcrest.MatcherAssert.assertThat;
        //import static org.hamcrest.Matchers.hasSize;
        assertThat(locationsList, hasSize(23) );

    }


}
