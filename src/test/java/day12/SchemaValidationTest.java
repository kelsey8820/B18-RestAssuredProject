package day12;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.ConfigurationReader;

import java.io.File;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class SchemaValidationTest {

    // LETS JUMP RIGHT IN !!!
    @BeforeAll
    public static void init() {
        // spartan1.base_url=http://54.174.216.245:8000
        RestAssured.baseURI = ConfigurationReader.getProperty("spartan1.base_url");
        RestAssured.basePath = "/api";
    }

    @DisplayName("Testing GET /Spartans response against Schema")
    @Test
    public void testAllSpartansSchema(){

        when()
            .get("/spartans").
        then()
            .body(matchesJsonSchemaInClasspath("AllSpartansSchema.json") ) ;

    }

    @DisplayName("Testing GET /Spartans response against Schema in rootPath")
    @Test
    public void testAllSpartansSchemaInRootPath(){

        // Create a File object that point to the schema
        // use matchesJsonSchema method that accept a file
        // and do your validation
        File mySchema = new File("AllSpartans2Schema.json");
        when()
                .get("/spartans").
                then()
                .body( matchesJsonSchema(mySchema) ) ;

    }

    @DisplayName("Testing GET /Spartans/search response against Schema")
    @Test
    public void testSearchSpartansSchema(){

        // search female in query param and validate response against schema
        given()
                .queryParam("gender","Female").
        when()
                .get("/spartans/search").
        then()
                .body(matchesJsonSchemaInClasspath("SearchSchema.json") ) ;

    }
    // what if my schema file is not under resources folder ?
    // then matchesJsonSchemaInClasspath will not work because it only look for
    // schema under resources folder
    // then we have to use matchesJsonSchema method and provide full path

}
