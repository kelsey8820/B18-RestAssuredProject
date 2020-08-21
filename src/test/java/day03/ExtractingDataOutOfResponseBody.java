package day03;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ExtractingDataOutOfResponseBody {

    @DisplayName("Getting Movie info")
    @Test
    public void test1(){

        // provide your baseURI in the test
        // add query parameters
            // apikey yourAPIkey here
            // t   = Boss Baby
            // Save the response into response object

     Response response =
            given()
                .log().all()
                .baseUri("http://www.omdbapi.com")
                .queryParam("apikey","26aa5b74")
                .queryParam("t","Boss Baby").
            when()
                .get();

     //response.prettyPrint() ;
     System.out.println(  response.statusCode()  );
     // if you want to get single data out for example just title , just year
        // use path method of Response object and provide the jsonPath
        String title = response.path("Title") ;
        System.out.println("title = " + title);
        // using same example - print out -- Year, Director , Actors
        // Optionally -- first Rating Source
        String year = response.path("Year") ;
        String director = response.path("Director") ;
        String actors = response.path("Actors");

        System.out.println("Year = " + year);
        System.out.println("director = " + director);
        System.out.println("actors = " + actors);

        // getting the first Rating source
        // the jsonPath for first Rating source is Ratings[0].Source
        String firstRatingSrc = response.path("Ratings[0].Source");
        System.out.println("firstRatingSrc = " + firstRatingSrc);




    }


}
