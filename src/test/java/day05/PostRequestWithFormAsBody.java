package day05;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class PostRequestWithFormAsBody {

    //posting in library app
    // body is not json , it's x-www-urlencoded-form-data

    //http://library1.cybertekschool.com/rest/v1/login
    // baseURI  is http://library1.cybertekschool.com
    // basePath is /rest/v1
    // we are working on POST /login

    // Post body , type x-www-urlencoded-form-data
    //email :    librarian69@library
    //password : KNPXrm3S

    // in URLs if you do not see port , because it's omitted because it's so common
    //  http --->> 80
    //  https --->> 443
    //  anything other than above 2 ports need to be explicitly set in the URL
    // for example spartan app use port 8000 -->> yourip:8000

    @BeforeAll
    public static void init(){

        RestAssured.baseURI = "http://library1.cybertekschool.com" ;
        RestAssured.basePath = "/rest/v1" ;

    }










}
