package day11;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pojo.Spartan2;
import utility.ConfigurationReader;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

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

         Response response = get("/spartans");
         List<Spartan2> spartan2List = response.jsonPath().getList("",Spartan2.class);
         System.out.println("spartan2List = " + spartan2List);

         // get the first spartan id so we can send below request :
         int firstSpartanIDFromTheList = spartan2List.get(0).getId() ;
         System.out.println("firstSpartanIDFromTheList = " + firstSpartanIDFromTheList);
         // GET /spartans/{id}
         given()
                 .pathParam("id", firstSpartanIDFromTheList).
         when()
                 .get("/spartans/{id}")
                 .prettyPeek();




     }




}
