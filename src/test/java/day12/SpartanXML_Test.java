package day12;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.ConfigurationReader;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SpartanXML_Test {

    // boilerplate goes here
    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = ConfigurationReader.getProperty("spartan1.base_url");
        RestAssured.basePath = "/api";
    }

    @DisplayName("Test XML Response from GET /Spartans")
    @Test
    public void testSpartanXML(){



    }

}
