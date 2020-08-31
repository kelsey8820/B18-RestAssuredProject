package day08;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.ConfigurationReader;
import utility.DB_Utility;

import static io.restassured.RestAssured.given;

public class SpartanApiDB_Practice {

    /**
     * The dev just implemented the search endpoint
     * and want it to be tested to make sure it's actually
     * returning the correct result from the database
     *
     *    GET /spartans/search?gender=Female&nameContains=a
     *  we want to test the count of result is correct
     *  for numberOfElements field.
     *
     *  The Database query to get the count is :
     *  // all the females that have a in their name , case insensitive
     *   -- This is how we get the data with case insensitive manner
     *      SELECT * FROM SPARTANS
     *       WHERE LOWER(gender) = 'female'
     *       and LOWER(name) LIKE '%a%' ;
     *
     */
    @BeforeAll
    public static void init(){

        RestAssured.baseURI = "http://100.25.192.231" ;
        RestAssured.port = 8000;
        RestAssured.basePath = "/api" ;

        DB_Utility.createConnection(ConfigurationReader.getProperty("spartan1.database.url") ,
                                    ConfigurationReader.getProperty("spartan1.database.username") ,
                                    ConfigurationReader.getProperty("spartan1.database.password")
                                    );

    }

    @DisplayName("Testing out my DB Connection ")
    @Test
    public void testDB(){

        //DB_Utility.runQuery("SELECT * FROM SPARTANS") ;
        //DB_Utility.displayAllData();

        // run this query so we can use it for expected result
        String query = "SELECT * FROM SPARTANS     " +
                       " WHERE LOWER(gender) = 'female'  " +
                       " and LOWER(name) LIKE '%a%' ";
        DB_Utility.runQuery( query ) ;
        //DB_Utility.displayAllData();
        //  if you do , get the row count so we can see how many data returned
        // and use it for expected result in next test
        int expectedResult =  DB_Utility.getRowCount();
        System.out.println("expectedResult = " + expectedResult);
    }



    @DisplayName("Testing /spartans/search Endpoint and Validate against DB")
    @Test
    public void testSearch(){

        // make a request to GET /spartans/search
        // using query parameter gender Female  nameContains a

        Response response = given()
                                    .log().all()
                                    .queryParam("gender","Female")
                                    .queryParam("nameContains", "a").
                            when()
                                    .get("/spartans/search")
                                    .prettyPeek();

        int resultCount =  response.path("numberOfElements") ;
        //int resultCount =  response.jsonPath().getInt("numberOfElements") ;
        System.out.println("resultCount = " + resultCount);

    }




    @AfterAll
    public static void destroy(){

        RestAssured.reset();
        DB_Utility.destroy();

    }








}
