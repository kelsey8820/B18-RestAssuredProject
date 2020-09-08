package day12;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class XMLResponseTest {

    @BeforeAll
    public static void init(){
        // We are going to send a get request to this endpoint
        //https://vpic.nhtsa.dot.gov/api/vehicles/GetMakeForManufacturer/988?format=xml
        RestAssured.baseURI = "https://vpic.nhtsa.dot.gov";
        RestAssured.basePath = "api/vehicles";
    }


}
