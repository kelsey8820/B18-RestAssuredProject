package day12;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
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

        given()
                .accept(ContentType.XML).
        when()
                .get("/spartans").
        then()
                .contentType(ContentType.XML)
                .body("List.item[0].name", is("Mina") )
                .body("List.item[0].gender", is("Male"))
                .body("List.item[0].id", is("424"))

        ;
        // XMLPath xp = response.xmlPath();


    }

}
