package day06;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;

public class HR_ORDS_GroovyMagic {

    @BeforeAll
    public static void init(){

        RestAssured.baseURI = "http://54.174.216.245" ;
        RestAssured.port = 1000 ;
        RestAssured.basePath = "ords/hr";

    }

    @DisplayName("Testing the /Employees endpoint")
    @Test
    public void testEmployees(){

        Response response = get("/employees") ;//.prettyPeek();

        JsonPath jp = response.jsonPath();

        // print all the id by getting a list
        System.out.println("All IDs "  + jp.getList("items.employee_id") ) ;

        // print first id and last id
        System.out.println("first id " + jp.getInt("items[0].employee_id"));
        System.out.println("last  id " + jp.getInt("items[-1].employee_id"));

        // get all the ids from first one till fifth


    }




}
