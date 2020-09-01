package day09;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ZipDataDriven {

    //api.zippopotam.us/us/:state/:city

    @ParameterizedTest
    @CsvFileSource(resources = "/state_city.csv", numLinesToSkip = 1)
    public void testStateCity(String state, String city, int numberOfZipcodes){

//        System.out.println("state = " + state);
//        System.out.println("city = " + city);
//        System.out.println("numberOfZipcodes = " + numberOfZipcodes);
         //import io.restassured.response.Response;
         Response response = given()
                         .pathParam("state", state)
                         .pathParam("city", city)
                         .baseUri("http://api.zippopotam.us/us").
                 when()
                        .get("/{state}/{city}")
                        .prettyPeek() ;

         // assert the state and city match in the response
        JsonPath jp = response.jsonPath() ;
        System.out.println("state = " + jp.getString("'state abbreviation'")  ) ;
//        assertThat("", is());


                 //.get("/{state}/{city}" , state , city )
    }


}
