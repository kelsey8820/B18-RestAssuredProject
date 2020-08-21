package day03;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class ExtractingDataUsingJsonPathMethods {

    @DisplayName("Getting Movie info")
    @Test
    public void test1(){

        Response response =
                given()
                        .log().all()
//                        .baseUri("http://www.omdbapi.com")
                        .queryParam("apikey","26aa5b74")
                        .queryParam("t","Boss Baby").
               when()
                        .get("http://www.omdbapi.com");
        // the JsonPath is a class that have a lot of methods
         // to get the body data in different format or data types
        // we get this object by calling the method of Response object called .jsonPath()
        JsonPath jp = response.jsonPath();
         // get the title as String
        String title = jp.getString("Title") ;
        // get the year as int
        int year  = jp.getInt("Year") ;

        System.out.println("title = " + title);
        System.out.println("year = " + year);

        String director = jp.getString("Director");
        System.out.println("director = " + director);

        // get the first rating source as we did in previous class
        String rating1Src =  jp.getString("Ratings[0].Source") ;
        System.out.println("rating 1 Source = " + rating1Src);

        // store the entire response as Map<String , Object>
        // Since our result is a Json Object with key value pair
        // we can directly call getMap method and provide the path
        // store the whole thing into a Map object
        Map<String,Object> responseMap =  jp.getMap("") ;
//        System.out.println("responseMap = " + responseMap);

        // print out the Awards key from above Map
//        System.out.println("Awards are "  + responseMap.get("Awards"));

        // one more example of Map
        // Store first Rating Json Object into a map
        /**
         *          {
         *             "Source": "Internet Movie Database",
         *             "Value": "6.3/10"
         *         }
         */
        Map<String,Object> firstRatingMap =  jp.getMap("Ratings[0]") ;
        System.out.println("firstRatingMap = " + firstRatingMap);




    }



}
