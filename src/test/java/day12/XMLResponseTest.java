package day12;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class XMLResponseTest {

    @BeforeAll
    public static void init(){
        // We are going to send a get request to this endpoint
        // in this Rest API Request , it use query parameter to specify the response content type
        //https://vpic.nhtsa.dot.gov/api/vehicles/GetMakeForManufacturer/988?format=xml
        RestAssured.baseURI = "https://vpic.nhtsa.dot.gov";
        RestAssured.basePath = "api/vehicles";
    }

    @Test
    public void testXML(){

        given()
                .log().all()
                .queryParam("format", "xml").
        when()
                .get("/GetMakeForManufacturer/988").
        then()
                //.log().all()
                .statusCode(200)
                .contentType(ContentType.XML)
                // the path must match , and the value is always String in xml
                .body("Response.Count" , is("2") )
                .body("Response.Message", is("Results returned successfully"))
                // find out the first Make_ID under the result by providing the index in the path
                .body("Response.Results.MakesForMfg[0].Make_ID", is("474"))
                // check the Make_Name in second result is Acura
                .body("Response.Results.MakesForMfg[1].Make_Name", is("ACURA") )

                ;

    }


}
