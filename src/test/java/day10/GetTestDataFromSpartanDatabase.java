package day10;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.ConfigurationReader;
import utility.DB_Utility;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetTestDataFromSpartanDatabase {


    @BeforeAll
    public static void init(){

        RestAssured.baseURI = ConfigurationReader.getProperty("spartan1.base_url");
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
        // get the id , name , gender , phone out of this map
        System.out.println("firstRowMap = " + firstRowMap);
        System.out.println("SPARTAN_ID key's value = " + firstRowMap.get("SPARTAN_ID")  );
        // we want to save id , name , gender , phone
        // so we can use the ID to make GET request
        // and verify the response body match the data from Database
        int idFromDB        = Integer.parseInt( firstRowMap.get("SPARTAN_ID") );
        String nameFromDB   = firstRowMap.get("NAME") ;
        String genderFromDB = firstRowMap.get("GENDER") ;
        long phoneFromDB    = Long.parseLong(firstRowMap.get("PHONE"));

                when()
                    .get("/spartans/{id}" , idFromDB ).
                then()
                        .log().all()
                    .statusCode(200)
                    .body("id",  is(idFromDB) )
                    .body("name",  is(nameFromDB) )
                    .body("gender", is(genderFromDB))
                    .body("phone.toLong()", is(phoneFromDB) ) ;
                    // the test is failing if the phone number fall within the range of int
                        // because body method is just getting it as int
                        // and we can not compare int with long since they are not same data type
                        // can I just convert
                        // my phone value from response to long directly inside jsonPath
                        // YES WE CAN ! Because jsonPath is groovy , we can call groovy function
                        // WAIT !! I DO NOT KNOW THE METHOD!!! --->> GOOGLE!!
                        // I found If I add toLong() to the int value , it's turning it into long value
                        // now we will try it out.




    }


}
