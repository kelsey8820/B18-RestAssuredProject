package day09;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SpartanRoleBaseAccessControlNegativeTest {


    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://54.160.106.84";
        RestAssured.port = 8000;
        RestAssured.basePath = "/api";
    }

    @DisplayName("User should not be able to delete data")
    @Test
    public void testUserCanNotDeleteData(){

        given()
                .auth().basic("user","user")
                .accept(ContentType.JSON)
                .log().all().
        when()
                .delete("/spartans/{id}" , 10).
        then()
                .statusCode(403)
                .contentType(ContentType.JSON)
                .header("Date", is( notNullValue() ) ) // checking Date header is not null
                .log().all();



    }

    @DisplayName("User should not be able to update data")
    @Test
    public void testUserCanNotUpdateData(){

    }

    @DisplayName("User should not be able to post data")
    @Test
    public void testUserCanNotPostData(){

    }




}
