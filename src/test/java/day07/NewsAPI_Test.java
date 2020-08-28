package day07 ;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class NewsAPI_Test {


    @DisplayName("News API get all News Author if the Source id is not null")
    @Test
    public void testNews(){


        String apiToken = "YOUR TOKEN GOES HERE"; // get it from here https://newsapi.org/register
        // Via the Authorization HTTP header. Bearer

        //GET http://newsapi.org/v2/top-headlines?country=us
        Response response = given()
                                    .baseUri("http://newsapi.org") // you can specify baseURI directly here if you only have one request and have no intention ofo sharing with diffferent request
                                    .basePath("/v2")
                                    .header("Authorization", "Bearer "+apiToken) // space between Bearer and the token required
                                    .queryParam("country","us")
                                    .log().all().
                            when().
                                    get("/top-headlines");
        JsonPath jp = response.jsonPath() ;

        List<String> allAuthor =  jp.getList("articles.author") ;
        System.out.println("allAuthor = " + allAuthor.size() );
       // allAuthor.forEach( eachAuthor -> System.out.println("eachAuthor = " + eachAuthor));
        // filter out the result by checking the source fields  --->> id filed null or not

        List<String> allAuthorFiltered
                =  jp.getList("articles.findAll { it.source.id !=null }.author") ;

        for (String author : allAuthorFiltered){

            System.out.println("author = " + author);

        }
        System.out.println("allAuthorFiltered = " + allAuthorFiltered.size() );


    }


}
