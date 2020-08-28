package day07;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GOT_Homework {

    // GET https://api.got.show/api/book/characters
    // get all the character names in the list if the character field house = house stark

    @DisplayName("Getting All Members of House Stark")
    @Test
    public void testingGOT(){

        Response response = given()
                                .baseUri("https://api.got.show")
                                .basePath("/api/book").
                            when()
                                .get("/characters")
                                ;
        List<String> houseStarkList =
                    response.jsonPath().getList(" findAll {it.house=='House Stark'}.name ") ;
        System.out.println("houseStarkList = " + houseStarkList);
        // you list should have size 76 ;

        assertThat( houseStarkList , hasSize(76) ) ;


    }


}
