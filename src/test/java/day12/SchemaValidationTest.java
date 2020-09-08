package day12;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import utility.ConfigurationReader;

public class SchemaValidationTest {

    // LETS JUMP RIGHT IN !!!
    @BeforeAll
    public static void init() {
        // spartan1.base_url=http://54.174.216.245:8000
        RestAssured.baseURI = ConfigurationReader.getProperty("spartan1.base_url");
        RestAssured.basePath = "/api";
    }



}
