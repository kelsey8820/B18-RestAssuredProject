package day09;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Spartan;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class SpartanPostRequestExtractingSpecTest {

    static ResponseSpecification validPostRequestSpec;
    static ResponseSpecification validPostResponseSpec ;


    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://54.160.106.84";
        RestAssured.port = 8000;
        RestAssured.basePath = "/api";
    }

    @DisplayName("Extracting the requestSpec and responseSpec practice")
    @Test
    public void test(){
        // make a post request and assert the status code header and body
        // eventually extract out the spec for reuse

        Spartan randomSp = createRandomSpartanObject();

        // validPostRequestSpec
        // so we want to add the auth , contentType , randomBody , logging
        // into the request spec

        given()
                .auth().basic("admin","admin")
                .accept(ContentType.JSON)   // what type you want from the server as response
                .contentType(ContentType.JSON) // what type you are sending to the server
                .body(randomSp)
                .log().all().
        when()
                .post("/spartans").
        then()
                .log().all()
                .statusCode(201)
                .header("Date" , notNullValue() )
                .body("success",is("A Spartan is Born!") )
                .body("data.name" , is(  randomSp.getName()  )   )
                .body("data.gender" , is(  randomSp.getGender()  )   )
                .body("data.phone" , is(  randomSp.getPhone()  )   )
                .body("data.id", notNullValue() )
                ;

    }



    public static Spartan createRandomSpartanObject() {
        Faker faker = new Faker();
        String name   = faker.name().firstName();
        String gender = faker.demographic().sex();
        // always getting long number outside range of int to avoid errors
        long phone    = faker.number().numberBetween(5000000000L,9999999999L);
        Spartan randomSpartanObject = new Spartan(name,gender,phone);
        System.out.println("Created Random Spartan Object : " + randomSpartanObject);
        return randomSpartanObject ;
    }


}
