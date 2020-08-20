package day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SpartanSearchTest_QueryParam {


    @BeforeAll
    public static void setUp(){

        RestAssured.baseURI = "http://54.174.216.245:8000" ;
        RestAssured.basePath = "/api" ;

    }




}
