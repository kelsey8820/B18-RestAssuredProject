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
    public void testStateCity(String expectedState, String expectedCity, int numberOfZipcodes){

//        System.out.println("state = " + state);
//        System.out.println("city = " + city);
//        System.out.println("numberOfZipcodes = " + numberOfZipcodes);
         //import io.restassured.response.Response;
         Response response = given()
                         .pathParam("state", expectedState)
                         .pathParam("city", expectedCity)
                         .baseUri("http://api.zippopotam.us/us").
                 when()
                        .get("/{state}/{city}")
                        .prettyPeek() ;

         // assert the state and city match in the response
        JsonPath jp = response.jsonPath() ;
        // why do we use single quote? because there can not be a space in json path
        // we are using the '' to treat the 2 word as one
        System.out.println("state = " + jp.getString("'state abbreviation'")  ) ;
        System.out.println("city = "  + jp.getString("'place name'"));

        assertThat( jp.getString("'state abbreviation'"), is(expectedState) );
        assertThat( jp.getString("'place name'"), is(expectedCity) );


                 //.get("/{state}/{city}" , state , city )
    }


}
