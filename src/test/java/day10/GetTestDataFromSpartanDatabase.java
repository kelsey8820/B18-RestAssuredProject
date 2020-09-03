package day10;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.DB_Utility;

import java.util.Map;

public class GetTestDataFromSpartanDatabase {


    @BeforeAll
    public static void init(){

        RestAssured.baseURI = "http://54.174.216.245/" ;
        RestAssured.port = 8000;
        RestAssured.basePath = "/api" ;
        DB_Utility.createConnection("spartan1");

    }


    @DisplayName("Testing GET /Spartans/{id} by getting the id from DB")
    @Test
    public void testDataFromDB(){

        // I want to write a query to get the newest data created from DB
         String myQuery = "SELECT * FROM SPARTANS ORDER BY SPARTAN_ID DESC" ;
        // AND JUST GET THE FIRST ROW USING OUR UTILITY METHOD
        DB_Utility.runQuery( myQuery ) ;  // now we have the result
        Map<String, String> firstRowMap = DB_Utility.getRowMap(1);


    }


}
