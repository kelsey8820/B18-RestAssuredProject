package day11;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import lombok.ToString;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utility.ConfigurationReader;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class JsonSchemaValidation {

    // please create a file called singleSpartanSchema.json
    // under src/test/resources
    // add the schema content I shared in code note
    @BeforeAll
    public static void init() {
        // spartan1.base_url=http://54.174.216.245:8000
        RestAssured.baseURI = ConfigurationReader.getProperty("spartan1.base_url");
        RestAssured.basePath = "/api";
    }

    @Test
    public void testSchema(){

        given()
                .log().uri().
        when()
                .get("/spartans/{id}" , 55)
                .prettyPeek().
        then()
              .body( matchesJsonSchemaInClasspath("singleSpartanSchema.json") )
        ;


    }

}