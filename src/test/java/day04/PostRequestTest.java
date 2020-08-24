package day04;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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


        String bodyString = "{\n" +
                "  \"name\"  : \"  " + randomName+ "     \",\n" +
                "  \"gender\": \"Female\",\n" +
                "  \"phone\": 6234567890\n" +
                "}";


    }


}
