package day10;

import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Category;
import pojo.User;
import utility.ConfigurationReader;
import utility.LibraryUtil;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class LibraryAppReusingTheSpecification_Shorter {


    @BeforeAll
    public static void init(){

        RestAssured.baseURI = ConfigurationReader.getProperty("library1.base_url");
        RestAssured.basePath = "/rest/v1" ;

        String theToken = LibraryUtil.loginAndGetToken("librarian69@library","KNPXrm3S");
        // just like we set the baseURI and basePath
        // we are using the static field of RestAssured to set it at global level
        RestAssured.requestSpecification  = given().accept(ContentType.JSON)  // we want json back
                                             .log().all()               // we want to log all
                                             .header("x-library-token", theToken) ; // we want to set the token header
        // we are using the static field of RestAssured to set it at global level
        RestAssured.responseSpecification =  expect().statusCode(200)       // expecting the Response status code 200
                                                .contentType(ContentType.JSON)  // contentType is json
                                                .log().all();     // want to log all of them

    }

    /**
     * Practice the De-Serialization using the same test
     * get the Map<String,String> object out of the response of GET /dashboard_stats
     * get the List<Category> object from the response of GET /get_book_categories
     * get the List<User> object from the response of GET /get_all_users
     * hint : you will need to create 2 POJO class called Category , User;
     *
     */
    @DisplayName("Testing GET /get_book_categories Endpoint with spec")
    @Test
    public void testLibrary(){

         Response response = when()
                 .get("/get_book_categories");
         List<Category> categoryList = response.jsonPath().getList("", Category.class) ;
        System.out.println("categoryList = " + categoryList);

    }

    @DisplayName("Testing GET /get_all_users Endpoint with spec")
    @Test
    public void testGetAllUsers(){


        Response response =  when().get(" /get_all_users");
        JsonPath jp = response.jsonPath();
        List<User> allUserLst = jp.getList("", User.class) ;
        System.out.println("allUserLst = " + allUserLst);

    }

    //* get the Map<String,String> object out of the response of GET /dashboard_stats
    @DisplayName("Testing GET /dashboard_stats Endpoint with spec")
    @Test
    public void testGet_Dashboard_stats(){


        Response response =  when().get(" /dashboard_stats").prettyPeek();
        // if there is no path needed to get to what you are looking for
        // or if you wanted to point to your entire response , you can just provide ""
        Map<String,Integer> statMap = response.jsonPath().getMap("") ;
        System.out.println("statMap = " + statMap);

    }

}
