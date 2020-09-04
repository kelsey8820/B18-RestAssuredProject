package day11;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utility.ConfigurationReader;

public class GettingDataFromOtherRequests {

    @BeforeAll
    public static void init() {
        // spartan1.base_url=http://54.174.216.245:8000
        RestAssured.baseURI = ConfigurationReader.getProperty("spartan1.base_url");
        RestAssured.basePath = "/api";
    }
    /**
     * We want to test GET /Spartans/{id}
     * and the ID is dynamic , and also different in different environments
     * we want to always work with available data
     * without dealing with 404 issue because data does not exists
     *
     *  Action Items
     *  1. Send a GET /spartans endpoint
     *  2. store the result as List of pojo
     *  3. initially just the the first data and use it for GET /Spartans/{id} request
     *      and use the name , gender , phone for body validation
     *  4 , eventually randomize the way you get the ID from List of Pojo
     */
     @Test
     public void testTheDynamicID(){




     }




}
