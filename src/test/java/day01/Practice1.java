package day01;
import io.restassured.RestAssured;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.* ;
import static io.restassured.matcher.RestAssuredMatchers.* ;
import static org.hamcrest.Matchers.*;


public class Practice1 {

    // you may use your own IP
    // we are using spartan app that does not require password
    //http://54.174.216.245:8000/api/hello

    @Test
    public void test1(){

        // MAKE SURE YOUR REQUEST WORKS IN POSTMAN
        // IF ANYTHING DOES NOT WORK MANNUALY IT WILL NOT WORK IN HERE EITHER

        //RestAssured.get("URL HERE")
        // SINCE WE DID THE STATIC IMPORT
        // we can directly call the get method
        // after we send the request
        // we can save the result in to a type called Response
        // need this  import io.restassured.response.Response;
        Response response = get("http://54.174.216.245:8000/api/hello") ;
        // response object store all the information about the response we got
        // like status  , statusline , body , headers and so on
        System.out.println("status code of this response : " + response.statusCode()  );
        // this is another way of getting status code starts with HTTP/1.1
        System.out.println("getting status line of this response " + response.statusLine() );

        // in restAssured there are usually 2 methods that does same action
        // one directly with the name like response.statusCode()
        // another stating with getXXX  like response.getStatusCode()
        System.out.println("status code of this response : " + response.getStatusCode()  );
        // getting the header out of the response
        // we can use       response.header("the header name goes here )
        // or we can use    response.getHeader("the header name goes here )
        System.out.println("Getting the value of date header " + response.header("Date") ) ;

    }






}
