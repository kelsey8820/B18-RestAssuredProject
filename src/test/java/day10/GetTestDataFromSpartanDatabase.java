package day10;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.DB_Utility;

public class GetTestDataFromSpartanDatabase {


    @BeforeAll
    public static void init(){

        RestAssured.baseURI = "http://54.174.216.245/" ;
        RestAssured.port = 8000;
        RestAssured.basePath = "/api" ;

        DB_Utility.createConnection("");

    }


    @DisplayName("Testing GET /Spartans/{id} by getting the id from DB")
    @Test
    public void testDataFromDB(){




    }


}
