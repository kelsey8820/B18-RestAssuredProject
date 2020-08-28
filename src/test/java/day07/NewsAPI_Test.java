package day07 ;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class NewsAPI_Test {


    @DisplayName("News API get all News Author if the Source id is not null")
    @Test
    public void testNews(){

        //GET http://newsapi.org/v2/top-headlines?country=us
        String apiToken = "42bb42f550eb432a90d48201b33380e5";
        // Via the Authorization HTTP header. Bearer

        Response response = given()
                                    .baseUri("http://newsapi.org") // you can specify baseURI directly here if you only have one request and have no intention ofo sharing with diffferent request
                                    .basePath("/v2")
                                    .header("Authorization", "Bearer "+apiToken)
                                    .queryParam("country","us")
                                    .log().all().
                            when().
                                    get("/top-headlines");




    }


}
