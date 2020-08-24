package day04;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;



public class PostRequestTest {

    @BeforeAll
    public static void init(){

        RestAssured.baseURI = "http://54.158.178.13" ;
        RestAssured.port = 8000 ;
        RestAssured.basePath = "/api" ;

    }

    @DisplayName("Post request with String as body")
    @Test
    public void testPostWithStringBody(){

        // lets try to get random names rather than same ADAM each time
        Faker faker = new Faker();
        String randomName = faker.name().firstName();
        System.out.println("randomName = " + randomName);
        // try to randomize gender and phone num on your own at home.
        String bodyString = "{\n" +
                "  \"name\"  : \"" + randomName+ "\",\n" +
                "  \"gender\": \"Female\",\n" +
                "  \"phone\": 6234567890\n" +
                "}";

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(bodyString).
        when()
                .post("/spartans").
        then()
                .log().all()
                .statusCode(201)
                .body("data.name", is( randomName ) ) ;
        ;


    }

    @DisplayName("Posting with external json file")
    @Test
    public void testPostWithExternalFile(){

        // create a file object that point to spartan.json you just added
        // so we can use this as body in post request
        File file1 = new File("spartan.json");

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(file1).
        when()
                .post("/spartans").
        then()
                .log().all()
                .statusCode(201)
                .body("data.name",is("From File") ) ;




    }





}
